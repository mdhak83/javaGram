package org.javagram.api.messages.functions;

import org.javagram.api.message.base.media.TLAbsMessageMedia;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.message.base.entity.TLAbsMessageEntity;
import org.javagram.api._primitives.TLVector;

/**
 * Get preview of webpage
 * messages.getWebPagePreview#8b68b0cc flags:# message:string entities:flags.3?Vector&lt;MessageEntity&gt; = MessageMedia;
 */
public class TLRequestMessagesGetWebPagePreview extends TLMethod<TLAbsMessageMedia> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8b68b0cc;

    private static final int FLAG_ENTITIES  = 0x00000008; // 3 : Message entities for styled text

    /**
     * Message from which to extract the preview
     */
    private String message;
    
    /**
     * @see <a href="https://core.telegram.org/api/entities">Message entities for styled text</a>
     */
    private TLVector<TLAbsMessageEntity> entities;

    public TLRequestMessagesGetWebPagePreview() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean hasEntities() {
        return (this.isFlagSet(FLAG_ENTITIES));
    }
    
    public TLVector<TLAbsMessageEntity> getEntities() {
        return entities;
    }

    public void setEntities(TLVector<TLAbsMessageEntity> entities) {
        this.entities = entities;
        this.setFlag(FLAG_ENTITIES, this.entities != null && !this.entities.isEmpty());
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.message, stream);
        StreamingUtils.writeTLVector(this.entities, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.message = StreamingUtils.readTLString(stream);
        this.entities = StreamingUtils.readTLVector(stream, context, TLAbsMessageEntity.class);
    }

    @Override
    public TLAbsMessageMedia deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsMessageMedia) {
            return (TLAbsMessageMedia) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.message.media.TLAbsMessageMedia, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getWebPagePreview#8b68b0cc";
    }

}