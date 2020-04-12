package org.javagram.api.auth.base.sentcode;

import org.javagram.api.auth.base.codetype.TLAbsCodeType;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLSentCode extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5e002502;

    private static final int FLAG_NEXT_TYPE        = 0x00000002; // 1
    private static final int FLAG_TIMEOUT          = 0x00000004; // 2

    private TLAbsSentCodeType type;
    private String phoneCodeHash;
    private TLAbsCodeType nextType;
    private int timeout;

    public TLSentCode() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getPhoneCodeHash() {
        return this.phoneCodeHash;
    }

    public void setPhoneCodeHash(String phoneCodeHash) {
        this.phoneCodeHash = phoneCodeHash;
    }

    public TLAbsSentCodeType getType() {
        return type;
    }

    public void setType(TLAbsSentCodeType type) {
        this.type = type;
    }

    public boolean hasNextType() {
        return this.isFlagSet(FLAG_NEXT_TYPE);
    }

    public TLAbsCodeType getNextType() {
        return nextType;
    }

    public void setNextType(TLAbsCodeType nextType) {
        this.nextType = nextType;
    }

    public boolean hasTimeout() {
        return this.isFlagSet(FLAG_TIMEOUT);
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.type, stream);
        StreamingUtils.writeTLString(this.phoneCodeHash, stream);
        if (this.hasNextType()) {
            StreamingUtils.writeTLObject(this.nextType, stream);
        }
        if (this.hasTimeout()) {
            StreamingUtils.writeInt(this.timeout, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.type = StreamingUtils.readTLObject(stream, context, TLAbsSentCodeType.class);
        this.phoneCodeHash = StreamingUtils.readTLString(stream);
        if (this.hasNextType()) {
            this.nextType = StreamingUtils.readTLObject(stream, context, TLAbsCodeType.class);
        }
        if (this.hasTimeout()) {
            this.timeout = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "auth.sentCode#5e002502";
    }

}