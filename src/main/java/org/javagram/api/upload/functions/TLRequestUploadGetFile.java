package org.javagram.api.upload.functions;

import org.javagram.api.file.base.input.location.TLAbsInputFileLocation;
import org.javagram.api.upload.base.file.TLAbsFile;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request upload get file.
 */
public class TLRequestUploadGetFile extends TLMethod<TLAbsFile> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb15a9afc;

    private static final int FLAG_PRECISE           = 0x00000001; // 0 : Disable some checks on limit and offset values, useful for example to stream videos by keyframes
    private static final int FLAG_CDN_SUPPORTED     = 0x00000002; // 1 : Whether the current client supports @see <a href="https://core.telegram.org/cdn">CDN downloads</a>

    /**
     * File location
     */
    private TLAbsInputFileLocation location;

    /**
     * Number of bytes to be skipped
     */
    private int offset;
    
    /**
     * Number of bytes to be returned
     */
    private int limit;

    public TLRequestUploadGetFile() {
        super();
    }

    public TLRequestUploadGetFile(TLAbsInputFileLocation location, int offset, int limit) {
        this.setLocation(location);
        this.setOffset(offset);
        this.setLimit(limit);
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public boolean isPrecise() {
        return this.isFlagSet(FLAG_PRECISE);
    }
    
    public void setPrecise(boolean value) {
        this.setFlag(FLAG_PRECISE, value);
    }

    public boolean isCdnSupported() {
        return this.isFlagSet(FLAG_CDN_SUPPORTED);
    }

    public void setCdnSupported(boolean value) {
        this.setFlag(FLAG_CDN_SUPPORTED, value);
    }

    public TLAbsInputFileLocation getLocation() {
        return this.location;
    }

    public final void setLocation(TLAbsInputFileLocation value) {
        this.location = value;
    }

    public int getOffset() {
        return this.offset;
    }

    public final void setOffset(int value) {
        this.offset = value;
    }

    public int getLimit() {
        return this.limit;
    }

    public final void setLimit(int value) {
        this.limit = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.location, stream);
        StreamingUtils.writeInt(this.offset, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.location = StreamingUtils.readTLObject(stream, context, TLAbsInputFileLocation.class);
        this.offset = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsFile deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } if (res instanceof TLAbsFile) {
            return (TLAbsFile) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsFile.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "upload.getFile#b15a9afc";
    }

}