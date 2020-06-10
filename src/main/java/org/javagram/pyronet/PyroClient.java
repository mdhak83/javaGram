package org.javagram.pyronet;

import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.javagram.mtproto.transport.ByteBufferDesc;

public class PyroClient {

    private final PyroSelector selector;
    private final SelectionKey key;
    private final ByteStream outbound;
    private final List<PyroClientListener> listeners;
    private Object attachment;
    private boolean doEagerWrite = false;
    private boolean doShutdown = false;
    private long timeout = 0L;
    private long lastEventTime;

    // called by PyroSelector.connect()
    PyroClient(PyroSelector selector, InetSocketAddress bind, InetSocketAddress host) throws IOException {
        this(selector, PyroClient.bindAndConfigure(selector, SocketChannel.open(), bind));
        ((SocketChannel) this.key.channel()).connect(host);
    }

    // called by PyroClient and PyroServer
    PyroClient(PyroSelector selector, SelectionKey key) {
        this.selector = selector;
        this.selector.checkThread();
        this.key = key;
        this.key.attach(this);
        this.outbound = new ByteStream();
        this.listeners = new CopyOnWriteArrayList<>();
        this.lastEventTime = System.currentTimeMillis();
    }

    public void addListener(PyroClientListener listener) {
        this.selector.checkThread();
        this.listeners.add(listener);
    }

    public void removeListener(PyroClientListener listener) {
        this.selector.checkThread();
        this.listeners.remove(listener);
    }

    public void removeListeners() {
        this.selector.checkThread();
        this.listeners.clear();
    }

    /**
     * Returns the PyroSelector that created this client
     * @return 
     */
    public PyroSelector selector() {
        return this.selector;
    }

    /**
     * Attach any object to a client, for example to store session information
     * @param attachment
     */
    public void attach(Object attachment) {
        this.attachment = attachment;
    }

    /**
     * Returns the previously attached object, or <code>null</code> if none is set
     * @param <T>
     * @return 
     */
    public <T> T attachment() {
        return (T) this.attachment;
    }

    /**
     * Returns the local socket address (host+port)
     * @return 
     */
    public InetSocketAddress getLocalAddress() {
        Socket s = ((SocketChannel) this.key.channel()).socket();
        return (InetSocketAddress) s.getLocalSocketAddress();
    }

    /**
     * Returns the remove socket address (host+port)
     * @return 
     */
    public InetSocketAddress getRemoteAddress() {
        Socket s = ((SocketChannel) key.channel()).socket();
        return (InetSocketAddress) s.getRemoteSocketAddress();
    }

    public void setTimeout(int ms) throws IOException {
        this.selector.checkThread();
        ((SocketChannel) this.key.channel()).socket().setSoTimeout(ms);
        // prevent a call to setTimeout from immediately causing a timeout
        this.lastEventTime = System.currentTimeMillis();
        this.timeout = ms;
    }

    public void setLinger(boolean enabled, int seconds) throws IOException {
        this.selector.checkThread();
        ((SocketChannel) this.key.channel()).socket().setSoLinger(enabled, seconds);
    }

    public void setKeepAlive(boolean enabled) throws IOException {
        this.selector.checkThread();
        ((SocketChannel) this.key.channel()).socket().setKeepAlive(enabled);
    }

    /**
     * If enabled, causes calls to write() to make an attempt to write the
     * bytes, without waiting for the selector to signal writable state.
     * @param enabled
     */
    public void setEagerWrite(boolean enabled) {
        this.doEagerWrite = enabled;
    }

    /**
     * Will enqueue the bytes to send them<br>
     * 1. when the selector is ready to write, if eagerWrite is disabled (default)<br>
     * 2. immediately, if eagerWrite is enabled<br>
     * The ByteBuffer instance is kept, not copied, and thus should not be modified
     *
     * @param data
     * @throws PyroException when shutdown() has been called.
     */
    public void write(ByteBufferDesc data) throws PyroException {
        this.selector.checkThread();
        if (!this.key.isValid()) {
            // graceful, as this is meant to be async
            return;
        }
        if (this.doShutdown) {
            throw new PyroException("shutting down");
        }
        this.outbound.append(data);
        if (this.doEagerWrite) {
            try {
                this.onReadyToWrite(System.currentTimeMillis());
            } catch (NotYetConnectedException exc) {
                this.adjustWriteOp();
            } catch (IOException exc) {
                this.onConnectionError(exc);
                this.key.cancel();
            }
        } else {
            this.adjustWriteOp();
        }
    }

