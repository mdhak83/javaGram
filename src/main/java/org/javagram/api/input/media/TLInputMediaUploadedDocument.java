package org.javagram.api.input.media;

import org.javagram.api.document.base.attribute.TLAbsDocumentAttribute;
import org.javagram.api.document.base.input.TLAbsInputDocument;
import org.javagram.api.file.base.input.TLAbsInputFile;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * New document
*/
public class TLInputMediaUploadedDocument extends TLAbsInputMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5b38c6c1;

    private static final int FLAG_STICKERS          = 0x00000001; // 0 : Attached stickers
    private static final int FLAG_TTL_SECONDS       = 0x00000002; // 1 : Time to live in seconds of self-destructing document
    private static final int FLAG_THUMB             = 0x00000004; // 2 : Thumbnail of the document, uploaded as for the file
    private static final int FLAG_NOSOUND_VIDEO     = 0x00000008; // 3 : Whether the specified document is a video file with no audio tracks (a GIF animation (even as MPEG4), for example)

    /**
     * The @see <a href="https://core.telegram.org/api/files">uploaded file</a>
     */
    private TLAbsInputFile file;
    
    /**
     * Thumbnail of the document, uploaded as for the file
     */
    private TLAbsInputFile thumb;
    
    /**
     * MIME type of document
     */
    private String mimeType;
    
    /**
     * Attributes that specify the type of the document (video, audio, voice, sticker, etc.)
     */
    private TLVector<TLAbsDocumentAttribute> attributes = new TLVector<>();
    
    /**
     * Attached stickers
     */
    private TLVector<TLAbsInputDocument> stickers;
    
    /**
     * Time to live in seconds of self-destructing document
     */
    private int ttlSeconds;

    public TLInputMediaUploadedDocument() {
        super();
        this.mimeType = "";
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
     * @param file the file
     */
    public void setFile(TLAbsInputFile file) {
        this.file = file;
    }

    /**
     * Gets attributes.
     *
     * @return the attributes
     */
    public TLVector<TLAbsDocumentAttribute> getAttributes() {
        return this.attributes;
    }

    /**
     * Sets attributes.
     *
     * @param attributes the attributes
     */
    public void setAttributes(TLVector<TLAbsDocumentAttribute> attributes) {
        this.attributes = attributes;
    }

    /**
     * Gets mime type.
     *
     * @return the mime type
     */
    public String getMimeType() {
        return this.mimeType;
    }

    /**
     * Sets mime type.
     *
     * @param mimeType the mime type
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
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

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.file, stream);
        if ((this.flags & FLAG_THUMB) != 0) {
            StreamingUtils.writeTLObject(this.thumb, stream);
        }
        StreamingUtils.writeTLString(this.mimeType, stream);
        StreamingUtils.writeTLObject(this.attributes, stream);
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
        if ((this.flags & FLAG_THUMB) != 0) {
            this.thumb = StreamingUtils.readTLObject(stream, context, TLAbsInputFile.class);
        }
        this.mimeType = StreamingUtils.readTLString(stream);
        this.attributes = StreamingUtils.readTLVector(stream, context, TLAbsDocumentAttribute.class);
        if ((this.flags & FLAG_STICKERS) != 0) {
            this.stickers = StreamingUtils.readTLVector(stream, context, TLAbsInputDocument.class);
        }
        if ((this.flags & FLAG_TTL_SECONDS) != 0) {
            this.ttlSeconds = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "inputMediaUploadedDocument#5b38c6c1";
    }

}