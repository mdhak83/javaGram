package org.telegram.mtproto;

import org.telegram.mtproto.backoff.ApiErrorExponentialBackoff;
import org.telegram.mtproto.backoff.ExponentalBackoff;
import org.telegram.mtproto.log.Logger;
import org.telegram.mtproto.schedule.PrepareSchedule;
import org.telegram.mtproto.schedule.PreparedPackage;
import org.telegram.mtproto.schedule.Scheduller;
import org.telegram.mtproto.secure.Entropy;
import org.telegram.mtproto.state.AbsMTProtoState;
import org.telegram.mtproto.state.KnownSalt;
import org.telegram.mtproto.time.TimeOverlord;
import org.telegram.mtproto.tl.*;
import org.telegram.mtproto.transport.ConnectionType;
import org.telegram.mtproto.transport.TcpContext;
import org.telegram.mtproto.transport.TcpContextCallback;
import org.telegram.mtproto.transport.TransportRate;
import org.telegram.mtproto.util.BytesCache;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.MyTLAppConfiguration;
import static org.telegram.mtproto.secure.CryptoUtils.*;
import static org.telegram.mtproto.util.TimeUtil.getUnixTime;
import org.telegram.utils.DeserializeException;
import static org.telegram.utils.StreamingUtils.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 03.11.13
 * Time: 8:14
 */
public class MTProto {
    private static final AtomicInteger instanceIndex = new AtomicInteger(1000);

    private static final int MESSAGES_CACHE = 3000;
    private static final int MESSAGES_CACHE_MIN = 20;

    private static final int MAX_INTERNAL_FLOOD_WAIT = 10;//10 sec

    private static final int PING_INTERVAL_REQUEST = 60000;
    private static final int PING_INTERVAL = 75;//75 secs

    private static final int ERROR_MSG_ID_TOO_SMALL = 16;
    private static final int ERROR_MSG_ID_TOO_BIG = 17;
    private static final int ERROR_MSG_ID_BITS = 18;
    private static final int ERROR_CONTAINER_MSG_ID_INCORRECT = 19;
    private static final int ERROR_TOO_OLD = 20;
    private static final int ERROR_SEQ_NO_TOO_SMALL = 32;
    private static final int ERROR_SEQ_NO_TOO_BIG = 33;
    private static final int ERROR_SEQ_EXPECTED_EVEN = 34;
    private static final int ERROR_SEQ_EXPECTED_ODD = 35;
    private static final int ERROR_BAD_SERVER_SALT = 48;
    private static final int ERROR_BAD_CONTAINER = 64;

    private static final int PING_TIMEOUT = 60 * 1000;
    private static final int RESEND_TIMEOUT = 60 * 1000;

    private static final int FUTURE_REQUEST_COUNT = 64;
    private static final int FUTURE_MINIMAL = 5;
    private static final long FUTURE_TIMEOUT = 30 * 60 * 1000;//30 secs

    private final String TAG;
    private final int INSTANCE_INDEX;
    private final HashSet<TcpContext> contexts = new HashSet<>();
    private final HashMap<Integer, Integer> contextConnectionId = new HashMap<>();
    private final HashSet<Integer> connectedContexts = new HashSet<>();
    private final HashSet<Integer> initedContext = new HashSet<>();
    private final Scheduller scheduler;
    private final MyTLAppConfiguration config;
    private final ConcurrentLinkedQueue<MTMessage> inQueue = new ConcurrentLinkedQueue<>();
    private final ArrayList<Long> receivedMessages = new ArrayList<>();
    private final MTProtoContext protoContext;
    private final int desiredConnectionCount;
    private final TcpContextCallback tcpListener;
    private final ConnectionFixerThread connectionFixerThread;
    private final SchedullerThread schedullerThread;
    private final ResponseProcessor responseProcessor;
    private final byte[] authKey;
    private final byte[] authKeyId;
    private byte[] session;

    private boolean isClosed;

    private final MTProtoCallback callback;

    private final AbsMTProtoState state;

    private long futureSaltsRequestedTime = Long.MIN_VALUE;

    private int roundRobin;

    private TransportRate connectionRate;

    private long lastPingTime = (System.nanoTime() / 1000000L) - (PING_INTERVAL_REQUEST * 10);

    private final ExponentalBackoff exponentalBackoff;
    private final ApiErrorExponentialBackoff apiErrorExponentialBackoff;
    private final ConcurrentLinkedQueue<Long> newSessionsIds = new ConcurrentLinkedQueue<>();

