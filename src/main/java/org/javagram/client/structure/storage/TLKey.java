package org.javagram.client.structure.storage;

import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.StreamingUtils;

@SuppressWarnings("ALL")
public class TLKey extends TLObject {

    public static final int CLASS_ID = 0x4a861b1a;

    private int dcId;

    private byte[] authKey;

    private boolean isAuthorised;

    private TLVector salts;

    private TLVector oldSessions;

    public TLKey(int dcId, byte[] authKey) {
        this.dcId = dcId;
        this.authKey = authKey;
        this.isAuthorised = false;
        this.salts = new TLVector<>();
        this.oldSessions = new TLVector<>();
    }

    public TLKey() {

    }

    public int getDcId() {
        return dcId;
    }

    public byte[] getAuthKey() {
        return authKey;
    }

    public boolean isAuthorised() {
        return isAuthorised;
    }

    public void setAuthorised(boolean authorised) {
        isAuthorised = authorised;
    }

    public TLVector<TLLastKnownSalt> getSalts() {
        return salts;
    }

    public TLVector<TLOldSession> getOldSessions() {
        return oldSessions;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "key#4a861b1a";
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.dcId, stream);
        StreamingUtils.writeByteArray(this.authKey, stream);
        StreamingUtils.writeTLBool(this.isAuthorised, stream);
        StreamingUtils.writeTLVector(this.salts, stream);
        StreamingUtils.writeTLVector(this.oldSessions, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.dcId = StreamingUtils.readInt(stream);
        this.authKey = StreamingUtils.readBytes(256, stream);
        this.isAuthorised = StreamingUtils.readTLBool(stream);
        this.salts = StreamingUtils.readTLVector(stream, context);
        this.oldSessions = StreamingUtils.readTLVector(stream, context);
    }
}
