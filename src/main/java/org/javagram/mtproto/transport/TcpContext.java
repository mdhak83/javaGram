package org.javagram.mtproto.transport;

import org.javagram.mtproto.MTProto;
import org.javagram.mtproto.log.Logger;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import org.javagram.pyronet.PyroClient;
import org.javagram.pyronet.PyroClientListener;
import org.javagram.pyronet.PyroSelector;

public class TcpContext implements PyroClientListener {

    private static volatile Integer NextChannelToken = 1;
    private static final int MAX_PACKED_SIZE = 1024 * 1024 * 1024;//1 MB
    private static final AtomicInteger CONTEXT_LAST_ID = new AtomicInteger(1);
    private static final int CONNECTION_TIMEOUT = 30000;
    private final static AtomicInteger SELECTOR_THREAD_INDEX = new AtomicInteger(0);

    private ConnectionState connectionState;
    private int failedConnectionCount;
    private int willRetryConnectCount = 5;
    private boolean hasSomeDataSinceLastConnect = false;
    private int channelToken = 0;
    private final Object timerSync = new Object();
    private Timer reconnectTimer;
    private boolean isFirstPackage = true;

    private final String TAG;
    private final String ip;
    private final int port;
    private final int contextId;
    private int sentPackets;

    private final PyroSelector selector;
    private PyroClient client;
    private ByteBufferDesc restOfTheData;
    private int lastPacketLength;

    private final TcpContextCallback callback;

    public TcpContext(MTProto proto, String ip, int port, TcpContextCallback callback) {
        this.contextId = CONTEXT_LAST_ID.incrementAndGet();
        this.connectionState = ConnectionState.TcpConnectionStageIdle;
        this.TAG = "MTProto#" + proto.getInstanceIndex() + "#Transport" + this.contextId;
        this.ip = ip;
        this.port = port;
        this.callback = callback;
        this.selector = new PyroSelector();
        this.selector.spawnNetworkThread("Selector-Thread#" + SELECTOR_THREAD_INDEX.getAndIncrement());
        BuffersStorage.getInstance();
    }
    
    public void close() {
        try {
            this.selector.close();
        } catch (IOException ex) {
            Logger.e(ex.getMessage(), ex);
        }
    }

    private static int generateChannelToken() {
        return NextChannelToken++;
    }

