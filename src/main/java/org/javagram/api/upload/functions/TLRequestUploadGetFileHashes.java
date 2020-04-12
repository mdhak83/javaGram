package org.javagram.api.upload.functions;

import org.javagram.api.upload.base.cdn.TLAbsCdnFile;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import org.javagram.api.file.base.TLFileHash;
import org.javagram.api.file.base.input.location.TLAbsInputFileLocation;
import org.javagram.utils.StreamingUtils;

/**
 * Get SHA256 hashes for verifying downloaded files
 * upload.getFileHashes#c7025931 location:InputFileLocation offset:int = Vector&lt;FileHash&gt;;
 */
public class TLRequestUploadGetFileHashes extends TLMethod<TLVector<TLFileHash>> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc7025931;

    /**
     * File
     */
    private TLAbsInputFileLocation location;

    /**
     * Offset from which to get file hashes
     */
    private int offset;

    public TLRequestUploadGetFileHashes() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputFileLocation getLocation() {
        return location;
    }

    public void setLocation(TLAbsInputFileLocation location) {
        this.location = location;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.location, stream);
        StreamingUtils.writeInt(this.offset, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.location = StreamingUtils.readTLObject(stream, context, TLAbsInputFileLocation.class);
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
        return "upload.getFileHashes#c7025931";
    }

}