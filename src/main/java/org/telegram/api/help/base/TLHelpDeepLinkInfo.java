package org.telegram.api.help.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.message.base.entity.TLAbsMessageEntity;
import org.telegram.api._primitives.TLVector;

/**
 * Deep linking info
 * help.deepLinkInfo#6a4ee832 flags:# update_app:flags.0?true message:string entities:flags.1?Vector&lt;MessageEntity&gt; = help.DeepLinkInfo;
 */
public class TLHelpDeepLinkInfo extends TLAbsHelpDeepLinkInfo {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6a4ee832;

    private static final int FLAG_UPDATE_APP    = 0x00000001; // 0
    private static final int FLAG_ENTITIES      = 0x00000002; // 1

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;
    
    /**
     * Message to show to the user
     */
    private String message;
    
    /**
     * @see <a href="https://core.telegram.org/api/entities">Message entities for styled text</a>
     */
    private TLVector<TLAbsMessageEntity> entities;
    
    public TLHelpDeepLinkInfo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }
    
    public boolean isAppUpdateRequired() {
        return this.isFlagSet(FLAG_UPDATE_APP);
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
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.message, stream);
        if (this.hasEntities()) {
            StreamingUtils.writeTLVector(this.entities, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.message = StreamingUtils.readTLString(stream);
        if (this.hasEntities()) {
            this.entities = StreamingUtils.readTLVector(stream, context, TLAbsMessageEntity.class);
        }
    }

    @Override
    public String toString() {
        return "help.deepLinkInfo#6a4ee832";
    }

}