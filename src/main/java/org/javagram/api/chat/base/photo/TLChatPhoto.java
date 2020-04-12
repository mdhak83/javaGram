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

    private TLAbsFileLocation photo_small; ///< Location of the file corresponding to the small thumbnail for group profile photo
    private TLAbsFileLocation photo_big; ///< Location of the file corresponding to the big thumbnail for group profile photo
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
    public TLAbsFileLocation getPhoto_small() {
        return this.photo_small;
    }

    /**
     * Sets photo _ small.
     *
     * @param photo_small the photo _ small
     */
    public void setPhoto_small(TLAbsFileLocation photo_small) {
        this.photo_small = photo_small;
    }

    /**
     * Gets photo _ big.
     *
     * @return the photo _ big
     */
    public TLAbsFileLocation getPhoto_big() {
        return this.photo_big;
    }

    /**
     * Sets photo _ big.
     *
     * @param photo_big the photo _ big
     */
    public void setPhoto_big(TLAbsFileLocation photo_big) {
        this.photo_big = photo_big;
    }

    public int getDcId() {
        return dcId;
    }

    public void setDcId(int dcId) {
        this.dcId = dcId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.photo_small, stream);
        StreamingUtils.writeTLObject(this.photo_big, stream);
        StreamingUtils.writeInt(this.dcId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.photo_small = StreamingUtils.readTLObject(stream, context, TLAbsFileLocation.class);
        this.photo_big = StreamingUtils.readTLObject(stream, context, TLAbsFileLocation.class);
        this.dcId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "chatPhoto#475cdbd5";
    }

}