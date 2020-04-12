package org.telegram.api.input.media;

import org.telegram.api.document.base.input.TLAbsInputDocument;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Forwarded document.
 */
public class TLInputMediaDocument extends TLAbsInputMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x23ab23d2;

    private static final int FLAG_TTL_SECONDS   = 0x00000001; // 0 : Time to live of self-destructing document

    /**
     * The document to be forwarded.
     */
    private TLAbsInputDocument id;
    
    /**
     * Time to live of self-destructing document
     */
    private int ttlSeconds;

    public TLInputMediaDocument() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputDocument getId() {
        return this.id;
    }

    public void setId(TLAbsInputDocument value) {
        this.id = value;
    }
    
    public boolean hasTtlSeconds() {
        return this.isFlagSet(FLAG_TTL_SECONDS);
    }
    
    public int getTtlSeconds() {
        return this.ttlSeconds;
    }
    
    public void setTtlSeconds(int value) {
        this.ttlSeconds = value;
        this.setFlag(FLAG_TTL_SECONDS, value != 0);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.id, stream);
        if (this.hasTtlSeconds()) {
            StreamingUtils.writeInt(this.ttlSeconds, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readTLObject(stream, context, TLAbsInputDocument.class);
        if (this.hasTtlSeconds()) {
            this.ttlSeconds = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "inputMediaDocument#23ab23d2";
    }

}