package org.telegram.api.upload.functions;

import org.telegram.api.upload.base.cdn.TLAbsCdnFile;
import org.telegram.api._primitives.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;

public class TLRequestUploadGetCdnFile extends TLMethod<TLAbsCdnFile> {
    public static final int CLASS_ID = 0x2000bcc3;

    private TLBytes fileToken;
    private int offset;
    private int limit;

    public TLRequestUploadGetCdnFile() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAbsCdnFile deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if (res instanceof TLAbsCdnFile)
            return (TLAbsCdnFile) res;
        throw new IOException("Incorrect response type. Expected " + TLAbsCdnFile.class.getCanonicalName()
                + ", got: " + res.getClass().getCanonicalName());
    }

    public TLBytes getFileToken() {
        return fileToken;
    }

    public void setFileToken(TLBytes fileToken) {
        this.fileToken = fileToken;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int value) {
        this.limit = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBytes(this.fileToken, stream);
        StreamingUtils.writeInt(this.offset, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.fileToken = StreamingUtils.readTLBytes(stream, context);
        this.offset = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "upload.getCdnFile#2000bcc3";
    }

}