    public MTProto(AbsMTProtoState state, MTProtoCallback callback, CallWrapper callWrapper, int connectionsCount, MyTLAppConfiguration config) {
        this.INSTANCE_INDEX = instanceIndex.incrementAndGet();
        this.TAG = "MTProto#" + this.INSTANCE_INDEX;
        this.config = config;
        this.exponentalBackoff = new ExponentalBackoff(this.TAG + "#BackOff");
        this.apiErrorExponentialBackoff = new ApiErrorExponentialBackoff();
        this.state = state;
        this.connectionRate = new TransportRate(state.getAvailableConnections());
        this.callback = callback;
        this.authKey = state.getAuthKey();
        this.authKeyId = substring(SHA1(this.authKey), 12, 8);
        this.protoContext = new MTProtoContext();
        this.protoContext.build(this.config);
        this.desiredConnectionCount = connectionsCount;
        this.session = Entropy.getInstance().generateSeed(8);
        this.tcpListener = new TcpListener();
        this.scheduler = new Scheduller(this, callWrapper);
        this.schedullerThread = new SchedullerThread();
        this.config.getExecutor().submit(this.schedullerThread);
        this.responseProcessor = new ResponseProcessor();
        this.config.getExecutor().submit(this.responseProcessor);
        this.connectionFixerThread = new ConnectionFixerThread();
        this.config.getExecutor().submit(this.connectionFixerThread);
    }

    public static int readInt(byte[] src) {
        return readInt(src, 0);
    }

    public static int readInt(byte[] src, int offset) {
        int a = src[offset + 0] & 0xFF;
        int b = src[offset + 1] & 0xFF;
        int c = src[offset + 2] & 0xFF;
        int d = src[offset + 3] & 0xFF;

        return a + (b << 8) + (c << 16) + (d << 24);
    }

    public void resetNetworkBackoff() {
        this.exponentalBackoff.reset();
    }

    public void reloadConnectionInformation() {
        this.connectionRate = new TransportRate(this.state.getAvailableConnections());
    }

    public int getInstanceIndex() {
        return this.INSTANCE_INDEX;
    }

    @Override
    public String toString() {
        return "mtproto#" + this.INSTANCE_INDEX;
    }

    public void close() {
        if (!this.isClosed) {
            this.isClosed = true;
            if (this.connectionFixerThread != null) {
                this.connectionFixerThread.interrupt();
            }
            if (this.schedullerThread != null) {
                this.schedullerThread.interrupt();
            }
            if (this.responseProcessor != null) {
                this.responseProcessor.interrupt();
            }
            closeConnections();
        }
    }

    public boolean isClosed() {
        return this.isClosed;
    }

    public void closeConnections() {
        synchronized (this.contexts) {
            for (TcpContext context : this.contexts) {
                context.suspendConnection(true);
                this.scheduler.onConnectionDies(context.getContextId());
            }
            this.contexts.clear();
            this.contexts.notifyAll();
        }
    }

    private boolean needProcessing(long messageId) {
        synchronized (this.receivedMessages) {
            if (this.receivedMessages.contains(messageId)) {
                return false;
            }

            if (this.receivedMessages.size() > MESSAGES_CACHE_MIN) {
                if (!receivedMessages.stream().anyMatch(x -> messageId > x)) {
                    return false;
                }
            }

            while (this.receivedMessages.size() >= (MESSAGES_CACHE - 1)) {
                this.receivedMessages.remove(0);
            }
            this.receivedMessages.add(messageId);
        }

        return true;
    }

    public void forgetMessage(int id) {
        this.scheduler.forgetMessage(id);
    }

    public int sendRpcMessage(TLMethod request, long timeout, boolean highPriority) {
        return sendMessage(request, timeout, true, highPriority);
    }

    public int sendMessage(TLObject request, long timeout, boolean isRpc, boolean highPriority) {
        final int id = this.scheduler.postMessage(request, isRpc, timeout, highPriority);
        Logger.d(this.TAG, "sendMessage #" + id + " " + request.toString());
        synchronized (this.scheduler) {
            this.scheduler.notifyAll();
        }

        return id;
    }

    private void onMTMessage(MTMessage mtMessage) {
        if ((this.futureSaltsRequestedTime - System.nanoTime()) > (FUTURE_TIMEOUT * 1000L)) {
            Logger.d(this.TAG, "Salt check timeout");
            final int count = this.state.maximumCachedSalts(getUnixTime(mtMessage.getMessageId()));
            if (count < FUTURE_MINIMAL) {
                Logger.d(this.TAG, "Too fiew actual salts: " + count + ", requesting news");
                this.scheduler.postMessage(new MTGetFutureSalts(FUTURE_REQUEST_COUNT), false, FUTURE_TIMEOUT);
                this.futureSaltsRequestedTime = System.nanoTime();
            }
        }

        if ((mtMessage.getSeqNo() % 2) == 1) {
            this.scheduler.confirmMessage(mtMessage.getMessageId());
        }
        if (!needProcessing(mtMessage.getMessageId())) {
            if (Logger.LOG_IGNORED) {
                Logger.d(this.TAG, "Ignoring messages #" + mtMessage.getMessageId());
            }
            return;
        }
        try {
            final TLObject intMessage = this.protoContext.deserializeMessage(new ByteArrayInputStream(mtMessage.getContent()));
            onMTProtoMessage(mtMessage.getMessageId(), intMessage);
        } catch (DeserializeException e) {
            onApiMessage(mtMessage.getContent());
        } catch (IOException e) {
            Logger.e(this.TAG, e);
            // ???
        }
    }

