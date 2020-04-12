package org.telegram.api.input;

import org.telegram.api.input.media.*;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.message.base.entity.TLAbsMessageEntity;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;

/**
 * A single media in an album sent with @see <a href="https://core.telegram.org/method/messages.sendMultiMedia">messages.sendMultiMedia</a>.
 * inputSingleMedia#1cc6e91f flags:# media:InputMedia random_id:long message:string entities:flags.0?Vector&lt;MessageEntity&gt; = InputSingleMedia;
 */
public class TLInputSingleMedia extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1cc6e91f;

    private static final int FLAG_ENTITIES  = 0x00000001; // 0

    /**
     * The media
     */
    private TLAbsInputMedia media;
    
    /**
     * Unique client media ID required to prevent message resending
     */
    private long randomId;
    
    /**
     * A caption for the media
     */
    private String message;
    
    /**
     * Message @see <a href="https://core.telegram.org/api/entities">entities</a> for styled text
     */
    private TLVector<TLAbsMessageEntity> entities;

    public TLInputSingleMedia() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputMedia getMedia() {
        return media;
    }

    public void setMedia(TLAbsInputMedia media) {
        this.media = media;
    }

    public long getRandomId() {
        return randomId;
    }

    public void setRandomId(long randomId) {
        this.randomId = randomId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean hasEntities() {
        return this.isFlagSet(FLAG_ENTITIES);
    }
    
    public TLVector<TLAbsMessageEntity> getEntities() {
        return entities;
    }

    public void setEntities(TLVector<TLAbsMessageEntity> entities) {
        this.entities = entities;
        if (this.entities != null && !this.entities.isEmpty()) {
            this.flags |= FLAG_ENTITIES;
        } else {
            this.flags &= ~FLAG_ENTITIES;
        }
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.media, stream);
        StreamingUtils.writeLong(this.randomId, stream);
        StreamingUtils.writeTLString(this.message, stream);
        if (this.hasEntities()) {
            StreamingUtils.writeTLVector(this.entities, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.media = StreamingUtils.readTLObject(stream, context, TLAbsInputMedia.class);
        this.randomId = StreamingUtils.readLong(stream);
        this.message = StreamingUtils.readTLString(stream);
        if (this.hasEntities()) {
            this.entities = StreamingUtils.readTLVector(stream, context, TLAbsMessageEntity.class);
        }
    }

    @Override
    public String toString() {
        return "inputSingleMedia#1cc6e91f";
    }

}