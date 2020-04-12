package org.javagram.api.photo.base;

import org.javagram.api.photo.base.size.TLAbsPhotoSize;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;

/**
 * The type TL photo.
 */
public class TLPhoto extends TLAbsPhoto {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd07504a5;

    private static final int FLAG_STICKERS = 0x00000001; // 0

    private long id;
    private long accessHash;
    private TLBytes fileReference;
    private int date;
    private TLVector<TLAbsPhotoSize> sizes;
    private int dcId;

    public TLPhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets access hash.
     *
     * @return the access hash
     */
    public long getAccessHash() {
        return this.accessHash;
    }

    /**
     * Sets access hash.
     *
     * @param accessHash the access hash
     */
    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public int getDate() {
        return this.date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(int date) {
        this.date = date;
    }

    /**
     * Gets sizes.
     *
     * @return the sizes
     */
    public TLVector<TLAbsPhotoSize> getSizes() {
        return this.sizes;
    }

    /**
     * Sets sizes.
     *
     * @param sizes the sizes
     */
    public void setSizes(TLVector<TLAbsPhotoSize> sizes) {
        this.sizes = sizes;
    }

    public TLBytes getFileReference() {
        return fileReference;
    }

    public void setFileReference(TLBytes fileReference) {
        this.fileReference = fileReference;
    }

    public int getDcId() {
        return dcId;
    }

    public void setDcId(int dcId) {
        this.dcId = dcId;
    }

    public boolean hasStickers() {
        return (flags & FLAG_STICKERS) != 0;
    }
    
    public TLAbsPhotoSize getSmallestPhotoSize() {
        TLAbsPhotoSize ret = null;
        if (this.sizes != null) {
            final String _sizes = "sabmcxdyw";
            int previousIndex = -1;
            for (TLAbsPhotoSize ps : this.sizes) {
                int index = _sizes.indexOf(ps.getType());
                if (index != -1 && index < previousIndex) {
                    previousIndex = index;
                    ret = ps;
                }
            }
        }
        return ret;
    }
    public TLAbsPhotoSize getBiggestPhotoSize() {
        TLAbsPhotoSize ret = null;
        if (this.sizes != null) {
            final String _sizes = "wydxcmbas";
            int previousIndex = Integer.MAX_VALUE;
            for (TLAbsPhotoSize ps : this.sizes) {
                int index = _sizes.indexOf(ps.getType());
                if (index != -1 && index < previousIndex) {
                    previousIndex = index;
                    ret = ps;
                }
            }
        }
        return ret;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeTLBytes(this.fileReference, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLObject(this.sizes, stream);
        StreamingUtils.writeInt(this.dcId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readLong(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.fileReference = StreamingUtils.readTLBytes(stream, context);
        this.date = StreamingUtils.readInt(stream);
        this.sizes = StreamingUtils.readTLVector(stream, context, TLAbsPhotoSize.class);
        this.dcId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "photo#d07504a5";
    }

}
