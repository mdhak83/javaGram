package org.telegram.api.input.media;

import org.telegram.api.document.base.input.TLAbsInputDocument;
import org.telegram.api.file.base.input.TLAbsInputFile;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input media uploaded photo.
 */
public class TLInputMediaUploadedPhoto extends TLAbsInputMedia {

    public static final int CLASS_ID = 0x1e287d04;

    private static final int FLAG_STICKERS = 0x00000001; // 0
    private static final int FLAG_TTL_SECONDS = 0x00000002; // 1

    private TLAbsInputFile file;
    private TLVector<TLAbsInputDocument> stickers;
    private int ttlSeconds;

    public TLInputMediaUploadedPhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets file.
     *
     * @return the file
     */
    public TLAbsInputFile getFile() {
        return this.file;
    }

    /**
     * Sets file.
     *
     * @param value the value
     */
    public void setFile(TLAbsInputFile value) {
        this.file = value;
    }

    public TLVector<TLAbsInputDocument> getStickers() {
        return stickers;
    }

    public void setStickers(TLVector<TLAbsInputDocument> stickers) {
        if (stickers == null || stickers.isEmpty()) {
            this.flags &= ~FLAG_STICKERS;
        } else {
            this.flags |= FLAG_STICKERS;
        }
        this.stickers = stickers;
    }

    public int getTtlSeconds() {
        return this.ttlSeconds;
    }

    public void setTtlSeconds(int ttlSeconds) {
        this.ttlSeconds = ttlSeconds;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.file, stream);
        if ((this.flags & FLAG_STICKERS) != 0) {
            StreamingUtils.writeTLVector(this.stickers, stream);
        }
        if ((this.flags & FLAG_TTL_SECONDS) != 0) {
            StreamingUtils.writeInt(this.ttlSeconds, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.file = StreamingUtils.readTLObject(stream, context, TLAbsInputFile.class);
        if ((this.flags & FLAG_STICKERS) != 0) {
            this.stickers = StreamingUtils.readTLVector(stream, context, TLAbsInputDocument.class);
        }
        if ((this.flags & FLAG_TTL_SECONDS) != 0) {
            this.ttlSeconds = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "inputMediaUploadedPhoto#1e287d04";
    }

}
