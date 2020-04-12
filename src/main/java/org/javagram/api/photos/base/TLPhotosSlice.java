package org.javagram.api.photos.base;

import org.javagram.api.photo.base.TLAbsPhoto;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL photos slice.
 */
public class TLPhotosSlice extends TLAbsPhotos {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x15051f54;

    /**
     * The Count.
     */
    protected int count;

    public TLPhotosSlice() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.count, stream);
        StreamingUtils.writeTLVector(this.photos, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.count = StreamingUtils.readInt(stream);
        this.photos = StreamingUtils.readTLVector(stream, context, TLAbsPhoto.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "photos.photosSlice#15051f54";
    }

}