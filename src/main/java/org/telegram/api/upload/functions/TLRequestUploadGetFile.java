package org.telegram.api.upload.functions;

import org.telegram.api.file.base.input.location.TLAbsInputFileLocation;
import org.telegram.api.upload.base.file.TLAbsFile;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
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

    private static final int FLAG_PRECISE           = 0x00000001; // 0
    private static final int FLAG_CDN_SUPPORTED     = 0x00000002; // 1

    private TLAbsInputFileLocation location;
    private int offset;
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

    @Override
    public TLAbsFile deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if (res instanceof TLAbsFile)
            return (TLAbsFile) res;
        throw new IOException("Incorrect response type. Expected " + TLAbsFile.class.getCanonicalName()
                + ", got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public TLAbsInputFileLocation getLocation() {
        return this.location;
    }

    /**
     * Sets location.
     *
     * @param value the value
     */
    public final void setLocation(TLAbsInputFileLocation value) {
        this.location = value;
    }

    /**
     * Gets offset.
     *
     * @return the offset
     */
    public int getOffset() {
        return this.offset;
    }

    /**
     * Sets offset.
     *
     * @param value the value
     */
    public final void setOffset(int value) {
        this.offset = value;
    }

    /**
     * Gets limit.
     *
     * @return the limit
     */
    public int getLimit() {
        return this.limit;
    }

    /**
     * Sets limit.
     *
     * @param value the value
     */
    public final void setLimit(int value) {
        this.limit = value;
    }

    /**
     * Gets flags.
     *
     * @return the flags
     */
    public int getFlags() {
        return this.flags;
    }

    /**
     * Sets flags.
     *
     * @param flags the flags
     */
    public void setFlags(int flags) {
        this.flags = flags;
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
    public String toString() {
        return "upload.getFile#b15a9afc";
    }

}