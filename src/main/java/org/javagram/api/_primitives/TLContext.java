package org.javagram.api._primitives;

import org.javagram.utils.DeserializeException;
import org.javagram.utils.StreamingUtils;
import org.javagram.mtproto.log.Logger;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import org.javagram.MyTLAppConfiguration;

/**
 * TypeLanguage context object.
 * It performs deserialization of objects and vectors.
 * All known classes might be registered in context for deserialization.
 * Often this might be performed from inherited class in init() method call.
 * If TL-Object contains static int field CLASS_ID, then it might be used for registration,
 * but it uses reflection so it might be slow in some cases.
 * It is recommended to manually pass CLASS_ID to registerClass method.
 *
 */
public abstract class TLContext {
    
    private static final String TAG = "TLCONTEXT";
    protected MyTLAppConfiguration config;

    private final HashMap<Integer, Class> registeredClasses = new HashMap<>();
    private final HashMap<Integer, Class> registeredCompatClasses = new HashMap<>();

    public TLContext() { 
    }

    public void build(MyTLAppConfiguration config) {
        this.config = config;
        this.registerClasses();
    }
    
    protected abstract void registerClasses();
    
    public boolean isSupportedObject(TLObject object) {
        return isSupportedObject(object.getClassId());
    }

    public boolean isSupportedObject(int classId) {
        return this.registeredClasses.containsKey(classId);
    }

    public <T extends TLObject> void registerClass(Class<T> tClass) {
        try {
            int classId = tClass.getField("CLASS_ID").getInt(null);
            this.registeredClasses.put(classId, tClass);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            Logger.e(TAG, e);
        }
    }

    public <T extends TLObject> void registerClass(int clazzId, Class<T> tClass) {
        if (this.registeredClasses.containsKey(clazzId)) {
            Logger.e("TelegramApi", new Exception("Class already exists" + clazzId));
        }
        this.registeredClasses.put(clazzId, tClass);
    }

    public <T extends TLObject> void registerCompatClass(Class<T> tClass) {
        try {
            int classId = tClass.getField("CLASS_ID").getInt(null);
            this.registeredCompatClasses.put(classId, tClass);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            Logger.e(TAG, e);
        }
    }

    public <T extends TLObject> void registerCompatClass(int clazzId, Class<T> tClass) {
        this.registeredCompatClasses.put(clazzId, tClass);
    }

    protected TLObject convertCompatClass(TLObject src) {
        return src;
    }

    public TLObject deserializeMessage(byte[] data) throws IOException {
        return deserializeMessage(new ByteArrayInputStream(data));
    }

    public TLObject deserializeMessage(int clazzId, InputStream stream) throws IOException {
        if (clazzId == TLGzipObject.CLASS_ID) {
            TLGzipObject obj = new TLGzipObject();
            obj.deserializeBody(stream, this);
            BufferedInputStream gzipInputStream = new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(obj.getPackedData())));
            int innerClazzId = StreamingUtils.readInt(gzipInputStream);
            return deserializeMessage(innerClazzId, gzipInputStream);
        }

        if (clazzId == TLBoolTrue.CLASS_ID) {
            return new TLBoolTrue();
        }

        if (clazzId == TLBoolFalse.CLASS_ID) {
            return new TLBoolFalse();
        }

        if (this.registeredCompatClasses.containsKey(clazzId)) {
            try {
                Class messageClass = this.registeredCompatClasses.get(clazzId);
                TLObject message = (TLObject) messageClass.getConstructor().newInstance();
                message.deserializeBody(stream, this);
                return convertCompatClass(message);
            } catch (DeserializeException e) {
                throw e;
            } catch (Exception e) {
                e.printStackTrace();
                Logger.e(TAG, e);
                throw new IOException("Unable to deserialize data #" + Integer.toHexString(clazzId) + " #" + clazzId);
            }
        }

        try {
            Class messageClass = this.registeredClasses.get(clazzId);
            if (messageClass != null) {
                TLObject message = (TLObject) messageClass.getConstructor().newInstance();
                message.deserializeBody(stream, this);
                return message;
            } else {
                throw new DeserializeException("Unsupported class: #" + Integer.toHexString(clazzId) + " #" + clazzId);
            }
        } catch (DeserializeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(TAG, e);
            throw new IOException("Unable to deserialize data #" + Integer.toHexString(clazzId) + " #" + clazzId);
        }
    }

    public TLObject deserializeMessage(InputStream stream) throws IOException {
        int clazzId = StreamingUtils.readInt(stream);
        return deserializeMessage(clazzId, stream);
    }

    public TLVector deserializeVector(InputStream stream) throws IOException {
        int clazzId = StreamingUtils.readInt(stream);
        switch (clazzId) {
            case TLVector.CLASS_ID:
                TLVector res = new TLVector();
                res.deserializeBody(stream, this);
                return res;
            case TLGzipObject.CLASS_ID:
                TLGzipObject obj = new TLGzipObject();
                obj.deserializeBody(stream, this);
                BufferedInputStream gzipInputStream = new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(obj.getPackedData())));
                return deserializeVector(gzipInputStream);
            default:
                throw new IOException("Unable to deserialize vector #" + Integer.toHexString(clazzId) + " #" + clazzId);
        }
    }

    public TLIntVector deserializeIntVector(InputStream stream) throws IOException {
        int clazzId = StreamingUtils.readInt(stream);
        switch (clazzId) {
            case TLVector.CLASS_ID:
                TLIntVector res = new TLIntVector();
                res.deserializeBody(stream, this);
                return res;
            case TLGzipObject.CLASS_ID:
                TLGzipObject obj = new TLGzipObject();
                obj.deserializeBody(stream, this);
                BufferedInputStream gzipInputStream = new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(obj.getPackedData())));
                return deserializeIntVector(gzipInputStream);
            default:
                throw new IOException("Unable to deserialize vector #" + Integer.toHexString(clazzId) + " #" + clazzId);
        }
    }

    public TLLongVector deserializeLongVector(InputStream stream) throws IOException {
        int clazzId = StreamingUtils.readInt(stream);
        switch (clazzId) {
            case TLVector.CLASS_ID:
                TLLongVector res = new TLLongVector();
                res.deserializeBody(stream, this);
                return res;
            case TLGzipObject.CLASS_ID:
                TLGzipObject obj = new TLGzipObject();
                obj.deserializeBody(stream, this);
                BufferedInputStream gzipInputStream = new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(obj.getPackedData())));
                return deserializeLongVector(gzipInputStream);
            default:
                throw new IOException("Unable to deserialize vector #" + Integer.toHexString(clazzId) + " #" + clazzId);
        }
    }

    public TLStringVector deserializeStringVector(InputStream stream) throws IOException {
        int clazzId = StreamingUtils.readInt(stream);
        switch (clazzId) {
            case TLVector.CLASS_ID:
                TLStringVector res = new TLStringVector();
                res.deserializeBody(stream, this);
                return res;
            case TLGzipObject.CLASS_ID:
                TLGzipObject obj = new TLGzipObject();
                obj.deserializeBody(stream, this);
                BufferedInputStream gzipInputStream = new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(obj.getPackedData())));
                return deserializeStringVector(gzipInputStream);
            default:
                throw new IOException("Unable to deserialize vector #" + Integer.toHexString(clazzId) + " #" + clazzId);
        }
    }

    public TLBytes allocateBytes(int size) {
        return new TLBytes(new byte[size], 0, size);
    }

    public Set<Integer> getRegisteredClassIds() {
        return this.registeredClasses.keySet();
    }

    public void releaseBytes(TLBytes unused) {
    }
    
}