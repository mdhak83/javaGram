package org.javagram.client.kernel;

import org.javagram.api.TLApiContext;
import org.javagram.api.TLConfig;
import org.javagram.utils.file.Downloader;
import org.javagram.utils.file.Uploader;
import org.javagram.api.functions.TLRequestInitConnection;
import org.javagram.api.functions.TLRequestInvokeWithLayer;
import org.javagram.mtproto.CallWrapper;
import org.javagram.mtproto.MTProto;
import org.javagram.mtproto.MTProtoCallback;
import org.javagram.mtproto.pq.Authorizer;
import org.javagram.mtproto.pq.PqAuth;
import org.javagram.mtproto.state.ConnectionInfo;
import org.javagram.mtproto.util.BytesCache;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.javagram.MyTLAppConfiguration;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLBoolTrue;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api.auth.base.authorization.TLExportedAuthorization;
import org.javagram.api.auth.functions.TLRequestAuthExportAuthorization;
import org.javagram.api.auth.functions.TLRequestAuthImportAuthorization;
import org.javagram.api.file.base.input.location.TLAbsInputFileLocation;
import org.javagram.api.help.functions.TLRequestHelpGetConfig;
import org.javagram.api.updates.base.TLAbsUpdates;
import org.javagram.api.upload.base.cdn.TLAbsCdnFile;
import org.javagram.api.upload.base.file.TLAbsFile;
import org.javagram.api.upload.functions.TLRequestUploadGetCdnFile;
import org.javagram.api.upload.functions.TLRequestUploadGetFile;
import org.javagram.api.upload.functions.TLRequestUploadSaveBigFilePart;
import org.javagram.api.upload.functions.TLRequestUploadSaveFilePart;
import org.javagram.utils.DefaultApiCallback;
import org.javagram.utils.GzipRequest;
import org.javagram.utils.Logger;
import org.javagram.utils.RpcCallback;
import org.javagram.utils.RpcCallbackEx;
import org.javagram.utils.RpcException;
import org.javagram.utils.TimeoutException;
public class TelegramApi {

    private static final AtomicInteger CURRENT_INSTANCES_NUMBER = new AtomicInteger(1000);
    private final int instanceNumber;
    private final String logtag;
    
    private static final AtomicInteger RPC_CALL_INDEX = new AtomicInteger(0);
    private static final AtomicInteger SENDER_THREAD_COUNT = new AtomicInteger(0);
    private static final AtomicInteger CONNECTION_THREAD_COUNT = new AtomicInteger(0);
    private static final AtomicInteger TIMEOUT_THREAD_COUNT = new AtomicInteger(0);

    private static final int CHANNELS_MAIN = 1;
    private static final int CHANNELS_FS = 2;
    private static final int DEFAULT_TIMEOUT = 15000;
    private static final int FILE_TIMEOUT = 45000;
    private final Map<Integer, MTProto> dcProtos = new HashMap<>();
    private final Map<Integer, Object> dcSync = new HashMap<>();
    private final TreeMap<Integer, Boolean> dcRequired = new TreeMap<>();
    private final Map<Integer, RpcCallbackWrapper> callbacks = new HashMap<>();
    private final Map<Integer, Integer> sentRequests = new HashMap<>();
    private final TreeMap<Long, Integer> timeoutTimes = new TreeMap<>();
    private static final int DEFAULTCOMPETABLETIMEOUTMILLIS = 30000;
    private boolean isClosed;
    private int primaryDc;
    private MTProto mainProto;
    private ProtoCallback callback;
    private final SenderThread senderThread;
    private final TLApiContext apiContext;
    private TimeoutThread timeoutThread;
    private final ConnectionThread dcThread;
    private final HashSet<Integer> registeredInApi = new HashSet<>();

    private MyTLAppConfiguration config;
    private DefaultApiCallback apiCallback;
    private final Downloader downloader;
    private final Uploader uploader;