    private void readData(ByteBuffer buffer) throws Exception {
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.rewind();

        ByteBuffer parseLaterBuffer = null;
        if (this.restOfTheData != null) {
            if (this.lastPacketLength == 0) {
                if ((this.restOfTheData.capacity() - this.restOfTheData.position()) >= buffer.limit()) {
                    this.restOfTheData.limit(this.restOfTheData.position() + buffer.limit());
                    this.restOfTheData.put(buffer);
                    buffer = restOfTheData.buffer;
                } else {
                    final ByteBufferDesc newBuffer = BuffersStorage.getInstance().getFreeBuffer(restOfTheData.limit() + buffer.limit());
                    this.restOfTheData.rewind();
                    newBuffer.put(this.restOfTheData.buffer);
                    newBuffer.put(buffer);
                    buffer = newBuffer.buffer;
                    BuffersStorage.getInstance().reuseFreeBuffer(restOfTheData);
                    this.restOfTheData = newBuffer;
                }
            } else {
                final int len;
                if ((this.lastPacketLength - this.restOfTheData.position()) <= buffer.limit()) {
                    len = this.lastPacketLength - this.restOfTheData.position();
                } else {
                    len = buffer.limit();
                }
                final int oldLimit = buffer.limit();
                buffer.limit(len);
                this.restOfTheData.put(buffer);
                buffer.limit(oldLimit);
                if (this.restOfTheData.position() == this.lastPacketLength) {
                    if (buffer.hasRemaining()) {
                        parseLaterBuffer = buffer;
                    } else {
                        parseLaterBuffer = null;
                    }
                    buffer = this.restOfTheData.buffer;
                } else {
                    return;
                }
            }
        }

        buffer.rewind();

        while (buffer.hasRemaining()) {
            if (!this.hasSomeDataSinceLastConnect) {
                this.client.setTimeout(CONNECTION_TIMEOUT * 30);
            }
            this.hasSomeDataSinceLastConnect = true;

            final int currentPacketLength;
            buffer.mark();
            final byte fByte = buffer.get();

            if ((fByte & (1 << 7)) != 0) {
                buffer.reset();
                if (buffer.remaining() < 4) {
                    final ByteBufferDesc reuseLater = this.restOfTheData;
                    this.restOfTheData = BuffersStorage.getInstance().getFreeBuffer(16384);
                    this.restOfTheData.put(buffer);
                    this.restOfTheData.limit(this.restOfTheData.position());
                    this.lastPacketLength = 0;
                    if (reuseLater != null) {
                        BuffersStorage.getInstance().reuseFreeBuffer(reuseLater);
                    }
                    break;
                }
                buffer.order(ByteOrder.BIG_ENDIAN);
                final int ackId = buffer.getInt() & (~(1 << 31));
                TcpContext.this.callback.onFastConfirm(ackId);
                buffer.order(ByteOrder.LITTLE_ENDIAN);
                continue;
            }

            if (fByte == 0x7f) {
                buffer.reset();
                if (buffer.remaining() < 4) {
                    if ((this.restOfTheData == null) || ((this.restOfTheData != null) && (this.restOfTheData.position() != 0))) {
                        final ByteBufferDesc reuseLater = this.restOfTheData;
                        this.restOfTheData = BuffersStorage.getInstance().getFreeBuffer(16384);
                        this.restOfTheData.put(buffer);
                        this.restOfTheData.limit(this.restOfTheData.position());
                        this.lastPacketLength = 0;
                        if (reuseLater != null) {
                            BuffersStorage.getInstance().reuseFreeBuffer(reuseLater);
                        }
                    } else {
                        this.restOfTheData.position(this.restOfTheData.limit());
                    }
                    break;
                }
                currentPacketLength = (buffer.getInt() >> 8) * 4;
            } else {
                currentPacketLength = ((int) fByte) * 4;
            }

            if (((currentPacketLength % 4) != 0) || (currentPacketLength > MAX_PACKED_SIZE)) {
                Logger.e(TcpContext.this.TAG, "Invalid packet length");
                reconnect();
                return;
            }

            if (currentPacketLength < buffer.remaining()) {
                Logger.d(TcpContext.this.TAG, TcpContext.this + " Received message len " + currentPacketLength + " but packet larger " + buffer.remaining());
            } else if (currentPacketLength == buffer.remaining()) {
                Logger.d(TcpContext.this.TAG, TcpContext.this + " Received message len " + currentPacketLength + " equal to packet size");
            } else {
                Logger.d(TcpContext.this.TAG, TcpContext.this + " Received packet size less(" + buffer.remaining() + ") then message size(" + currentPacketLength + ")");

                ByteBufferDesc reuseLater = null;
                final int len = currentPacketLength + ((fByte == 0x7f) ? 4 : 1);
                if ((this.restOfTheData != null) && (this.restOfTheData.capacity() < len)) {
                    reuseLater = this.restOfTheData;
                    this.restOfTheData = null;
                }
                if (this.restOfTheData == null) {
                    buffer.reset();
                    this.restOfTheData = BuffersStorage.getInstance().getFreeBuffer(len);
                    this.restOfTheData.put(buffer);
                } else {
                    this.restOfTheData.position(restOfTheData.limit());
                    this.restOfTheData.limit(len);
                }
                this.lastPacketLength = len;
                if (reuseLater != null) {
                    BuffersStorage.getInstance().reuseFreeBuffer(reuseLater);
                }
                return;
            }

            final ByteBufferDesc toProceed = BuffersStorage.getInstance().getFreeBuffer(currentPacketLength);
            final int old = buffer.limit();
            buffer.limit(buffer.position() + currentPacketLength);
            toProceed.put(buffer);
            buffer.limit(old);
            toProceed.rewind();


            if (toProceed.buffer.remaining() == 4) {
                final int error = toProceed.readInt32(false);
                TcpContext.this.onError(error);
            } else {
                final byte[] pkg = new byte[toProceed.buffer.remaining()];
                toProceed.readRaw(pkg, false);
                onMessage(pkg, currentPacketLength);
                BuffersStorage.getInstance().reuseFreeBuffer(toProceed);
            }

            if (this.restOfTheData != null) {
                if (((this.lastPacketLength != 0) && (this.restOfTheData.position() == this.lastPacketLength)) || ((this.lastPacketLength == 0) && !this.restOfTheData.hasRemaining())) {
                    BuffersStorage.getInstance().reuseFreeBuffer(restOfTheData);
                    this.restOfTheData = null;
                } else {
                    this.restOfTheData.compact();
                    this.restOfTheData.limit(this.restOfTheData.position());
                    this.restOfTheData.position(0);
                }
            }

            if (parseLaterBuffer != null) {
                buffer = parseLaterBuffer;
                parseLaterBuffer = null;
            }
        }
    }