    /**
     * Writes as many as possible bytes to the socket buffer
     * @return 
     */
    public int flush() {
        int total = 0;
        int written = -1;
        while (this.outbound.hasData() && written != 0) {
            try {
                written = this.onReadyToWrite(System.currentTimeMillis());
            } catch (IOException exc) {
                written = 0;
            }
            total += written;
        }
        return total;
    }

    /**
     * Makes an attempt to write all outbound bytes, fails on failure.
     *
     * @return 
     * @throws PyroException on failure
     */
    public int flushOrDie() throws PyroException {
        int total = 0;
        int written;
        while (this.outbound.hasData()) {
            try {
                written = this.onReadyToWrite(System.currentTimeMillis());
            } catch (IOException exc) {
                written = 0;
            }
            if (written == 0) {
                throw new PyroException("failed to flush, wrote " + total + " bytes");
            }
            total += written;
        }
        return total;
    }

    /**
     * Returns whether there are bytes left in the outbound queue.
     * @return 
     */
    public boolean hasDataEnqueued() {
        this.selector.checkThread();
        return this.outbound.hasData();
    }

    /**
     * Gracefully shuts down the connection.
     * The connection is closed after the last outbound bytes are sent.
     * Enqueuing new bytes after shutdown, is not allowed and will throw an exception
     */
    public void shutdown() {
        this.selector.checkThread();
        this.doShutdown = true;
        if (!this.hasDataEnqueued()) {
            this.dropConnection();
        }
    }

    /**
     * Immediately drop the connection, regardless of any pending outbound
     * bytes. Actual behaviour depends on the socket linger settings.
     */
    public void dropConnection() {
        this.selector.checkThread();
        if (this.isDisconnected()) {
            return;
        }
        Runnable drop = new Runnable() {
            @Override
            @SuppressWarnings("synthetic-access")
            public void run() {
                try {
                    if (key.channel().isOpen()) {
                        ((SocketChannel) key.channel()).close();
                    }
                } catch (IOException ex) {
                    selector().scheduleTask(this);
                }
            }
        };
        drop.run();
        this.onConnectionError("local");
    }

    /**
     * Returns whether the connection is connected to a remote client.
     * @return 
     */
    public boolean isDisconnected() {
        this.selector.checkThread();
        return !((SocketChannel) this.key.channel()).isOpen();
    }

    //
    void onInterestOp(long now) {
        if (!this.key.isValid()) {
            this.onConnectionError("remote");
        } else {
            try {
                if (this.key.isConnectable()) {
                    this.onReadyToConnect(now);
                }
                if (this.key.isReadable()) {
                    this.onReadyToRead(now);
                }
                if (this.key.isWritable()) {
                    this.onReadyToWrite(now);
                }
            } catch (IOException ex) {
                this.onConnectionError(ex);
                this.key.cancel();
            }
        }
    }

    protected boolean didTimeout(long now) {
        if (this.timeout == 0) {
            return false; // never timeout
        }
        return (now - this.lastEventTime) > this.timeout;
    }

    private void onReadyToConnect(long now) throws IOException {
        this.selector.checkThread();
        this.lastEventTime = now;
        this.selector.adjustInterestOp(key, SelectionKey.OP_CONNECT, false);
        ((SocketChannel) this.key.channel()).finishConnect();
        this.listeners.forEach((listener) -> {
            listener.connectedClient(this);
        });
    }