    /**
     * Instantiates a new Telegram api.
     *
     * @param config     the _ app info
     * @param _apiCallback the _ api callback
     */
    public TelegramApi(MyTLAppConfiguration config, DefaultApiCallback _apiCallback) {
        this.instanceNumber = CURRENT_INSTANCES_NUMBER.incrementAndGet();
        this.logtag = "[TelegramApi#" + this.instanceNumber + "]";

        long start = System.currentTimeMillis();
        this.apiCallback = _apiCallback;
        this.config = config;
        this.primaryDc = this.config.getApiState().getPrimaryDc();
        this.isClosed = false;
        this.callback = new ProtoCallback();
        Logger.d(this.logtag, "Phase 0 in " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        this.apiContext = new TLApiContext() {
            
            @Override
            public TLObject deserializeMessage(int clazzId, InputStream stream) throws IOException {
                return super.deserializeMessage(clazzId, stream);
            }

            @Override
            public TLBytes allocateBytes(int size) {
                return new TLBytes(BytesCache.getInstance().allocate(size), 0, size);
            }

            @Override
            public void releaseBytes(TLBytes unused) {
                BytesCache.getInstance().put(unused.getData());
            }
            
        };
        this.apiContext.build(this.config);

        Logger.d(this.logtag, "Phase 1 in " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        this.timeoutThread = new TimeoutThread();
        this.config.getExecutor().submit(this.timeoutThread);

        this.dcThread = new ConnectionThread();
        this.config.getExecutor().submit(this.dcThread);

        this.senderThread = new SenderThread();
        this.config.getExecutor().submit(this.senderThread);
        Logger.d(this.logtag, "Phase 2 in " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        this.downloader = new Downloader(this);
        this.uploader = new Uploader(this);
        Logger.d(this.logtag, "Phase 3 in " + (System.currentTimeMillis() - start) + " ms");
    }

    /**
     * Gets downloader.
     *
     * @return the downloader
     */
    public Downloader getDownloader() {
        return this.downloader;
    }

    /**
     * Gets uploader.
     *
     * @return the uploader
     */
    public Uploader getUploader() {
        return this.uploader;
    }

    /**
     * Switch to dc.
     *
     * @param dcId the dc id
     */
    public void switchToDc(int dcId) {
        if (this.mainProto != null) {
            this.mainProto.close();
        }
        this.mainProto = null;
        this.primaryDc = dcId;
        this.config.getApiState().setPrimaryDc(dcId);
        synchronized (this.dcRequired) {
            this.dcRequired.notifyAll();
        }
    }

    @Override
    public String toString() {
        return this.logtag;
    }

    private TLMethod wrapForDc(int dcId, TLMethod method) {
        if (this.registeredInApi.contains(dcId)) {
            return new TLRequestInvokeWithLayer(method);
        }

        return new TLRequestInvokeWithLayer(
                new TLRequestInitConnection(this.config.getApiId(), this.config.getDeviceModel(), this.config.getSystemVersion(), this.config.getAppVersion(), this.config.getSystemLangCode(), this.config.getLangPack(), this.config.getLangCode(), method)
        );
    }

    /**
     * Gets api context.
     *
     * @return the api context
     */
    public TLApiContext getApiContext() {
        return this.apiContext;
    }

    /**
     * On message arrived.
     *
     * @param object the object
     */
    protected void onMessageArrived(TLObject object) {
        if (object instanceof TLAbsUpdates) {
            Logger.d(this.logtag, "<< update " + object.toString());
            this.apiCallback.onUpdate((TLAbsUpdates) object);
        } else {
            Logger.d(this.logtag, "<< unknown object " + (object != null ? object.toString() : "null"));
        }
    }

    /**
     * Is closed.
     *
     * @return the boolean
     */
    public boolean isClosed() {
        return this.isClosed;
    }

    /**
     * Close void.
     */
    public void close() {
        if (!this.isClosed) {
            this.apiCallback.onAuthCancelled(this);
            this.isClosed = true;
            if (this.timeoutThread != null) {
                this.timeoutThread.interrupt();
                this.timeoutThread = null;
            }
            this.mainProto.close();
        }
    }

    /**
     * Reset network backoff.
     */
    public void resetNetworkBackoff() {
        if (this.mainProto != null) {
            this.mainProto.resetNetworkBackoff();
        }
        synchronized(this.dcProtos) {
            this.dcProtos.values().forEach((mtProto) -> {
                mtProto.resetNetworkBackoff();
            });
        }
    }

    /**
     * Reset connection info.
     */
    public void resetConnectionInfo() {
        this.mainProto.reloadConnectionInformation();
        synchronized (this.dcProtos) {
            this.dcProtos.values().stream().forEachOrdered(proto -> {
                proto.reloadConnectionInformation();
            });
        }
    }

    // Basic sync and async methods

    private <T extends TLObject> void doRpcCall(TLMethod<T> method, int timeout, RpcCallback<T> callback, int destDc) {
        doRpcCall(method, timeout, callback, destDc, true);
    }

    private <T extends TLObject> void doRpcCall(TLMethod<T> method, int timeout, RpcCallback<T> callback, int destDc,
                                                boolean authRequired) {
        if (this.isClosed) {
            if (callback != null) {
                callback.onError(0, null);
            }
            return;
        }
        int localRpcId = RPC_CALL_INDEX.getAndIncrement();
        synchronized(this.callbacks) {
            RpcCallbackWrapper wrapper = new RpcCallbackWrapper(localRpcId, method, callback);
            wrapper.dcId = destDc;
            wrapper.timeout = timeout;
            wrapper.isAuthRequred = authRequired;

            this.callbacks.put(localRpcId, wrapper);

            if (callback != null) {
                long timeoutTime = System.nanoTime() + timeout * 2 * 1000 * 1000L;
                synchronized (this.timeoutTimes) {
                    while (this.timeoutTimes.containsKey(timeoutTime)) {
                        timeoutTime++;
                    }
                    this.timeoutTimes.put(timeoutTime, localRpcId);
                    this.timeoutTimes.notifyAll();
                }
                wrapper.timeoutTime = timeoutTime;
            }

            if (authRequired) {
                checkDcAuth(destDc);
            } else {
                checkDc(destDc);
            }

            this.callbacks.notifyAll();
        }

        Logger.d(this.logtag, ">> #" + +localRpcId + ": " + method.toString());
    }

    private <T extends TLObject> T doRpcCall(TLMethod<T> method, int timeout, int destDc) throws IOException, java.util.concurrent.TimeoutException {
        return this.doRpcCall(method, timeout, destDc, true);
    }

    private <T extends TLObject> T doRpcCall(TLMethod<T> method, int timeout, int destDc, boolean authRequired) throws RpcException, java.util.concurrent.TimeoutException {
        if (this.isClosed) {
            throw new RpcException(0, "Connection is closed");
        }

        T resultObject = null;
        final CompletableFuture<T> completableFuture = new CompletableFuture<>();

        doRpcCall(method, timeout, new RpcCallback<T>() {
            @Override
            public void onResult(T result) {
                completableFuture.complete(result);
            }

            @Override
            public void onError(int errorCode, String message) {
                completableFuture.completeExceptionally(new RpcException(errorCode, message));
            }
        }, destDc, authRequired);


        try {
            resultObject = completableFuture.get(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Logger.w(logtag, method.toString() + " : " + e.getMessage());
//            Logger.e(logtag, e);
        } catch (java.util.concurrent.TimeoutException e) {
            Logger.w(logtag, method.toString() + " : " + e.getMessage());
            //Logger.e(logtag, e);
            throw e;
        } catch (ExecutionException e) {
            Logger.w(logtag, method.toString() + " : " + e.getMessage());
            //Logger.e(logtag, e);
            if (e.getCause() instanceof RpcException) {
                throw (RpcException) e.getCause();
            }
        }

        return resultObject;
    }

    /**
     * Do rpc call weak.
     *
     * @param <T>    the type parameter
     * @param method the method
     */
// Public async methods
    public <T extends TLObject> void doRpcCallWeak(TLMethod<T> method) {
        doRpcCallWeak(method, DEFAULT_TIMEOUT);
    }

    /**
     * Do rpc call weak.
     *
     * @param <T>     the type parameter
     * @param method  the method
     * @param timeout the timeout
     */
    public <T extends TLObject> void doRpcCallWeak(TLMethod<T> method, int timeout) {
        doRpcCall(method, timeout, null);
    }

    /**
     * Do rpc call.
     *
     * @param <T>      the type parameter
     * @param method   the method
     * @param callback the callback
     */
    public <T extends TLObject> void doRpcCall(TLMethod<T> method, RpcCallback<T> callback) {
        doRpcCall(method, DEFAULT_TIMEOUT, callback);
    }

    /**
     * Do rpc call.
     *
     * @param <T>      the type parameter
     * @param method   the method
     * @param timeout  the timeout
     * @param callback the callback
     */
    public <T extends TLObject> void doRpcCall(TLMethod<T> method, int timeout, RpcCallback<T> callback) {
        doRpcCall(method, timeout, callback, 0);
    }

    // Public sync methods

    /**
     * Do rpc call.
     *
     * @param <T>    the type parameter
     * @param method the method
     * @return the t
     * @throws IOException the iO exception
     * @throws java.util.concurrent.TimeoutException
     */
    public <T extends TLObject> T doRpcCall(TLMethod<T> method) throws IOException, java.util.concurrent.TimeoutException {
        return doRpcCall(method, DEFAULTCOMPETABLETIMEOUTMILLIS);
    }

    /**
     * Do rpc call.
     *
     * @param <T>     the type parameter
     * @param method  the method
     * @param timeout the timeout
     * @return the t
     * @throws IOException the iO exception
     * @throws java.util.concurrent.TimeoutException
     */
    public <T extends TLObject> T doRpcCall(TLMethod<T> method, int timeout) throws IOException, java.util.concurrent.TimeoutException {
        return doRpcCall(method, timeout, 0);
    }

    /**
     * Do rpc call side.
     *
     * @param <T>    the type parameter
     * @param method the method
     * @return the t
     * @throws IOException the iO exception
     * @throws java.util.concurrent.TimeoutException
     */
    public <T extends TLObject> T doRpcCallSide(TLMethod<T> method) throws IOException, java.util.concurrent.TimeoutException {
        return doRpcCall(method, DEFAULTCOMPETABLETIMEOUTMILLIS, this.primaryDc, true);
    }

    /**
     * Do rpc call side.
     *
     * @param <T>     the type parameter
     * @param method  the method
     * @param timeout the timeout
     * @return the t
     * @throws IOException the iO exception
     * @throws java.util.concurrent.TimeoutException
     */
    public <T extends TLObject> T doRpcCallSide(TLMethod<T> method, int timeout) throws IOException, java.util.concurrent.TimeoutException {
        return doRpcCall(method, timeout, this.primaryDc, true);
    }

    /**
     * Do rpc call side gzip.
     *
     * @param <T>     the type parameter
     * @param method  the method
     * @param timeout the timeout
     * @return the t
     * @throws IOException the iO exception
     * @throws java.util.concurrent.TimeoutException
     */
    public <T extends TLObject> T doRpcCallSideGzip(TLMethod<T> method, int timeout) throws IOException, java.util.concurrent.TimeoutException {
        return doRpcCall(new GzipRequest<>(method), timeout, this.primaryDc, true);
    }

    /**
     * Do rpc call gzip.
     *
     * @param <T>     the type parameter
     * @param method  the method
     * @param timeout the timeout
     * @return the t
     * @throws IOException the iO exception
     * @throws java.util.concurrent.TimeoutException
     */
    public <T extends TLObject> T doRpcCallGzip(TLMethod<T> method, int timeout) throws IOException, java.util.concurrent.TimeoutException {
        return doRpcCall(new GzipRequest<>(method), timeout, 0);
    }

    /**
     * Do rpc call non auth.
     *
     * @param <T>    the type parameter
     * @param method the method
     * @return the t
     * @throws org.javagram.utils.RpcException
     * @throws java.util.concurrent.TimeoutException
     */
    public <T extends TLObject> T doRpcCallNonAuth(TLMethod<T> method) throws RpcException, java.util.concurrent.TimeoutException {
        return doRpcCallNonAuth(method, DEFAULTCOMPETABLETIMEOUTMILLIS, this.primaryDc);
    }

    /**
     * Do rpc call non auth.
     *
     * @param <T>    the type parameter
     * @param method the method
     * @param dcId   the dc id
     * @return the t
     * @throws IOException the iO exception
     * @throws java.util.concurrent.TimeoutException
     */
    public <T extends TLObject> T doRpcCallNonAuth(TLMethod<T> method, int dcId) throws IOException, java.util.concurrent.TimeoutException {
        return doRpcCallNonAuth(method, DEFAULTCOMPETABLETIMEOUTMILLIS, dcId);
    }

    /**
     * Do rpc call non auth.
     *
     * @param <T>     the type parameter
     * @param method  the method
     * @param timeout the timeout
     * @param dcId    the dc id
     * @return the t
     * @throws org.javagram.utils.RpcException
     * @throws java.util.concurrent.TimeoutException
     */
    public <T extends TLObject> T doRpcCallNonAuth(TLMethod<T> method, int timeout, int dcId) throws RpcException, java.util.concurrent.TimeoutException {
        return doRpcCall(method, timeout, dcId, false);
    }

    /**
     * Do rpc call non auth.
     *
     * @param <T>      the type parameter
     * @param method   the method
     * @param timeout  the timeout
     * @param callback the callback
     */
    public <T extends TLObject> void doRpcCallNonAuth(TLMethod<T> method, int timeout, RpcCallback<T> callback) {
        doRpcCall(method, timeout, callback, 0, false);
    }

    /**
     * Do save file part.
     *
     * @param _fileId   the _ file id
     * @param _filePart the _ file part
     * @param _bytes    the _ bytes
     * @return the boolean
     * @throws IOException the iO exception
     * @throws java.util.concurrent.TimeoutException
     */
    public boolean doSaveFilePart(long _fileId, int _filePart, byte[] _bytes) throws IOException, java.util.concurrent.TimeoutException {
        final TLRequestUploadSaveFilePart tlRequestUploadSaveFilePart = new TLRequestUploadSaveFilePart();
        tlRequestUploadSaveFilePart.setFileId(_fileId);
        tlRequestUploadSaveFilePart.setFilePart(_filePart);
        tlRequestUploadSaveFilePart.setBytes(new TLBytes(_bytes));
        final TLBool res = doRpcCall(tlRequestUploadSaveFilePart, FILE_TIMEOUT, this.primaryDc, true);
        return res instanceof TLBoolTrue;
    }

    /**
     * Do save big file part.
     *
     * @param _fileId     the _ file id
     * @param _filePart   the _ file part
     * @param _totalParts the _ total parts
     * @param _bytes      the _ bytes
     * @return the boolean
     * @throws IOException the iO exception
     * @throws java.util.concurrent.TimeoutException
     */
    public boolean doSaveBigFilePart(long _fileId, int _filePart, int _totalParts, byte[] _bytes) throws IOException, java.util.concurrent.TimeoutException {
        final TLRequestUploadSaveBigFilePart tlRequestUploadSaveBigFilePart = new TLRequestUploadSaveBigFilePart();
        tlRequestUploadSaveBigFilePart.setFileId(_fileId);
        tlRequestUploadSaveBigFilePart.setFilePart(_filePart);
        tlRequestUploadSaveBigFilePart.setFileTotalParts(_totalParts);
        tlRequestUploadSaveBigFilePart.setBytes(new TLBytes(_bytes));
        final TLBool res = doRpcCall(tlRequestUploadSaveBigFilePart, FILE_TIMEOUT, this.primaryDc);
        return res instanceof TLBoolTrue;
    }

    /**
     * Do get file.
     *
     * @param dcId      the dc id
     * @param _location the _ location
     * @param _offset   the _ offset
     * @param _limit    the _ limit
     * @return the tL file
     * @throws IOException the iO exception
     * @throws java.util.concurrent.TimeoutException
     */
    public TLAbsFile doGetFile(int dcId, TLAbsInputFileLocation _location, int _offset, int _limit) throws IOException, java.util.concurrent.TimeoutException {
        final TLRequestUploadGetFile tlRequestUploadGetFile = new TLRequestUploadGetFile();
        tlRequestUploadGetFile.setLocation(_location);
        tlRequestUploadGetFile.setOffset(_offset);
        tlRequestUploadGetFile.setLimit(_limit);
        tlRequestUploadGetFile.setPrecise(false);
        tlRequestUploadGetFile.setCdnSupported(true);
        return this.doRpcCall(tlRequestUploadGetFile, FILE_TIMEOUT, dcId);
    }

    public TLAbsCdnFile doGetCdnFile(int dcId, TLBytes fileToken, int offset, int limit) throws IOException, java.util.concurrent.TimeoutException {
        final TLRequestUploadGetCdnFile tlRequestUploadGetCdnFile = new TLRequestUploadGetCdnFile();
        tlRequestUploadGetCdnFile.setFileToken(fileToken);
        tlRequestUploadGetCdnFile.setOffset(offset);
        tlRequestUploadGetCdnFile.setLimit(limit);
        return doRpcCall(tlRequestUploadGetCdnFile, FILE_TIMEOUT, dcId);
    }

    private void checkDcAuth(int dcId) {
        if (dcId != 0) {
            boolean contains;
            synchronized(this.dcProtos) {
                contains = (this.dcProtos.containsKey(dcId));
            }
            if (!contains) {
                synchronized (this.dcRequired) {
                    this.dcRequired.put(dcId, true);
                    this.dcRequired.notifyAll();
                }
            } else if (!this.config.getApiState().isAuthenticated(dcId)) {
                synchronized (this.dcRequired) {
                    this.dcRequired.put(dcId, true);
                    this.dcRequired.notifyAll();
                }
            }
        }
    }

    private void checkDc(int dcId) {
        if (dcId != 0) {
            boolean contains;
            synchronized(this.dcProtos) {
                contains = (this.dcProtos.containsKey(dcId));
            }
            if (!contains) {
                synchronized(this.dcRequired) {
                    if (!this.dcRequired.containsKey(dcId)) {
                        this.dcRequired.put(dcId, false);
                    }
                    this.dcRequired.notifyAll();
                }
            }
        } else if (this.mainProto == null) {
            synchronized (this.dcRequired) {
                this.dcRequired.notifyAll();
            }
        }
    }

    public void notifyCallbacks() {
        synchronized (this.callbacks) {
            this.callbacks.notifyAll();
        }
    }

    private class ProtoCallback implements MTProtoCallback {

        @Override
        public void onSessionCreated(MTProto proto) {
            if (TelegramApi.this.isClosed) {
                return;
            }

            Logger.w(TelegramApi.this.logtag, proto + ": onSessionCreated");

            if (proto == TelegramApi.this.mainProto) {
                TelegramApi.this.registeredInApi.add(TelegramApi.this.primaryDc);
            } else {
                for (Map.Entry<Integer, MTProto> p : TelegramApi.this.dcProtos.entrySet()) {
                    if (p.getValue() == proto) {
                        TelegramApi.this.registeredInApi.add(p.getKey());
                        break;
                    }
                }
            }

            TelegramApi.this.apiCallback.onUpdatesInvalidated(TelegramApi.this);
        }

        @Override
        public void onAuthInvalidated(MTProto proto) {
            if (TelegramApi.this.isClosed) {
                return;
            }

            if (proto == TelegramApi.this.mainProto) {
                synchronized (TelegramApi.this.dcRequired) {
                    TelegramApi.this.mainProto.close();
                    TelegramApi.this.mainProto = null;
                    TelegramApi.this.config.getApiState().setAuthenticated(TelegramApi.this.primaryDc, false);
                    TelegramApi.this.dcRequired.notifyAll();
                }

                synchronized (TelegramApi.this.dcProtos) {
                    TelegramApi.this.dcProtos.entrySet().stream().forEachOrdered(p -> {
                        p.getValue().close();
                        TelegramApi.this.config.getApiState().setAuthenticated(p.getKey(), false);
                    });
                }

                TelegramApi.this.apiCallback.onAuthCancelled(TelegramApi.this);
            } else {
                synchronized (TelegramApi.this.dcProtos) {
                    for (Map.Entry<Integer, MTProto> p : TelegramApi.this.dcProtos.entrySet()) {
                        if (p.getValue() == proto) {
                            TelegramApi.this.config.getApiState().setAuthenticated(p.getKey(), false);
                            TelegramApi.this.dcProtos.remove(p.getKey());
                            break;
                        }
                    }
                }
                synchronized (TelegramApi.this.dcRequired) {
                    TelegramApi.this.dcRequired.notifyAll();
                }
            }
        }

        @Override
        public void onApiMessage(byte[] message, MTProto proto) {
            if (TelegramApi.this.isClosed) {
                return;
            }

            if (proto == TelegramApi.this.mainProto) {
                TelegramApi.this.registeredInApi.add(TelegramApi.this.primaryDc);
            } else {
                for (Map.Entry<Integer, MTProto> p : TelegramApi.this.dcProtos.entrySet()) {
                    if (p.getValue() == proto) {
                        TelegramApi.this.registeredInApi.add(p.getKey());
                        break;
                    }
                }
            }

            try {
                TLObject object = TelegramApi.this.apiContext.deserializeMessage(message);
                onMessageArrived(object);
            } catch (Throwable t) {
                Logger.e(TelegramApi.this.logtag, t);
            }
        }

        @Override
        public void onRpcResult(int callId, byte[] response, MTProto proto) {
            if (TelegramApi.this.isClosed) {
                return;
            }

            if (proto == TelegramApi.this.mainProto) {
                TelegramApi.this.registeredInApi.add(TelegramApi.this.primaryDc);
            } else {
                for (Map.Entry<Integer, MTProto> p : TelegramApi.this.dcProtos.entrySet()) {
                    if (p.getValue() == proto) {
                        TelegramApi.this.registeredInApi.add(p.getKey());
                        break;
                    }
                }
            }

            try {
                RpcCallbackWrapper currentCallback = null;
                synchronized (TelegramApi.this.callbacks) {
                    if (TelegramApi.this.sentRequests.containsKey(callId)) {
                        currentCallback = TelegramApi.this.callbacks.remove(TelegramApi.this.sentRequests.remove(callId));
                    }
                }
                if (currentCallback != null && currentCallback.method != null) {
                    long start = System.currentTimeMillis();
                    TLObject object = currentCallback.method.deserializeResponse(response, TelegramApi.this.apiContext);
                    Logger.d(TelegramApi.this.logtag, "<< #" + +currentCallback.id + " deserialized " + object + " in " + (System.currentTimeMillis() - start) + " ms");

                    synchronized (currentCallback) {
                        if (currentCallback.isCompleted) {
                            Logger.d(TelegramApi.this.logtag, "<< #" + +currentCallback.id + " ignored " + object + " in " + currentCallback.elapsed() + " ms");
                            return;
                        } else {
                            currentCallback.isCompleted = true;
                        }
                    }
                    Logger.d(TelegramApi.this.logtag, "<< #" + +currentCallback.id + " " + object + " in " + currentCallback.elapsed() + " ms");

                    synchronized (TelegramApi.this.timeoutTimes) {
                        TelegramApi.this.timeoutTimes.remove(currentCallback.timeoutTime);
                    }
                    if (currentCallback.callback != null) {
                        currentCallback.callback.onResult(object);
                    }
                }
            } catch (Throwable t) {
                Logger.e(TelegramApi.this.logtag, t);
            }
        }

        @Override
        public void onRpcError(int callId, int errorCode, String message, MTProto proto) {
            if (TelegramApi.this.isClosed) {
                return;
            }

            if (errorCode == 400 && message != null &&
                    (message.startsWith("CONNECTION_NOT_INITED") || message.startsWith("CONNECTION_LAYER_INVALID"))) {
                Logger.w(TelegramApi.this.logtag, proto + ": (!)Error #400 " + message);

                int dc = -1;
                if (proto == TelegramApi.this.mainProto) {
                    dc = TelegramApi.this.primaryDc;
                } else {
                    for (Map.Entry<Integer, MTProto> p : TelegramApi.this.dcProtos.entrySet()) {
                        if (p.getValue() == proto) {
                            dc = p.getKey();
                            break;
                        }
                    }
                }
                if (dc < 0) {
                    return;
                }
                TelegramApi.this.registeredInApi.remove(dc);

                RpcCallbackWrapper currentCallback;
                synchronized (TelegramApi.this.callbacks) {
                    currentCallback = TelegramApi.this.callbacks.remove(TelegramApi.this.sentRequests.remove(callId));
                    if (currentCallback != null) {
                        currentCallback.isSent = false;
                        TelegramApi.this.callbacks.notifyAll();
                    }
                }

                return;
            } else {
                if (proto == TelegramApi.this.mainProto) {
                    TelegramApi.this.registeredInApi.add(TelegramApi.this.primaryDc);
                } else {
                    for (Map.Entry<Integer, MTProto> p : TelegramApi.this.dcProtos.entrySet()) {
                        if (p.getValue() == proto) {
                            TelegramApi.this.registeredInApi.add(p.getKey());
                            break;
                        }
                    }
                }
            }

            try {
                RpcCallbackWrapper currentCallback = null;
                synchronized (TelegramApi.this.callbacks) {
                    if (TelegramApi.this.sentRequests.containsKey(callId)) {
                        currentCallback = TelegramApi.this.callbacks.remove(TelegramApi.this.sentRequests.remove(callId));
                    }
                }
                if (currentCallback != null) {
                    synchronized (currentCallback) {
                        if (currentCallback.isCompleted) {
                            Logger.d(TelegramApi.this.logtag, "<< #" + +currentCallback.id + " ignored error #" + errorCode + " " + message + " in " + currentCallback.elapsed() + " ms");
                            return;
                        } else {
                            currentCallback.isCompleted = true;
                        }
                    }
                    Logger.d(TelegramApi.this.logtag, "<< #" + +currentCallback.id + " error #" + errorCode + " " + message + " in " + currentCallback.elapsed() + " ms");
                    synchronized (TelegramApi.this.timeoutTimes) {
                        TelegramApi.this.timeoutTimes.remove(currentCallback.timeoutTime);
                    }
                    if (currentCallback.callback != null) {
                        currentCallback.callback.onError(errorCode, message);
                    }
                }
            } catch (Throwable t) {
                Logger.e(TelegramApi.this.logtag, t);
            }
        }

        @Override
        public void onConfirmed(int callId) {
            RpcCallbackWrapper currentCallback = null;
            synchronized (TelegramApi.this.callbacks) {
                if (TelegramApi.this.sentRequests.containsKey(callId)) {
                    currentCallback = TelegramApi.this.callbacks.get(TelegramApi.this.sentRequests.get(callId));
                }
            }
            if (currentCallback != null) {
                Logger.d(TelegramApi.this.logtag, "<< #" + +currentCallback.id + " confirmed in " + currentCallback.elapsed() + " ms");
                synchronized (currentCallback) {
                    if (currentCallback.isCompleted || currentCallback.isConfirmed) {
                        return;
                    } else {
                        currentCallback.isConfirmed = true;
                    }
                }
                if (currentCallback.callback instanceof RpcCallbackEx) {
                    ((RpcCallbackEx) currentCallback.callback).onConfirmed();
                }
            }
        }
    }

    private class SenderThread extends Thread {
        /**
         * Instantiates a new Sender thread.
         */
        public SenderThread() {
            this.setName("Sender#" + TelegramApi.SENDER_THREAD_COUNT.getAndIncrement());
            this.setPriority(Thread.MIN_PRIORITY);
        }

        @Override
        public void run() {
            while (!TelegramApi.this.isClosed) {
                Logger.d(TelegramApi.this.logtag, "Sender iteration");
                RpcCallbackWrapper wrapper = null;
                synchronized (TelegramApi.this.callbacks) {
                    for (RpcCallbackWrapper w : TelegramApi.this.callbacks.values()) {
                        if (!w.isSent) {
                            if (w.dcId == 0 && TelegramApi.this.mainProto != null) {
                                if (TelegramApi.this.config.getApiState().isAuthenticated(TelegramApi.this.primaryDc) || !w.isAuthRequred) {
                                    wrapper = w;
                                    break;
                                }
                            }
                            if (w.dcId != 0 && TelegramApi.this.dcProtos.containsKey(w.dcId)) {
                                if (TelegramApi.this.config.getApiState().isAuthenticated(w.dcId) || !w.isAuthRequred) {
                                    wrapper = w;
                                    break;
                                }
                            }
                        }
                    }
                    if (wrapper == null) {
                        try {
                            TelegramApi.this.callbacks.wait();
                        } catch (InterruptedException e) {
                            Logger.e(TelegramApi.this.logtag, e);
                            return;
                        }
                        continue;
                    }
                }

                if (TelegramApi.this.mainProto == null) {
                    continue;
                }

                if (wrapper.dcId == 0) {
                    if (!TelegramApi.this.config.getApiState().isAuthenticated(TelegramApi.this.primaryDc) && wrapper.isAuthRequred) {
                        continue;
                    }
                    synchronized (TelegramApi.this.callbacks) {
                        boolean isHighPriority = wrapper.callback != null && wrapper.callback instanceof RpcCallbackEx;
                        int rpcId = TelegramApi.this.mainProto.sendRpcMessage(wrapper.method, wrapper.timeout, isHighPriority);
                        TelegramApi.this.sentRequests.put(rpcId, wrapper.id);
                        wrapper.isSent = true;
                        Logger.d(TelegramApi.this.logtag, "#> #" + wrapper.id + " sent to MTProto #" + TelegramApi.this.mainProto.getInstanceIndex() + " with id #" + rpcId);
                    }
                } else {
                    if (!TelegramApi.this.dcProtos.containsKey(wrapper.dcId) || (!TelegramApi.this.config.getApiState().isAuthenticated(wrapper.dcId) && wrapper.isAuthRequred)) {
                        continue;
                    }

                    MTProto proto = TelegramApi.this.dcProtos.get(wrapper.dcId);
                    synchronized (TelegramApi.this.callbacks) {
                        boolean isHighPriority = wrapper.callback != null && wrapper.callback instanceof RpcCallbackEx;
                        int rpcId = proto.sendRpcMessage(wrapper.method, wrapper.timeout, isHighPriority);
                        TelegramApi.this.sentRequests.put(rpcId, wrapper.id);
                        wrapper.isSent = true;
                        Logger.d(TelegramApi.this.logtag, "#> #" + wrapper.id + " sent to MTProto #" + proto.getInstanceIndex() + " with id #" + rpcId);
                    }
                }
            }
        }
    }

    private class ConnectionThread extends Thread {
        
        /**
         * Instantiates a new Connection thread.
         */
        public ConnectionThread() {
            this.setName("Connection#" + TelegramApi.CONNECTION_THREAD_COUNT.getAndIncrement());
            this.setPriority(Thread.MIN_PRIORITY);
        }

        private MTProto waitForDc(final int dcId) throws IOException, java.util.concurrent.TimeoutException {
            Logger.d(TelegramApi.this.logtag, "#" + dcId + ": waitForDc");
            if (TelegramApi.this.isClosed) {
                Logger.w(TelegramApi.this.logtag, "#" + dcId + ": Api is closed");
                throw new TimeoutException();
            }

//        if (!state.isAuthenticated(primaryDc)) {
//            Logger.w(TAG, "#" + dcId + ": Dc is not authenticated");
//            throw new TimeoutException();
//        }

            Object syncObj;
            synchronized (TelegramApi.this.dcSync) {
                syncObj = TelegramApi.this.dcSync.get(dcId);
                if (syncObj == null) {
                    syncObj = new Object();
                    TelegramApi.this.dcSync.put(dcId, syncObj);
                }
            }

            synchronized (syncObj) {
                MTProto proto;
                synchronized (TelegramApi.this.dcProtos) {
                    proto = TelegramApi.this.dcProtos.get(dcId);
                    if (proto != null) {
                        if (proto.isClosed().get()) {
                            Logger.d(TelegramApi.this.logtag, "#" + dcId + "proto removed because of death");
                            TelegramApi.this.dcProtos.remove(dcId);
                            proto = null;
                        }
                    }
                }

                if (proto == null) {
                    Logger.d(TelegramApi.this.logtag, "#" + dcId + ": Creating proto for dc");
                    ConnectionInfo[] connectionInfo = TelegramApi.this.config.getApiState().getAvailableConnections(dcId);

                    if (connectionInfo.length == 0) {
                        Logger.w(TelegramApi.this.logtag, "#" + dcId + ": Unable to find proper dc config");
                        TLConfig config = doRpcCall(new TLRequestHelpGetConfig());
                        TelegramApi.this.config.getApiState().updateSettings(config);
                        resetConnectionInfo();
                        connectionInfo = TelegramApi.this.config.getApiState().getAvailableConnections(dcId);
                    }

                    if (connectionInfo.length == 0) {
                        Logger.w(TelegramApi.this.logtag, "#" + dcId + ": Still unable to find proper dc config");
                        throw new TimeoutException();
                    }

                    if (TelegramApi.this.config.getApiState().getAuthKey(dcId) != null) {
                        byte[] authKey = TelegramApi.this.config.getApiState().getAuthKey(dcId);
                        if (authKey == null) {
                            throw new TimeoutException();
                        }
                        proto = new MTProto(TelegramApi.this.config.getApiState().getMtProtoState(dcId), TelegramApi.this.callback, new CallWrapper() {
                                @Override
                                public TLObject wrapObject(TLMethod srcRequest) {
                                    return wrapForDc(dcId, srcRequest);
                                }
                        }, CHANNELS_FS, TelegramApi.this.config);

                        TelegramApi.this.dcProtos.put(dcId, proto);
                        return proto;
                    } else {
                        Logger.w(TelegramApi.this.logtag, "#" + dcId + ": Creating key");
                        Authorizer authorizer = new Authorizer(TelegramApi.this.config);
                        PqAuth auth = authorizer.doAuth(connectionInfo);
                        if (auth == null) {
                            Logger.w(TelegramApi.this.logtag, "#" + dcId + ": Timed out");
                            throw new TimeoutException();
                        }
                        TelegramApi.this.config.getApiState().putAuthKey(dcId, auth.getAuthKey());
                        TelegramApi.this.config.getApiState().setAuthenticated(dcId, false);
                        TelegramApi.this.config.getApiState().getMtProtoState(dcId).initialServerSalt(auth.getServerSalt());

                        byte[] authKey = TelegramApi.this.config.getApiState().getAuthKey(dcId);
                        if (authKey == null) {
                            Logger.w(TelegramApi.this.logtag, "#" + dcId + ": auth key == null");
                            throw new TimeoutException();
                        }

                        proto = new MTProto(TelegramApi.this.config.getApiState().getMtProtoState(dcId), TelegramApi.this.callback,
                                new CallWrapper() {
                                    @Override
                                    public TLObject wrapObject(TLMethod srcRequest) {
                                        return wrapForDc(dcId, srcRequest);
                                    }
                                }, CHANNELS_FS, TelegramApi.this.config);

                        TelegramApi.this.dcProtos.put(dcId, proto);

                        return proto;
                    }
                } else {
                    Logger.w(TelegramApi.this.logtag, "#" + dcId + ": returning proper proto");
                    return proto;
                }
            }
        }

        private MTProto waitForAuthDc(final int dcId) throws IOException, java.util.concurrent.TimeoutException {
            Logger.d(TelegramApi.this.logtag, "#" + dcId + ": waitForAuthDc");
            if (TelegramApi.this.isClosed) {
                Logger.w(TelegramApi.this.logtag, "#" + dcId + ": Api is closed");
                throw new TimeoutException();
            }

            MTProto proto = waitForDc(dcId);

            if (!TelegramApi.this.config.getApiState().isAuthenticated(dcId)) {
                Logger.w(TelegramApi.this.logtag, "#" + dcId + ": exporting auth");
                TLRequestAuthExportAuthorization exportAuthorization = new TLRequestAuthExportAuthorization();
                exportAuthorization.setDcId(dcId);
                TLExportedAuthorization exAuth = doRpcCall(exportAuthorization);

                Logger.w(TelegramApi.this.logtag, "#" + dcId + ": importing auth");
                TLRequestAuthImportAuthorization tlRequestAuthImportAuthorization = new TLRequestAuthImportAuthorization();
                tlRequestAuthImportAuthorization.setId(exAuth.getId());
                tlRequestAuthImportAuthorization.setBytes(exAuth.getBytes());
                doRpcCallNonAuth(tlRequestAuthImportAuthorization, DEFAULT_TIMEOUT, dcId);

                TelegramApi.this.config.getApiState().setAuthenticated(dcId, true);
            }

            return proto;
        }

        @Override
        public void run() {
            while (!TelegramApi.this.isClosed) {
                Logger.d(TelegramApi.this.logtag, "Connection iteration");
                if (TelegramApi.this.mainProto == null) {
                    if (TelegramApi.this.config.getApiState().getAuthKey(TelegramApi.this.primaryDc) == null) {
                        try {
                            long start = System.currentTimeMillis();
                            waitForDc(TelegramApi.this.primaryDc);
                            TelegramApi.this.mainProto = new MTProto(TelegramApi.this.config.getApiState().getMtProtoState(TelegramApi.this.primaryDc), TelegramApi.this.callback,
                                    new CallWrapper() {
                                        @Override
                                        public TLObject wrapObject(TLMethod srcRequest) {
                                            return wrapForDc(TelegramApi.this.primaryDc, srcRequest);
                                        }
                                    }, CHANNELS_MAIN, TelegramApi.this.config);
                            Logger.d(TelegramApi.this.logtag, "#MTProto #" + TelegramApi.this.mainProto.getInstanceIndex() + " created in " + (System.currentTimeMillis() - start) + " ms");
                        } catch (IOException | java.util.concurrent.TimeoutException e) {
                            Logger.e(TelegramApi.this.logtag, e);
                            try {
                                Thread.sleep(1000);
                                continue;
                            } catch (InterruptedException e1) {
                                Logger.e(TelegramApi.this.logtag, e1);
                                return;
                            }
                        }
                    } else {
                        long start = System.currentTimeMillis();
                        TelegramApi.this.mainProto = new MTProto(TelegramApi.this.config.getApiState().getMtProtoState(TelegramApi.this.primaryDc), TelegramApi.this.callback,
                                new CallWrapper() {
                                    @Override
                                    public TLObject wrapObject(TLMethod srcRequest) {
                                        return wrapForDc(TelegramApi.this.primaryDc, srcRequest);
                                    }
                                }, CHANNELS_MAIN, TelegramApi.this.config);
                        Logger.d(TelegramApi.this.logtag, "#MTProto #" + TelegramApi.this.mainProto.getInstanceIndex() + " created in " + (System.currentTimeMillis() - start) + " ms");
                    }
                    synchronized (TelegramApi.this.callbacks) {
                        TelegramApi.this.callbacks.notifyAll();
                    }
                    continue;
                }

                Integer dcId = null;
                Boolean authRequired = null;
                synchronized (TelegramApi.this.dcRequired) {
                    if (TelegramApi.this.dcRequired.isEmpty()) {
                        dcId = null;
                        authRequired = null;
                    } else {
                        try {
                            dcId = TelegramApi.this.dcRequired.firstKey();
                        } catch (Exception e) {
                            Logger.e(TelegramApi.this.logtag, e);
                        }
                    }

                    if (dcId == null) {
                        try {
                            TelegramApi.this.dcRequired.wait();
                        } catch (InterruptedException e) {
                            // e.printStackTrace();
                        }
                        continue;
                    }

                    authRequired = TelegramApi.this.dcRequired.remove(dcId);
                }

                if (TelegramApi.this.dcProtos.containsKey(dcId)) {
                    if (authRequired && !TelegramApi.this.config.getApiState().isAuthenticated(dcId) && TelegramApi.this.config.getApiState().isAuthenticated(TelegramApi.this.primaryDc)) {
                        try {
                            waitForAuthDc(dcId);
                            synchronized (TelegramApi.this.callbacks) {
                                TelegramApi.this.callbacks.notifyAll();
                            }
                        } catch (IOException | java.util.concurrent.TimeoutException e) {
                            try {
                                Thread.sleep(1000);
                                continue;
                            } catch (InterruptedException e1) {
                                Logger.e(TelegramApi.this.logtag, e1);
                                return;
                            }
                        }
                    }
                } else {
                    try {
                        if (authRequired && !TelegramApi.this.config.getApiState().isAuthenticated(dcId) && TelegramApi.this.config.getApiState().isAuthenticated(TelegramApi.this.primaryDc)) {
                            waitForAuthDc(dcId);
                        } else {
                            waitForDc(dcId);
                        }
                        synchronized (TelegramApi.this.callbacks) {
                            TelegramApi.this.callbacks.notifyAll();
                        }
                    } catch (IOException | java.util.concurrent.TimeoutException e) {
                        Logger.e(TelegramApi.this.logtag, e);
                    }
                }
            }
        }
    }

    private class TimeoutThread extends Thread {
        /**
         * Instantiates a new Timeout thread.
         */
        public TimeoutThread() {
            this.setName("Timeout#" + TelegramApi.TIMEOUT_THREAD_COUNT.getAndIncrement());
        }

        @Override
        public void run() {
            while (!TelegramApi.this.isClosed) {
                Logger.d(TelegramApi.this.logtag, "Timeout Iteration");
                Long key = null;
                Integer id;
                synchronized (TelegramApi.this.timeoutTimes) {
                    if (TelegramApi.this.timeoutTimes.isEmpty()) {
                        key = null;
                    } else {
                        try {
                            key = TelegramApi.this.timeoutTimes.firstKey();
                        } catch (Exception e) {
                            Logger.e(TelegramApi.this.logtag, e);
                        }
                    }

                    if (key == null) {
                        try {
                            TelegramApi.this.timeoutTimes.wait();
                        } catch (InterruptedException e) {
                            // e.printStackTrace();
                        }
                        continue;
                    }

                    long delta = (key - System.nanoTime()) / (1000 * 1000);
                    if (delta > 0) {
                        try {
                            TelegramApi.this.timeoutTimes.wait(delta);
                        } catch (InterruptedException e) {
                            // e.printStackTrace();
                        }
                        continue;
                    }

                    id = TelegramApi.this.timeoutTimes.remove(key);
                    if (id == null) {
                        continue;
                    }
                }

                RpcCallbackWrapper currentCallback;
                synchronized (TelegramApi.this.callbacks) {
                    currentCallback = TelegramApi.this.callbacks.remove(id);
                }
                if (currentCallback != null) {
                    synchronized (currentCallback) {
                        if (currentCallback.isCompleted) {
                            Logger.d(TelegramApi.this.logtag, "RPC #" + id + ": Timeout ignored");
                            return;
                        } else {
                            currentCallback.isCompleted = true;
                        }
                    }
                    Logger.d(TelegramApi.this.logtag, "RPC #" + id + ": Timeout (" + currentCallback.elapsed() + " ms)");
                    currentCallback.callback.onError(0, null);
                } else {
                    Logger.d(TelegramApi.this.logtag, "RPC #" + id + ": Timeout ignored2");
                }
            }
            
            synchronized(TelegramApi.this.timeoutTimes) {
                for (Map.Entry<Long, Integer> entry : TelegramApi.this.timeoutTimes.entrySet()) {
                    RpcCallbackWrapper currentCallback;
                    synchronized (TelegramApi.this.callbacks) {
                        currentCallback = TelegramApi.this.callbacks.remove(entry.getValue());
                    }
                    if (currentCallback != null) {
                        synchronized(currentCallback) {
                            if (currentCallback.isCompleted) {
                                return;
                            } else {
                                currentCallback.isCompleted = true;
                            }
                        }
                        Logger.d(TelegramApi.this.logtag, "RPC #" + entry.getValue() + ": Timeout (" + currentCallback.elapsed() + " ms)");
                        currentCallback.callback.onError(0, null);
                    }
                }
            }
        }
    }

    private class RpcCallbackWrapper {
        /**
         * The Id.
         */
        public int id;
        /**
         * The Request time.
         */
        public long requestTime = System.currentTimeMillis();
        /**
         * The Is sent.
         */
        public boolean isSent;
        /**
         * The Is completed.
         */
        public boolean isCompleted;
        /**
         * The Is confirmed.
         */
        public boolean isConfirmed;
        /**
         * The Callback.
         */
        public RpcCallback callback;
        /**
         * The Timeout time.
         */
        public long timeoutTime;
        /**
         * The Timeout.
         */
        public long timeout;
        /**
         * The Method.
         */
        public TLMethod method;

        /**
         * The Is auth requred.
         */
        public boolean isAuthRequred;
        /**
         * The Dc id.
         */
        public int dcId;

        private RpcCallbackWrapper(int id, TLMethod method, RpcCallback callback) {
            this.id = id;
            this.method = method;
            this.callback = callback;
        }

        /**
         * Elapsed long.
         *
         * @return the long
         */
        public long elapsed() {
            return System.currentTimeMillis() - this.requestTime;
        }
    }
}