    private void onApiMessage(byte[] data) {
        this.callback.onApiMessage(data, this);
    }

    private void onMTProtoMessage(long msgId, TLObject object) {
        Logger.d(this.TAG, "MTProtoMessage: " + object.toString());

        if (object instanceof MTBadMessage) {
            MTBadMessage badMessage = (MTBadMessage) object;
            Logger.d(this.TAG, "BadMessage: " + badMessage.getErrorCode() + " #" + badMessage.getBadMsgId());
            this.scheduler.confirmMessage(badMessage.getBadMsgId());
            this.scheduler.onMessageConfirmed(badMessage.getBadMsgId());
            long time = this.scheduler.getMessageIdGenerationTime(badMessage.getBadMsgId());
            if (time != 0) {
                long delta = System.nanoTime() / 1000000 - time;
                switch (badMessage.getErrorCode()) {
                    case ERROR_MSG_ID_TOO_BIG:
                    case ERROR_MSG_ID_TOO_SMALL:
                        TimeOverlord.getInstance().onForcedServerTimeArrived((msgId >> 32) * 1000, delta);
                        if (badMessage.getErrorCode() == ERROR_MSG_ID_TOO_BIG) {
                            this.scheduler.resetMessageId();
                        }       this.scheduler.resendAsNewMessage(badMessage.getBadMsgId());
                        requestSchedule();
                        break;
                    case ERROR_SEQ_NO_TOO_BIG:
                    case ERROR_SEQ_NO_TOO_SMALL:
                        if (this.scheduler.isMessageFromCurrentGeneration(badMessage.getBadMsgId())) {
                            Logger.d(this.TAG, "Resetting session");
                            this.session = Entropy.getInstance().generateSeed(8);
                            this.scheduler.resetSession();
                        }   this.scheduler.resendAsNewMessage(badMessage.getBadMsgId());
                        requestSchedule();
                        break;
                    case ERROR_BAD_SERVER_SALT:
                        long salt = badMessage.getNewServerSalt();
                        // Sync time
                        TimeOverlord.getInstance().onMethodExecuted(badMessage.getBadMsgId(), msgId, delta);
                        this.state.badServerSalt(salt);
                        Logger.d(this.TAG, "Reschedule messages because bad_server_salt #" + badMessage.getBadMsgId());
                        this.scheduler.resendAsNewMessage(badMessage.getBadMsgId());
                        requestSchedule();
                        break;
                    case ERROR_BAD_CONTAINER:
                    case ERROR_CONTAINER_MSG_ID_INCORRECT:
                        this.scheduler.resendMessage(badMessage.getBadMsgId());
                        requestSchedule();
                        break;
                    case ERROR_TOO_OLD:
                        this.scheduler.resendAsNewMessage(badMessage.getBadMsgId());
                        requestSchedule();
                        break;
                    default:
                        if (Logger.LOG_IGNORED) {
                            Logger.d(this.TAG, "Ignored BadMsg #" + badMessage.getErrorCode() + " (" + badMessage.getBadMsgId() + ", " + badMessage.getBadMsqSeqno() + ")");
                        }
                        this.scheduler.forgetMessageByMsgId(badMessage.getBadMsgId());
                        break;
                }
            } else {
                if (Logger.LOG_IGNORED) {
                    Logger.d(this.TAG, "Unknown package #" + badMessage.getBadMsgId());
                }
            }
        } else if (object instanceof MTMsgsAck) {
            MTMsgsAck ack = (MTMsgsAck) object;
            String log = "";
            for (Long ackMsgId : ack.getMessages()) {
                this.scheduler.onMessageConfirmed(ackMsgId);
                if (log.length() > 0) {
                    log += ", ";
                }
                log += ackMsgId;
                int id = this.scheduler.mapSchedullerId(ackMsgId);
                if (id > 0) {
                    this.callback.onConfirmed(id);
                }
            }
            Logger.d(this.TAG, "msgs_ack: " + log);
        } else if (object instanceof MTRpcResult) {
            MTRpcResult result = (MTRpcResult) object;

            Logger.d(this.TAG, "rpc_result: " + result.getMessageId());

            int id = this.scheduler.mapSchedullerId(result.getMessageId());
            if (id > 0) {
                int responseConstructor = readInt(result.getContent());
                if (responseConstructor == MTRpcError.CLASS_ID) {
                    try {
                        MTRpcError error = (MTRpcError) this.protoContext.deserializeMessage(result.getContent());
                        BytesCache.getInstance().put(result.getContent());

                        if (error.getErrorCode() == 420) {
                            if (error.getErrorTag().startsWith("FLOOD_WAIT_")) {
                                // Secs
                                int delay = Integer.parseInt(error.getErrorTag().substring("FLOOD_WAIT_".length()));
                                Logger.w(this.TAG, error.getErrorTag());
                                if (delay <= MAX_INTERNAL_FLOOD_WAIT) {
                                    this.scheduler.resendAsNewMessageDelayed(result.getMessageId(), delay * 1000);
                                    requestSchedule();
                                    return;
                                }
                            }
                        }
                        if (error.getErrorCode() == 401) {
                            if (error.getErrorTag().equals("AUTH_KEY_UNREGISTERED")
                                    || error.getErrorTag().equals("AUTH_KEY_INVALID")
                                    || error.getErrorTag().equals("USER_DEACTIVATED")
                                    || error.getErrorTag().equals("SESSION_REVOKED")
                                    || error.getErrorTag().equals("SESSION_EXPIRED")) {
                                Logger.e(this.TAG, "Auth key invalidated");
                                this.callback.onAuthInvalidated(this);
                                close();
                                return;
                            }
                        }

                        if (error.getErrorCode() == 500) {
                            Logger.w(this.TAG, error.getErrorTag());
                            long delay = this.apiErrorExponentialBackoff.nextBackOffMillis();
                            this.scheduler.resendAsNewMessageDelayed(result.getMessageId(), delay);
                            requestSchedule();
                            return;
                        }

                        this.callback.onRpcError(id, error.getErrorCode(), error.getMessage(), this);
                        this.scheduler.forgetMessage(id);
                    } catch (IOException e) {
                        Logger.e(this.TAG, e);
                        return;
                    }
                } else {
                    Logger.d(this.TAG, "rpc_result: " + result.getMessageId() + " #" + Integer.toHexString(responseConstructor));
                    this.apiErrorExponentialBackoff.reset();
                    this.callback.onRpcResult(id, result.getContent(), this);
                    BytesCache.getInstance().put(result.getContent());
                    this.scheduler.forgetMessage(id);
                }
            } else {
                if (Logger.LOG_IGNORED) {
                    Logger.d(this.TAG, "ignored rpc_result: " + result.getMessageId());
                }
                BytesCache.getInstance().put(result.getContent());
            }
            this.scheduler.confirmMessage(result.getMessageId());
            this.scheduler.onMessageConfirmed(result.getMessageId());
            long time = this.scheduler.getMessageIdGenerationTime(result.getMessageId());
            if (time != 0) {
                long delta = System.nanoTime() / 1000000 - time;
                TimeOverlord.getInstance().onMethodExecuted(result.getMessageId(), msgId, delta);
            }
        } else if (object instanceof MTPong) {
            final MTPong pong = (MTPong) object;
            if (Logger.LOG_PING) {
                Logger.d(this.TAG, "pong: " + pong.getPingId());
            }
            this.scheduler.onMessageConfirmed(pong.getMessageId());
            this.scheduler.forgetMessageByMsgId(pong.getMessageId());
            final long time = this.scheduler.getMessageIdGenerationTime(pong.getMessageId());
            if (time != 0) {
                final long delta = (System.nanoTime() / 1000000) - time;
                TimeOverlord.getInstance().onMethodExecuted(pong.getMessageId(), msgId, delta);
            }
        } else if (object instanceof MTFutureSalts) {
            final MTFutureSalts salts = (MTFutureSalts) object;
            this.scheduler.onMessageConfirmed(salts.getRequestId());
            this.scheduler.forgetMessageByMsgId(salts.getRequestId());

            final long time = this.scheduler.getMessageIdGenerationTime(salts.getRequestId());

            if (time > 0) {
                final KnownSalt[] knownSalts = new KnownSalt[salts.getSalts().size()];
                for (int i = 0; i < knownSalts.length; i++) {
                    final MTFutureSalt salt = salts.getSalts().get(i);
                    knownSalts[i] = new KnownSalt(salt.getValidSince(), salt.getValidUntil(), salt.getSalt());
                }

                final long delta = (System.nanoTime() / 1000000) - time;
                TimeOverlord.getInstance().onForcedServerTimeArrived(salts.getNow(), delta);
                this.state.mergeKnownSalts(salts.getNow(), knownSalts);
            }
        } else if (object instanceof MTMessageDetailedInfo) {
            final MTMessageDetailedInfo detailedInfo = (MTMessageDetailedInfo) object;
            Logger.d(this.TAG, "msg_detailed_info: " + detailedInfo.getMsgId() + ", answer: " + detailedInfo.getAnswerMsgId());
            if (this.receivedMessages.contains(detailedInfo.getAnswerMsgId())) {
                this.scheduler.confirmMessage(detailedInfo.getAnswerMsgId());
            } else {
                int id = this.scheduler.mapSchedullerId(detailedInfo.getMsgId());
                if (id > 0) {
                    this.scheduler.postMessage(new MTNeedResendMessage(new long[]{detailedInfo.getAnswerMsgId()}), false, RESEND_TIMEOUT);
                } else {
                    this.scheduler.confirmMessage(detailedInfo.getAnswerMsgId());
                    this.scheduler.forgetMessageByMsgId(detailedInfo.getMsgId());
                }
            }
        } else if (object instanceof MTNewMessageDetailedInfo) {
            MTNewMessageDetailedInfo detailedInfo = (MTNewMessageDetailedInfo) object;
            Logger.d(this.TAG, "msg_new_detailed_info: " + detailedInfo.getAnswerMsgId());
            if (this.receivedMessages.contains(detailedInfo.getAnswerMsgId())) {
                this.scheduler.confirmMessage(detailedInfo.getAnswerMsgId());
            } else {
                this.scheduler.postMessage(new MTNeedResendMessage(new long[]{detailedInfo.getAnswerMsgId()}), false, RESEND_TIMEOUT);
            }
        } else if (object instanceof MTNewSessionCreated) {
            MTNewSessionCreated newSessionCreated = (MTNewSessionCreated) object;
            if (!newSessionsIds.contains(newSessionCreated.getUniqId())) {
                KnownSalt[] knownSalts = new KnownSalt[1];
                int validSince = (int) System.currentTimeMillis() / 1000;
                knownSalts[0] = new KnownSalt(validSince, validSince + 30 * 60, ((MTNewSessionCreated) object).getServerSalt());
                this.state.mergeKnownSalts(validSince, knownSalts);
            }
            this.scheduler.updateMessageId(((MTNewSessionCreated) object).getFirstMsgId());
            this.callback.onSessionCreated(this);
        } else {
            this.scheduler.onMessageConfirmed(msgId);
            if (Logger.LOG_IGNORED) {
                Logger.w(this.TAG, "Ignored MTProto message " + object.toString());
            }
        }
    }

