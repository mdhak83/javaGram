package org.javagram.api.upload.functions;

import org.javagram.api.upload.base.cdn.TLAbsCdnFile;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import org.javagram.api.file.base.TLFileHash;
import org.javagram.utils.StreamingUtils;

/**
 * Get SHA256 hashes for verifying downloaded @see <a href="https://core.telegram.org/cdn">CDN</a> files
 * upload.getCdnFileHashes#4da54231 file_token:bytes offset:int = Vector&lt;FileHash&gt;;
 */
public class TLRequestUploadGetCdnFileHashes extends TLMethod<TLVector<TLFileHash>> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4da54231;

    /**
     * File
     */
    private TLBytes fileToken;

    /**
     * Offset from which to start getting hashes
     */
    private int offset;

    public TLRequestUploadGetCdnFileHashes() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
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

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBytes(this.fileToken, stream);
        StreamingUtils.writeInt(this.offset, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.fileToken = StreamingUtils.readTLBytes(stream, context);
        this.offset = StreamingUtils.readInt(stream);
    }

    @Override
    public TLVector<TLFileHash> deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLVector) {
            return (TLVector<TLFileHash>) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsCdnFile.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "upload.getCdnFileHashes#4da54231";
    }

}