    private void onReadyToRead(long now) throws IOException {
        this.selector.checkThread();
        this.lastEventTime = now;
        SocketChannel channel = (SocketChannel) this.key.channel();
        ByteBuffer buffer = this.selector.networkBuffer;
        // read from channel
        buffer.clear();
        int bytes = channel.read(buffer);
        if (bytes == -1) {
            throw new EOFException();
        }
        buffer.flip();
        this.listeners.forEach((listener) -> {
            listener.receivedData(this, buffer);
        });
    }

    private int onReadyToWrite(long now) throws IOException {
        this.selector.checkThread();
        int sent = 0;
        // copy outbound bytes into network buffer
        ByteBuffer buffer = this.selector.networkBuffer;
        buffer.clear();
        this.outbound.get(buffer);
        buffer.flip();
        // write to channel
        if (buffer.hasRemaining()) {
            SocketChannel channel = (SocketChannel) this.key.channel();
            sent = channel.write(buffer);
        }
        if (sent > 0) {
            this.outbound.discard(sent);
        }
        for (PyroClientListener listener : this.listeners) {
            listener.sentData(this, sent);
        }
        this.adjustWriteOp();
        if (this.doShutdown && !this.outbound.hasData()) {
            this.dropConnection();
        }
        return sent;
    }

    void onConnectionError(final Object cause) {
        this.selector.checkThread();
        try {
            // if the key is invalid, the channel may remain open!!
            ((SocketChannel) this.key.channel()).close();
        } catch (IOException ex) {
            // message: "A non-blocking socket operation could not be completed immediately"
            // try again later
            this.selector.scheduleTask(new Runnable() {
                @Override
                public void run() {
                    PyroClient.this.onConnectionError(cause);
                }
            });
            return;
        }

        if (cause instanceof ConnectException) {
            this.listeners.forEach((listener) -> {
                listener.unconnectableClient(this);
            });
        } else if (cause instanceof EOFException) {
            // after read=-1
            this.listeners.forEach((listener) -> {
                listener.disconnectedClient(this);
            });
        } else if (cause instanceof IOException) {
            this.listeners.forEach((listener) -> {
                listener.droppedClient(this, (IOException) cause);
            });
        } else if (!(cause instanceof String)) {
            throw new IllegalStateException((Exception) cause);
        } else if (cause.equals("local")) {
            this.listeners.forEach((listener) -> {
                listener.disconnectedClient(this);
            });
        } else if (cause.equals("remote")) {
            this.listeners.forEach((listener) -> {
                listener.droppedClient(this, null);
            });
        } else {
            throw new IllegalStateException("illegal cause: " + cause);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + this.getAddressText() + "]";
    }

    private String getAddressText() {
        if (!this.key.channel().isOpen()) {
            return "closed";
        }
        InetSocketAddress sockaddr = this.getRemoteAddress();
        if (sockaddr == null) {
            return "connecting";
        }
        InetAddress inetaddr = sockaddr.getAddress();
        return inetaddr.getHostAddress() + "@" + sockaddr.getPort();
    }

    void adjustWriteOp() {
        this.selector.checkThread();
        boolean interested = this.outbound.hasData();
        this.selector.adjustInterestOp(this.key, SelectionKey.OP_WRITE, interested);
    }

    static final SelectionKey bindAndConfigure(PyroSelector selector, SocketChannel channel, InetSocketAddress bind) throws IOException {
        selector.checkThread();
        channel.socket().bind(bind);
        return configure(selector, channel, true);
    }

    static final SelectionKey configure(PyroSelector selector, SocketChannel channel, boolean connect) throws IOException {
        selector.checkThread();
        channel.configureBlocking(false);
        channel.socket().setSoLinger(true, 4);
        channel.socket().setReuseAddress(true);
        channel.socket().setKeepAlive(false);
        channel.socket().setTcpNoDelay(true);
        channel.socket().setReceiveBufferSize(PyroSelector.BUFFER_SIZE);
        channel.socket().setSendBufferSize(PyroSelector.BUFFER_SIZE);
        int ops = SelectionKey.OP_READ;
        if (connect) {
            ops |= SelectionKey.OP_CONNECT;
        }
        return selector.register(channel, ops);
    }

}