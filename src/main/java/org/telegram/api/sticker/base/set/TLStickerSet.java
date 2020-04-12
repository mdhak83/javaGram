package org.telegram.api.sticker.base.set;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.photo.base.size.TLAbsPhotoSize;

/**
 * Represents a stickerset (stickerpack)
 */
public class TLStickerSet extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xeeb46f27;

    private static final int FLAG_INSTALLED_DATE    = 0x00000001; // 0
    private static final int FLAG_ARCHIVED          = 0x00000002; // 1
    private static final int FLAG_OFFICIAL          = 0x00000004; // 2
    private static final int FLAG_MASKS             = 0x00000008; // 3
    private static final int FLAG_THUMB             = 0x00000010; // 4
    private static final int FLAG_ANIMATED          = 0x00000020; // 5

    private int installedDate;
    private long id;
    private long accessHash;
    private String title;
    private String shortName;
    private TLAbsPhotoSize thumb;
    private int thumbDcId;
    private int count;
    private int hash;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(int installedDate) {
        this.installedDate = installedDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public TLAbsPhotoSize getThumb() {
        return thumb;
    }

    public void setThumb(TLAbsPhotoSize thumb) {
        this.thumb = thumb;
    }

    public int getThumbDcId() {
        return thumbDcId;
    }

    public void setThumbDcId(int thumbDcId) {
        this.thumbDcId = thumbDcId;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public long getId() {
        return id;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public String getTitle() {
        return title;
    }

    public String getShortName() {
        return shortName;
    }

    public int getCount() {
        return count;
    }

    public int getHash() {
        return hash;
    }

    public boolean isInstalled() {
        return (flags & FLAG_INSTALLED_DATE) != 0;
    }

    public boolean isArchived() {
        return (flags & FLAG_ARCHIVED) != 0;
    }

    public boolean isOffcial() {
        return (flags & FLAG_OFFICIAL) != 0;
    }

    public boolean isMask() {
        return (flags & FLAG_MASKS) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if ((this.flags & FLAG_INSTALLED_DATE) != 0) {
            StreamingUtils.writeInt(this.installedDate, stream);
        }
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeTLString(this.shortName, stream);
        if ((this.flags & FLAG_THUMB) != 0) {
            StreamingUtils.writeTLObject(this.thumb, stream);
            StreamingUtils.writeInt(this.thumbDcId, stream);
        }
        StreamingUtils.writeInt(this.count, stream);
        StreamingUtils.writeInt(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_INSTALLED_DATE) != 0) {
            this.installedDate = StreamingUtils.readInt(stream);
        }
        this.id = StreamingUtils.readLong(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.title = StreamingUtils.readTLString(stream);
        this.shortName = StreamingUtils.readTLString(stream);
        if ((this.flags & FLAG_THUMB) != 0) {
            this.thumb = StreamingUtils.readTLObject(stream, context, TLAbsPhotoSize.class);
            this.thumbDcId = StreamingUtils.readInt(stream);
        }
        this.count = StreamingUtils.readInt(stream);
        this.hash = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "stickerSet#eeb46f27";
    }

}
