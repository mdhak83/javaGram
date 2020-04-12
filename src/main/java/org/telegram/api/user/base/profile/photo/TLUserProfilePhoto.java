package org.telegram.api.user.base.profile.photo;

import org.telegram.api.file.base.location.TLAbsFileLocation;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL user profile photo.
 */
public class TLUserProfilePhoto extends TLAbsUserProfilePhoto {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xecd75d8c;

    private long photoId;
    private TLAbsFileLocation photoSmall;
    private TLAbsFileLocation photoBig;
    private int dcId;

    public TLUserProfilePhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets photo id.
     *
     * @return the photo id
     */
    public long getPhotoId() {
        return this.photoId;
    }

    /**
     * Sets photo id.
     *
     * @param photoId the photo id
     */
    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

    /**
     * Gets photo small.
     *
     * @return the photo small
     */
    public TLAbsFileLocation getPhotoSmall() {
        return this.photoSmall;
    }

    /**
     * Sets photo small.
     *
     * @param photoSmall the photo small
     */
    public void setPhotoSmall(TLAbsFileLocation photoSmall) {
        this.photoSmall = photoSmall;
    }

    /**
     * Gets photo big.
     *
     * @return the photo big
     */
    public TLAbsFileLocation getPhotoBig() {
        return this.photoBig;
    }

    /**
     * Sets photo big.
     *
     * @param photoBig the photo big
     */
    public void setPhotoBig(TLAbsFileLocation photoBig) {
        this.photoBig = photoBig;
    }

    public int getDcId() {
        return dcId;
    }

    public void setDcId(int dcId) {
        this.dcId = dcId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.photoId, stream);
        StreamingUtils.writeTLObject(this.photoSmall, stream);
        StreamingUtils.writeTLObject(this.photoBig, stream);
        StreamingUtils.writeInt(this.dcId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.photoId = StreamingUtils.readLong(stream);
        this.photoSmall = StreamingUtils.readTLObject(stream, context, TLAbsFileLocation.class);
        this.photoBig = StreamingUtils.readTLObject(stream, context, TLAbsFileLocation.class);
        this.dcId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "userProfilePhoto#ecd75d8c";
    }

}