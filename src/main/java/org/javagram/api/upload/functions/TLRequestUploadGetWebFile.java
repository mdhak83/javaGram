package org.javagram.api.upload.functions;

import org.javagram.api.file.base.input.location.web.TLInputWebFileLocation;
import org.javagram.api.upload.base.TLWebFile;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api.file.base.input.location.web.TLAbsInputWebFileLocation;
import org.javagram.utils.StreamingUtils;

/**
 * Returns content of an HTTP file or a part, by proxying the request through telegram.
 * upload.getWebFile#24e6818d location:InputWebFileLocation offset:int limit:int = upload.WebFile;
 */
public class TLRequestUploadGetWebFile extends TLMethod<TLWebFile> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x24e6818d;

    /**
     * The file to download
     */
    private TLAbsInputWebFileLocation location;
    
    /**
     * Number of bytes to be skipped
     */
    private int offset;
    
    /**
     * Number of bytes to be returned
     */
    private int limit;

    public TLRequestUploadGetWebFile() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputWebFileLocation getLocation() {
        return location;
    }

    public void setLocation(TLAbsInputWebFileLocation location) {
        this.location = location;
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

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public TLWebFile deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("[TLRequestUploadGetWebFile::deserializeResponse] Unable to parse response");
        } else if (res instanceof TLWebFile) {
            return (TLWebFile) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLWebFile.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.location, stream);
        StreamingUtils.writeInt(this.offset, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.location = StreamingUtils.readTLObject(stream, context, TLInputWebFileLocation.class);
        this.offset = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "upload.getWebFile#24e6818d";
    }
    
}