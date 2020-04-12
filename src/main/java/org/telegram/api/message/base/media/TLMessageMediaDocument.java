package org.telegram.api.message.base.media;

import org.telegram.api.document.base.TLAbsDocument;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Document (video, audio, voice, sticker, any media type except photo)
 * messageMediaDocument#9cb070d7 flags:# document:flags.0?Document ttl_seconds:flags.2?int = MessageMedia;
 */
public class TLMessageMediaDocument extends TLAbsMessageMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9cb070d7;

    private static final int FLAG_DOCUMENT      = 0x00000001; // 0 : Attached document
    private static final int FLAG_TTL_SECONDS   = 0x00000004; // 2 : Time to live of self-destructing document
    
    /**
     * Attached document
     */
    protected TLAbsDocument document;
    
    /**
     * Time to live of self-destructing document
     */
    private int ttlSeconds;

    public TLMessageMediaDocument() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean hasDocument() {
        return (this.isFlagSet(FLAG_DOCUMENT));
    }
    
    public TLAbsDocument getAbsDocument() {
        return this.document;
    }

    public void setDocument(TLAbsDocument document) {
        this.document = document;
        this.setFlag(FLAG_DOCUMENT, this.document != null);
    }

    public boolean hasTtlSeconds() {
        return (this.isFlagSet(FLAG_TTL_SECONDS));
    }
    
    public int getTtlSeconds() {
        return this.ttlSeconds;
    }
    
    public void setTtlSeconds(int value) {
        this.ttlSeconds = value;
        this.setFlag(FLAG_TTL_SECONDS, this.ttlSeconds != 0);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if (this.hasDocument()) {
            StreamingUtils.writeTLObject(this.document, stream);
        }
        if (this.hasTtlSeconds()) {
            StreamingUtils.writeInt(this.ttlSeconds, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.hasDocument()) {
            this.document = StreamingUtils.readTLObject(stream, context, TLAbsDocument.class);
        }
        if (this.hasTtlSeconds()) {
            this.ttlSeconds = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "messageMediaDocument#9cb070d7";
    }
    
}