    public int getContextId() {
        return this.contextId;
    }

    public void postMessage(byte[] data, boolean useFastConfirm) {
        final ByteBufferDesc buffer = BuffersStorage.getInstance().getFreeBuffer(data.length);
        buffer.writeRaw(data);
        sendData(buffer, true, useFastConfirm);
    }

    private synchronized void onMessage(byte[] data, int len) {
        this.callback.onRawMessage(data, 0, len, this);
    }

    private synchronized void onError(int errorCode) {
        this.callback.onError(errorCode, this);
    }

    private void sendData(final ByteBufferDesc buff, final boolean canReuse, final boolean reportAck) {
        if (buff == null) {
            return;
        }
        this.selector.scheduleTask(() -> {
            if ((this.connectionState == ConnectionState.TcpConnectionStageIdle) ||
                (this.connectionState == ConnectionState.TcpConnectionStageReconnecting) ||
                (this.connectionState == ConnectionState.TcpConnectionStageSuspended) || (client == null)) {
                this.connect();
            }

            if ((this.client == null) || this.client.isDisconnected()) {
                if (canReuse) {
                    BuffersStorage.getInstance().reuseFreeBuffer(buff);
                }
                Logger.e(TcpContext.this.TAG, TcpContext.this + " disconnected, don't send data");
                return;
            }

            int bufferLen = buff.limit();
            int packetLength = bufferLen / 4;

            if (packetLength < 0x7f) {
                bufferLen++;
            } else {
                bufferLen += 4;
            }
            if (this.isFirstPackage) {
                bufferLen++;
            }

            final ByteBufferDesc buffer = BuffersStorage.getInstance().getFreeBuffer(bufferLen);
            if (this.isFirstPackage) {
                buffer.writeByte((byte) 0xef);
                this.isFirstPackage = false;
            }
            if (packetLength < 0x7f) {
                if (reportAck) {
                    packetLength |= (1 << 7);
                }
                buffer.writeByte(packetLength);
            } else {
                packetLength = (packetLength << 8) + 0x7f;
                if (reportAck) {
                    packetLength |= (1 << 7);
                }
                buffer.writeInt32(packetLength);
            }

            buffer.writeRaw(buff);
            if (canReuse) {
                BuffersStorage.getInstance().reuseFreeBuffer(buff);
            }

            buffer.rewind();

            this.sentPackets++;
            this.client.write(buffer);
        });
    }

    //region PyroClient Overrides

