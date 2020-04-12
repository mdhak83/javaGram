package org.javagram.api.upload.functions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import org.javagram.api.file.base.TLFileHash;
import org.javagram.utils.StreamingUtils;

/**
 * Request a reupload of a certain file to a CDN DC.
 * upload.reuploadCdnFile#9b2754a8 file_token:bytes request_token:bytes = Vector&lt;FileHash&gt;;
 */
public class TLRequestReuploadCdnFile extends TLMethod<TLVector<TLFileHash>> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9b2754a8;

    /**
     * File token
     */
    private TLBytes fileToken;

    /**
     * Request token
     */
    private TLBytes requestToken;

    public TLRequestReuploadCdnFile() {
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

    public TLBytes getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(TLBytes requestToken) {
        this.requestToken = requestToken;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBytes(this.fileToken, stream);
        StreamingUtils.writeTLBytes(this.requestToken, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.fileToken = StreamingUtils.readTLBytes(stream, context);
        this.requestToken = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public TLVector<TLFileHash> deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLVector) {
            return (TLVector<TLFileHash>) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "upload.reuploadCdnFile#9b2754a8";
    }

}