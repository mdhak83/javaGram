package org.telegram.client.structure.storage;

import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;

public class TLDcInfo extends TLObject {

    public static final int CLASS_ID = 0x5d8c6cc;

    private int dcId;
    private String address;
    private int port;
    private int version;

    public TLDcInfo() { }

    public TLDcInfo(int flags, int dcId, String address, int port, int version) {
        this.flags = flags;
        this.dcId = dcId;
        this.address = address;
        this.port = port;
        this.version = version;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getDcId() {
        return dcId;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.dcId, stream);
        StreamingUtils.writeTLString(this.address, stream);
        StreamingUtils.writeInt(this.port, stream);
        StreamingUtils.writeInt(this.version, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.dcId = StreamingUtils.readInt(stream);
        this.address = StreamingUtils.readTLString(stream);
        this.port = StreamingUtils.readInt(stream);
        this.version = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "dcInfo#5d8c6cc";
    }

}