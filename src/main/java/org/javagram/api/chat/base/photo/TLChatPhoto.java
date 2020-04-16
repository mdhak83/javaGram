package org.javagram.api.chat.base.photo;

import org.javagram.api.file.base.location.TLAbsFileLocation;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represent a chat photo
 */
public class TLChatPhoto extends TLAbsChatPhoto {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x475cdbd5;

    private TLAbsFileLocation photoSmall; ///< Location of the file corresponding to the small thumbnail for group profile photo
    private TLAbsFileLocation photoBig; ///< Location of the file corresponding to the big thumbnail for group profile photo
    private int dcId;

    public TLChatPhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets photo _ small.
     *
     * @return the photo _ small
     */
    public TLAbsFileLocation getPhotoSmall() {
        return this.photoSmall;
    }

    /**
     * Sets photo _ small.
     *
     * @param photoSmall the photo _ small
     */
    public void setPhotoSmall(TLAbsFileLocation photoSmall) {
        this.photoSmall = photoSmall;
    }

    /**
     * Gets photo _ big.
     *
     * @return the photo _ big
     */
    public TLAbsFileLocation getPhotoBig() {
        return this.photoBig;
    }

    /**
     * Sets photo _ big.
     *
     * @param photoBig the photo _ big
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
        StreamingUtils.writeTLObject(this.photoSmall, stream);
        StreamingUtils.writeTLObject(this.photoBig, stream);
        StreamingUtils.writeInt(this.dcId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.photoSmall = StreamingUtils.readTLObject(stream, context, TLAbsFileLocation.class);
        this.photoBig = StreamingUtils.readTLObject(stream, context, TLAbsFileLocation.class);
        this.dcId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "chatPhoto#475cdbd5";
    }

}