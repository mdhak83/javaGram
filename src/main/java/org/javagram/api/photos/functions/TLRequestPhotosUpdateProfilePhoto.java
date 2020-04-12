package org.javagram.api.photos.functions;

import org.javagram.api.photo.base.input.TLAbsInputPhoto;
import org.javagram.api.user.base.profile.photo.TLAbsUserProfilePhoto;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request photos update profile photo.
 */
public class TLRequestPhotosUpdateProfilePhoto extends TLMethod<TLAbsUserProfilePhoto> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf0bb5152;

    private TLAbsInputPhoto id;

    public TLRequestPhotosUpdateProfilePhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAbsUserProfilePhoto deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if (res instanceof TLAbsUserProfilePhoto)
            return (TLAbsUserProfilePhoto) res;
        throw new IOException("Incorrect response type. Expected " + TLAbsUserProfilePhoto.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public TLAbsInputPhoto getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param value the value
     */
    public void setId(TLAbsInputPhoto value) {
        this.id = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readTLObject(stream, context, TLAbsInputPhoto.class);
    }

    @Override
    public String toString() {
        return "photos.updateProfilePhoto#f0bb5152";
    }

}