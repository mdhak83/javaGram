package org.javagram.api.photos.functions;

import org.javagram.api.user.base.input.TLAbsInputUser;
import org.javagram.api.photos.base.TLAbsPhotos;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request photos get user photos.
 */
public class TLRequestPhotosGetUserPhotos extends TLMethod<TLAbsPhotos> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x91cd32a8;

    private TLAbsInputUser userId;
    private int offset;
    private long maxId;
    private int limit;

    public TLRequestPhotosGetUserPhotos() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public TLAbsInputUser getUserId() {
        return this.userId;
    }

    /**
     * Sets user id.
     *
     * @param value the value
     */
    public void setUserId(TLAbsInputUser value) {
        this.userId = value;
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
    public void setOffset(int value) {
        this.offset = value;
    }

    /**
     * Gets max id.
     *
     * @return the max id
     */
    public long getMaxId() {
        return this.maxId;
    }

    /**
     * Sets max id.
     *
     * @param value the value
     */
    public void setMaxId(long value) {
        this.maxId = value;
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
    public void setLimit(int value) {
        this.limit = value;
    }

    @Override
    public TLAbsPhotos deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsPhotos) {
            return (TLAbsPhotos) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsPhotos.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.userId, stream);
        StreamingUtils.writeInt(this.offset, stream);
        StreamingUtils.writeLong(this.maxId, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
        this.offset = StreamingUtils.readInt(stream);
        this.maxId = StreamingUtils.readLong(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "photos.getUserPhotos#91cd32a8";
    }

}