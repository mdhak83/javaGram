package org.telegram.client.structure.storage;

import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;

public class TLStorage extends TLObject {

    public static final int CLASS_ID = 0x3ef9c4b4;

    private TLVector keys;

    private TLVector dcInfos;

    private int primaryDc;

    private boolean isAuthorized;

    private int uid;

    private String phone;

    public TLStorage() {
        keys = new TLVector<>();
        dcInfos = new TLVector<>();
        primaryDc = 1;
        isAuthorized = false;
        uid = 0;
        phone = "";
    }

    public TLVector<TLKey> getKeys() {
        return keys;
    }

    public TLVector<TLDcInfo> getDcInfos() {
        return dcInfos;
    }

    public int getPrimaryDc() {
        return primaryDc;
    }

    public void setPrimaryDc(int primaryDc) {
        this.primaryDc = primaryDc;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "storage#3ef9c4b4";
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.keys, stream);
        StreamingUtils.writeTLVector(this.dcInfos, stream);
        StreamingUtils.writeInt(this.primaryDc, stream);
        StreamingUtils.writeTLBool(this.isAuthorized, stream);
        StreamingUtils.writeInt(this.uid, stream);
        StreamingUtils.writeTLString(this.phone, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.keys = StreamingUtils.readTLVector(stream, context);
        this.dcInfos = StreamingUtils.readTLVector(stream, context);
        this.primaryDc = StreamingUtils.readInt(stream);
        this.isAuthorized = StreamingUtils.readTLBool(stream);
        this.uid = StreamingUtils.readInt(stream);
        this.phone = StreamingUtils.readTLString(stream);
    }

}