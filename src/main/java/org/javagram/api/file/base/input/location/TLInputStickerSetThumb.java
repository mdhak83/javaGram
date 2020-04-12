package org.javagram.api.file.base.input.location;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.sticker.base.input.set.TLAbsInputStickerSet;

/**
 * Location of stickerset thumbnail (see @see <a href="https://core.telegram.org/api/files">files</a>)
 * inputStickerSetThumb#dbaeae9 stickerset:InputStickerSet volume_id:long local_id:int = InputFileLocation;
 */
public class TLInputStickerSetThumb extends TLAbsInputFileLocation {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xdbaeae9;

    /**
     * Sticker set
     */
    private TLAbsInputStickerSet stickerset;

    /**
     * Volume ID from @see <a href="https://core.telegram.org/type/FileLocation">FileLocation</a> met in the profile photo container.
     */
    private long volumeId;

    /**
     * Local ID from @see <a href="https://core.telegram.org/type/FileLocation">FileLocation</a> met in the profile photo container.
     */
    private int localId;

    public TLInputStickerSetThumb() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputStickerSet getStickerset() {
        return stickerset;
    }

    public void setStickerset(TLAbsInputStickerSet stickerset) {
        this.stickerset = stickerset;
    }

    public long getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(long volumeId) {
        this.volumeId = volumeId;
    }

    public int getLocalId() {
        return localId;
    }

    public void setLocalId(int localId) {
        this.localId = localId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.stickerset, stream);
        StreamingUtils.writeLong(this.volumeId, stream);
        StreamingUtils.writeInt(this.localId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.stickerset = StreamingUtils.readTLObject(stream, context, TLAbsInputStickerSet.class);
        this.volumeId = StreamingUtils.readLong(stream);
        this.localId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "inputStickerSetThumb#dbaeae9";
    }

}