    private void internalSchedule() {
        long time = System.nanoTime() / 1000000;
        if (time - this.lastPingTime > PING_INTERVAL_REQUEST) {
            this.lastPingTime = time;
            synchronized (this.contexts) {
                for (TcpContext context : this.contexts) {
                    this.scheduler.postMessageDelayed(
                            new MTPingDelayDisconnect(Entropy.getInstance().generateRandomId(), PING_INTERVAL),
                            false, PING_INTERVAL_REQUEST, 0, context.getContextId(), false);
                }
            }
        }
    }

    public void requestSchedule() {
        synchronized (this.scheduler) {
            this.scheduler.notifyAll();
        }
    }

    private EncryptedMessage encrypt(int seqNo, long messageId, byte[] content) throws IOException {
        long salt = this.state.findActualSalt((int) (TimeOverlord.getInstance().getServerTime() / 1000));
        ByteArrayOutputStream messageBody = new ByteArrayOutputStream();
        writeLong(salt, messageBody);
        writeByteArray(this.session, messageBody);
        writeLong(messageId, messageBody);
        writeInt(seqNo, messageBody);
        writeInt(content.length, messageBody);
        writeByteArray(content, messageBody);

        byte[] innerData = messageBody.toByteArray();
        byte[] msgKey = substring(SHA1(innerData), 4, 16);
        int fastConfirm = readInt(SHA1(innerData)) | (1 << 31);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        writeByteArray(this.authKeyId, out);
        writeByteArray(msgKey, out);

        byte[] sha1_a = SHA1(msgKey, substring(this.authKey, 0, 32));
        byte[] sha1_b = SHA1(substring(this.authKey, 32, 16), msgKey, substring(this.authKey, 48, 16));
        byte[] sha1_c = SHA1(substring(this.authKey, 64, 32), msgKey);
        byte[] sha1_d = SHA1(msgKey, substring(this.authKey, 96, 32));

        byte[] aesKey = concat(substring(sha1_a, 0, 8), substring(sha1_b, 8, 12), substring(sha1_c, 4, 12));
        byte[] aesIv = concat(substring(sha1_a, 8, 12), substring(sha1_b, 0, 8), substring(sha1_c, 16, 4), substring(sha1_d, 0, 8));

        byte[] encoded = AES256IGEEncrypt(align(innerData, 16), aesIv, aesKey);
        writeByteArray(encoded, out);
        EncryptedMessage res = new EncryptedMessage();
        res.data = out.toByteArray();
        res.fastConfirm = fastConfirm;
        return res;
    }