    @Override
    public void connectedClient(PyroClient client) {
        this.connectionState = ConnectionState.TcpConnectionStageConnected;
        this.channelToken = generateChannelToken();
        Logger.d(TcpContext.this.TAG, "Client connected: " + this.channelToken);
    }

    @Override
    public void unconnectableClient(PyroClient client, Exception cause) {
        this.handleDisconnect(client, false);
    }

    @Override
    public void droppedClient(PyroClient client, IOException cause) {
        this.handleDisconnect(client, (cause instanceof SocketTimeoutException));
    }

    @Override
    public void disconnectedClient(PyroClient client) {
        this.handleDisconnect(client, false);
    }

    @Override
    public void receivedData(PyroClient client, ByteBuffer data) {
        try {
            this.failedConnectionCount = 0;
            this.readData(data);
        } catch (Exception e) {
            Logger.e(TcpContext.this.TAG, e);
            reconnect();
        }
    }

    @Override
    public void sentData(PyroClient client, int bytes) {
        Logger.d(TcpContext.this.TAG, "Received data " + bytes);
    }

    //endregion PyroClient Overrides

    private synchronized void handleDisconnect(PyroClient client, boolean timeout) {
        synchronized(this.timerSync) {
            if (this.reconnectTimer != null) {
                this.reconnectTimer.cancel();
                this.reconnectTimer = null;
            }
        }

        this.isFirstPackage = true;
        if (this.restOfTheData != null) {
            BuffersStorage.getInstance().reuseFreeBuffer(restOfTheData);
            this.restOfTheData = null;
        }
        this.channelToken = 0;
        this.lastPacketLength = 0;
        if ((this.connectionState != ConnectionState.TcpConnectionStageSuspended) && (this.connectionState != ConnectionState.TcpConnectionStageIdle)) {
            this.connectionState = ConnectionState.TcpConnectionStageIdle;
        }

        this.callback.onChannelBroken(TcpContext.this);

        if (this.connectionState == ConnectionState.TcpConnectionStageIdle) {
            this.failedConnectionCount++;
            if (this.failedConnectionCount == 1) {
                if (this.hasSomeDataSinceLastConnect) {
                    this.willRetryConnectCount = 5;
                } else {
                    this.willRetryConnectCount = 1;
                }
            }
            Logger.d(TcpContext.this.TAG, "Reconnect " + this.ip + ":" + this.port + " " + TcpContext.this);
            try {
                synchronized(this.timerSync) {
                    this.reconnectTimer = new Timer();
                    this.reconnectTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            selector.scheduleTask(() -> {
                                try {
                                    synchronized(timerSync) {
                                        if (reconnectTimer != null) {
                                            reconnectTimer.cancel();
                                            reconnectTimer = null;
                                        }
                                    }
                                } catch (Exception e2) {
                                    Logger.e(TcpContext.this.TAG, e2);
                                }
                                connect();
                            });
                        }
                    }, (failedConnectionCount > 3) ? 500 : 300, (failedConnectionCount > 3) ? 500 : 300);
                }
            } catch (Exception e3) {
                Logger.e(TcpContext.this.TAG, e3);
            }
        }
    }

    public void connect() {
        this.selector.scheduleTask(() -> {
            if (((this.connectionState == ConnectionState.TcpConnectionStageConnected) || (this.connectionState == ConnectionState.TcpConnectionStageConnecting)) && (this.client != null)) {
                return;
            }

            this.connectionState = ConnectionState.TcpConnectionStageConnecting;
            try {
                try {
                    synchronized(this.timerSync) {
                        if (this.reconnectTimer != null) {
                            this.reconnectTimer.cancel();
                            this.reconnectTimer = null;
                        }
                    }
                } catch (Exception e2) {
                    Logger.e(TcpContext.this.TAG, e2);
                }

                Logger.d(TcpContext.this.TAG, String.format(TcpContext.this + " Connecting (%s:%d)", ip, port));
                this.isFirstPackage = true;
                if (this.restOfTheData != null) {
                    BuffersStorage.getInstance().reuseFreeBuffer(restOfTheData);
                    this.restOfTheData = null;
                }
                this.lastPacketLength = 0;
                this.hasSomeDataSinceLastConnect = false;
                if (this.client != null) {
                    this.client.removeListener(TcpContext.this);
                    this.client.dropConnection();
                    this.client = null;
                }
                this.client = this.selector.connect(new InetSocketAddress(ip, port));
                this.client.addListener(TcpContext.this);
                this.client.setTimeout(CONNECTION_TIMEOUT);
                this.selector.wakeup();
            } catch (Exception e) {
                handleConnectionError(e);
            }
        });
    }

    private void handleConnectionError(Exception e) {
        try {
            synchronized(this.timerSync) {
                if (this.reconnectTimer != null) {
                    this.reconnectTimer.cancel();
                    this.reconnectTimer = null;
                }
            }
        } catch (Exception e2) {
            Logger.e(TcpContext.this.TAG, e2);
        }
        this.connectionState = ConnectionState.TcpConnectionStageReconnecting;
        this.callback.onChannelBroken(TcpContext.this);
        this.failedConnectionCount++;
        if (this.failedConnectionCount == 1) {
            if (this.hasSomeDataSinceLastConnect) {
                this.willRetryConnectCount = 3;
            } else {
                this.willRetryConnectCount = 1;
            }
        }
        if (this.failedConnectionCount > this.willRetryConnectCount) {
            this.failedConnectionCount = 0;
        }

        if (e != null) {
            Logger.e(TcpContext.this.TAG, e);
        }
        Logger.d(TcpContext.this.TAG, "Reconnect " + ip + ":" + port + " " + TcpContext.this);
        try {
            this.reconnectTimer = new Timer();
            this.reconnectTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    selector.scheduleTask(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                synchronized (timerSync) {
                                    if (reconnectTimer != null) {
                                        reconnectTimer.cancel();
                                        reconnectTimer = null;
                                    }
                                }
                            } catch (Exception e2) {
                                Logger.e(TcpContext.this.TAG, e2);
                            }
                            connect();
                        }
                    });
                }
            }, (this.failedConnectionCount >= 3) ? 500 : 300, (this.failedConnectionCount >= 3) ? 500 : 300);
        } catch (Exception e3) {
            Logger.e(TcpContext.this.TAG, e3);
        }
    }

    private void reconnect() {
        this.suspendConnection(false);
        this.connectionState = ConnectionState.TcpConnectionStageReconnecting;
        this.connect();
    }

    public void suspendConnection(boolean task) {
        if (task) {
            this.selector.scheduleTask(new Runnable() {
                @Override
                public void run() {
                    suspendConnectionInternal();
                }
            });
        } else {
            this.suspendConnectionInternal();
        }
    }

    private void suspendConnectionInternal() {
        synchronized(this.timerSync) {
            if (this.reconnectTimer != null) {
                this.reconnectTimer.cancel();
                this.reconnectTimer = null;
            }
        }
        if ((this.connectionState == ConnectionState.TcpConnectionStageIdle) || (this.connectionState == ConnectionState.TcpConnectionStageSuspended)) {
            return;
        }
        Logger.d(TcpContext.this.TAG, "suspend connnection " + TcpContext.this);
        this.connectionState = ConnectionState.TcpConnectionStageSuspended;
        if (this.client != null) {
            this.client.removeListener(TcpContext.this);
            this.client.dropConnection();
            this.client = null;
        }
        this.callback.onChannelBroken(TcpContext.this);
        this.isFirstPackage = true;
        if (this.restOfTheData != null) {
            BuffersStorage.getInstance().reuseFreeBuffer(restOfTheData);
            this.restOfTheData = null;
        }
        this.lastPacketLength = 0;
        this.channelToken = 0;
    }

}