    private byte[] optimizedSHA(byte[] serverSalt, byte[] session, long msgId, int seq, int len, byte[] data, int datalen) {
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(serverSalt);
            crypt.update(session);
            crypt.update(longToBytes(msgId));
            crypt.update(intToBytes(seq));
            crypt.update(intToBytes(len));
            crypt.update(data, 0, datalen);
            return crypt.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    private MTMessage decrypt(byte[] data, int offset, int len) throws IOException {
        final ByteArrayInputStream stream = new ByteArrayInputStream(data);
        stream.skip(offset);
        final byte[] msgAuthKey = readBytes(8, stream);
        for (int i = 0; i < this.authKeyId.length; i++) {
            if (msgAuthKey[i] != this.authKeyId[i]) {
                Logger.e(this.TAG, "Unsupported msgAuthKey");
                throw new SecurityException();
            }
        }
        final byte[] msgKey = readBytes(16, stream);

        final byte[] sha1_a = SHA1(msgKey, substring(this.authKey, 8, 32));
        final byte[] sha1_b = SHA1(substring(this.authKey, 40, 16), msgKey, substring(this.authKey, 56, 16));
        final byte[] sha1_c = SHA1(substring(this.authKey, 72, 32), msgKey);
        final byte[] sha1_d = SHA1(msgKey, substring(this.authKey, 104, 32));

        final byte[] aesKey = concat(substring(sha1_a, 0, 8), substring(sha1_b, 8, 12), substring(sha1_c, 4, 12));
        final byte[] aesIv = concat(substring(sha1_a, 8, 12), substring(sha1_b, 0, 8), substring(sha1_c, 16, 4), substring(sha1_d, 0, 8));

        final int totalLen = len - 8 - 16;
        final byte[] encMessage = BytesCache.getInstance().allocate(totalLen);
        readBytes(encMessage, 0, totalLen, stream);

        final byte[] rawMessage = BytesCache.getInstance().allocate(totalLen);
        final long decryptStart = System.currentTimeMillis();
        AES256IGEDecryptBig(encMessage, rawMessage, totalLen, aesIv, aesKey);
        Logger.d(this.TAG, "Decrypted in " + (System.currentTimeMillis() - decryptStart) + " ms");
        BytesCache.getInstance().put(encMessage);

        final ByteArrayInputStream bodyStream = new ByteArrayInputStream(rawMessage);
        final byte[] serverSalt = readBytes(8, bodyStream);
        final byte[] session = readBytes(8, bodyStream);
        final long messageId = readLong(bodyStream);
        final int mes_seq = StreamingUtils.readInt(bodyStream);

        final int msg_len = StreamingUtils.readInt(bodyStream);

        final int bodySize = totalLen - 32;

        if ((msg_len % 4) != 0) {
            throw new SecurityException("Message length is not multiple of 4");
        }

        if (msg_len > bodySize) {
            throw new SecurityException("Message length is longer than body size");
        }

        if ((msg_len - bodySize) > 15) {
            throw new SecurityException("Message length is more than 15 bytes longer than body size");
        }

        final byte[] message = BytesCache.getInstance().allocate(msg_len);
        readBytes(message, 0, msg_len, bodyStream);

        BytesCache.getInstance().put(rawMessage);

        final byte[] checkHash = optimizedSHA(serverSalt, session, messageId, mes_seq, msg_len, message, msg_len);

        if (!arrayEq(substring(checkHash, 4, 16), msgKey)) {
            throw new SecurityException();
        }

        if (!arrayEq(session, this.session)) {
            return null;
        }

        if (TimeOverlord.getInstance().getTimeAccuracy() < 10) {
            final long time = (messageId >> 32);
            final long serverTime = TimeOverlord.getInstance().getServerTime() / 1000;

            if ((serverTime + 30) < time) {
                Logger.e(this.TAG, "1. Incorrect message (" + messageId + ") time: " + time + " with server time: " + serverTime);
                // return null;
            }

            if (time < (serverTime - 300)) {
                Logger.e(this.TAG, "2. Incorrect message (" + messageId + ") time: " + time + " with server time: " + serverTime);
                // return null;
            }
        }

        return new MTMessage(messageId, mes_seq, message, message.length);
    }

    private class SchedullerThread extends Thread {
        private SchedullerThread() {
            setName("Scheduller#" + hashCode());
        }

        @Override
        public void run() {
            setPriority(Thread.MIN_PRIORITY);
            PrepareSchedule prepareSchedule = new PrepareSchedule();
            while (!MTProto.this.isClosed) {
                if (Logger.LOG_THREADS) {
                    Logger.d(MTProto.this.TAG, "Scheduller Iteration");
                }

                int[] contextIds;
                synchronized (MTProto.this.contexts) {
                    TcpContext[] currentContexts = MTProto.this.contexts.toArray(new TcpContext[0]);
                    contextIds = new int[currentContexts.length];
                    for (int i = 0; i < contextIds.length; i++) {
                        contextIds[i] = currentContexts[i].getContextId();
                    }
                }

                synchronized (MTProto.this.scheduler) {
                    MTProto.this.scheduler.prepareScheduller(prepareSchedule, contextIds);
                    if (prepareSchedule.isDoWait()) {
                        if (Logger.LOG_THREADS) {
                            Logger.d(MTProto.this.TAG, "Scheduller:wait " + prepareSchedule.getDelay());
                        }
                        try {
                            MTProto.this.scheduler.wait(Math.min(prepareSchedule.getDelay(), 30000));
                        } catch (InterruptedException e) {
                            Logger.e(MTProto.this.TAG, e);
                            return;
                        }
                        internalSchedule();
                        continue;
                    }
                }

                TcpContext context = null;
                synchronized (MTProto.this.contexts) {
                    TcpContext[] currentContexts = MTProto.this.contexts.toArray(new TcpContext[0]);
                    outer:
                    for (int i = 0; i < currentContexts.length; i++) {
                        int index = (i + MTProto.this.roundRobin + 1) % currentContexts.length;
                        for (int allowed : prepareSchedule.getAllowedContexts()) {
                            if (currentContexts[index].getContextId() == allowed) {
                                context = currentContexts[index];
                                break outer;
                            }
                        }

                    }

                    if (currentContexts.length != 0) {
                        MTProto.this.roundRobin = (MTProto.this.roundRobin + 1) % currentContexts.length;
                    }
                }

                if (context == null) {
                    if (Logger.LOG_THREADS) {
                        Logger.d(MTProto.this.TAG, "Scheduller: no context");
                    }
                    continue;
                }

                if (Logger.LOG_THREADS) {
                    Logger.d(MTProto.this.TAG, "doSchedule");
                }

                internalSchedule();
                synchronized (MTProto.this.scheduler) {
                    long start = System.currentTimeMillis();
                    PreparedPackage preparedPackage = MTProto.this.scheduler.doSchedule(context.getContextId(), MTProto.this.initedContext.contains(context.getContextId()));
                    if (Logger.LOG_THREADS) {
                        Logger.d(MTProto.this.TAG, "Schedulled in " + (System.currentTimeMillis() - start) + " ms");
                    }
                    if (preparedPackage == null) {
                        continue;
                    }

                    if (Logger.LOG_THREADS) {
                        Logger.d(MTProto.this.TAG, "MessagePushed (#" + context.getContextId() + "): time:" + getUnixTime(preparedPackage.getMessageId()));
                        Logger.d(MTProto.this.TAG, "MessagePushed (#" + context.getContextId() + "): seqNo:" + preparedPackage.getSeqNo() + ", msgId" + preparedPackage.getMessageId());
                    }

                    try {
                        EncryptedMessage msg = encrypt(preparedPackage.getSeqNo(), preparedPackage.getMessageId(), preparedPackage.getContent());
                        if (preparedPackage.isHighPriority()) {
                            MTProto.this.scheduler.registerFastConfirm(preparedPackage.getMessageId(), msg.fastConfirm);
                        }
                        context.postMessage(msg.data, preparedPackage.isHighPriority());
                        MTProto.this.initedContext.add(context.getContextId());
                    } catch (IOException e) {
                        Logger.e(MTProto.this.TAG, e);
                    }
                }
            }
        }
    }

    private class ResponseProcessor extends Thread {
        public ResponseProcessor() {
            setName("ResponseProcessor#" + hashCode());
        }

        @Override
        public void run() {
            setPriority(Thread.MIN_PRIORITY);
            while (!MTProto.this.isClosed) {
                if (Logger.LOG_THREADS) {
                    Logger.d(MTProto.this.TAG, "Response Iteration");
                }
                synchronized (MTProto.this.inQueue) {
                    if (MTProto.this.inQueue.isEmpty()) {
                        try {
                            MTProto.this.inQueue.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    if (MTProto.this.inQueue.isEmpty()) {
                        continue;
                    }
                }
                MTMessage message = MTProto.this.inQueue.poll();
                onMTMessage(message);
                BytesCache.getInstance().put(message.getContent());
            }
        }
    }

    private class ConnectionFixerThread extends Thread {
        private ConnectionFixerThread() {
            setName("ConnectionFixerThread#" + hashCode());
        }

        @Override
        public void run() {
            setPriority(Thread.MIN_PRIORITY);
            while (!MTProto.this.isClosed) {
                if (Logger.LOG_THREADS) {
                    Logger.d(MTProto.this.TAG, "Connection Fixer Iteration");
                }
                synchronized (MTProto.this.contexts) {
                    if (MTProto.this.contexts.size() >= MTProto.this.desiredConnectionCount) {
                        try {
                            MTProto.this.contexts.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }

                ConnectionType type = MTProto.this.connectionRate.tryConnection();
                TcpContext context = new TcpContext(MTProto.this, type.getHost(), type.getPort(), MTProto.this.tcpListener);
                context.connect();
                if (MTProto.this.isClosed) {
                    return;
                }
                MTProto.this.scheduler.postMessageDelayed(new MTPing(Entropy.getInstance().generateRandomId()), false, PING_TIMEOUT, 0, context.getContextId(), false);
                synchronized (MTProto.this.contexts) {
                    MTProto.this.contexts.add(context);
                    MTProto.this.contextConnectionId.put(context.getContextId(), type.getId());
                }
                synchronized (MTProto.this.scheduler) {
                    MTProto.this.scheduler.notifyAll();
                }
            }
        }
    }

    private class TcpListener implements TcpContextCallback {

        @Override
        public void onRawMessage(byte[] data, int offset, int len, TcpContext context) {
            if (MTProto.this.isClosed) {
                return;
            }
            try {
                MTMessage decrypted = decrypt(data, offset, len);
                if (decrypted == null) {
                    Logger.d(MTProto.this.TAG, "message ignored");
                    return;
                }
                if (!MTProto.this.connectedContexts.contains(context.getContextId())) {
                    MTProto.this.connectedContexts.add(context.getContextId());
                    MTProto.this.exponentalBackoff.onSuccess();
                    MTProto.this.connectionRate.onConnectionSuccess(MTProto.this.contextConnectionId.get(context.getContextId()));
                }

                Logger.d(MTProto.this.TAG, "MessageArrived (#" + context.getContextId() + "): time: " + getUnixTime(decrypted.getMessageId()));
                Logger.d(MTProto.this.TAG, "MessageArrived (#" + context.getContextId() + "): seqNo: " + decrypted.getSeqNo() + ", msgId:" + decrypted.getMessageId());
                int messageClass = readInt(decrypted.getContent());
                if (messageClass == MTMessagesContainer.CLASS_ID) {
                    try {
                        TLObject object = MTProto.this.protoContext.deserializeMessage(new ByteArrayInputStream(decrypted.getContent()));
                        if (object instanceof MTMessagesContainer) {
                            for (MTMessage mtMessage : ((MTMessagesContainer) object).getMessages()) {
                                MTProto.this.inQueue.add(mtMessage);
                            }
                            synchronized (MTProto.this.inQueue) {
                                MTProto.this.inQueue.notifyAll();
                            }
                        }
                        BytesCache.getInstance().put(decrypted.getContent());
                    } catch (DeserializeException e) {
                        // Ignore this
                        Logger.e(MTProto.this.TAG, e);
                    }
                } else if (messageClass == MTMessageCopy.CLASS_ID) {
                    Logger.d(TAG, "On msg copy");
                    try {
                        TLObject object = MTProto.this.protoContext.deserializeMessage(new ByteArrayInputStream(decrypted.getContent()));
                        MTMessageCopy messageCopy = (MTMessageCopy) object;
                        MTProto.this.scheduler.confirmMessage(decrypted.getMessageId());
                        MTProto.this.inQueue.add(messageCopy.getOrig_message());
                        synchronized (MTProto.this.inQueue) {
                            MTProto.this.inQueue.notifyAll();
                        }
                    } catch (DeserializeException e) {
                        Logger.e(MTProto.this.TAG, e);
                    }
                } else {
                    MTProto.this.inQueue.add(decrypted);
                    synchronized (MTProto.this.inQueue) {
                        MTProto.this.inQueue.notifyAll();
                    }
                }
            } catch (IOException e) {
                Logger.e(MTProto.this.TAG, e);
                synchronized (MTProto.this.contexts) {
                    context.suspendConnection(true);
                    if (!MTProto.this.connectedContexts.contains(context.getContextId())) {
                        MTProto.this.exponentalBackoff.onFailureNoWait();
                        MTProto.this.connectionRate.onConnectionFailure(MTProto.this.contextConnectionId.get(context.getContextId()));
                    }
                    MTProto.this.contexts.remove(context);
                    MTProto.this.contexts.notifyAll();
                    MTProto.this.scheduler.onConnectionDies(context.getContextId());
                }
            }
        }

        @Override
        public void onError(int errorCode, TcpContext context) {
            if (MTProto.this.isClosed) {
                return;
            }

            Logger.e(MTProto.this.TAG, "OnError (#" + context.getContextId() + "): " + errorCode);
            context.suspendConnection(true);
            context.connect();
            // Fully maintained at transport level: TcpContext dies
        }

        @Override
        public void onChannelBroken(TcpContext context) {
            if (MTProto.this.isClosed) {
                return;
            }
            int contextId = context.getContextId();
            Logger.d(MTProto.this.TAG, "onChannelBroken (#" + contextId + ")");
            synchronized (MTProto.this.contexts) {
                MTProto.this.contexts.remove(context);
                if (!MTProto.this.connectedContexts.contains(contextId)) {
                    if (MTProto.this.contextConnectionId.containsKey(contextId)) {
                        MTProto.this.exponentalBackoff.onFailureNoWait();
                        MTProto.this.connectionRate.onConnectionFailure(MTProto.this.contextConnectionId.get(contextId));
                    }
                }
                MTProto.this.contexts.notifyAll();
            }
            MTProto.this.scheduler.onConnectionDies(context.getContextId());
            requestSchedule();
        }

        @Override
        public void onFastConfirm(int hash) {
            if (MTProto.this.isClosed) {
                return;
            }
            MTProto.this.scheduler.onMessageFastConfirmed(hash);
            int[] ids = MTProto.this.scheduler.mapFastConfirm(hash);
            for (int id : ids) {
                MTProto.this.callback.onConfirmed(id);
            }
        }
    }

    private class EncryptedMessage {
        public byte[] data;
        public int fastConfirm;
    }
}
