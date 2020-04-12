package org.telegram.api.help.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.json.base.TLDataJSON;
import org.telegram.api.message.base.entity.TLAbsMessageEntity;
import org.telegram.api._primitives.TLVector;

/**
 * Info about the latest telegram Terms Of Service
 * help.termsOfService#780a0310 flags:# popup:flags.0?true id:DataJSON text:string entities:Vector&lt;MessageEntity&gt; min_age_confirm:flags.1?int = help.TermsOfService;
 */
public class TLHelpTermsOfService extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x780a0310;

    private static final int FLAG_POPUP             = 0x00000001; // 0 : Whether a prompt must be showed to the user, in order to accept the new terms.
    private static final int FLAG_MIN_AGE_CONFIRM   = 0x00000002; // 1 : Minimum age required to sign up to telegram, the user must confirm that they is older than the minimum age.

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;
    
    /**
     * ID of the new terms
     */
    private TLDataJSON id;
    
    /**
     * Text of the new terms
     */
    private String text;
    
    /**
     * @see <a href="https://core.telegram.org/api/entities">Message entities for styled text</a>
     */
    private TLVector<TLAbsMessageEntity> entities;
    
    /**
     * Minimum age required to sign up to telegram, the user must confirm that they is older than the minimum age.
     */
    private int minAgeConfirm;

    public TLHelpTermsOfService() {
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

    /**
     * 
     * @return True if a prompt must be showed to the user, in order to accept the new terms.
     */
    public boolean isPopup() {
        return this.isFlagSet(FLAG_POPUP);
    }
    
    public void setPopup(boolean value) {
        this.setFlag(FLAG_POPUP, value);
    }
    
    public TLDataJSON getId() {
        return id;
    }

    public void setId(TLDataJSON id) {
        this.id = id;
    }

    public TLVector<TLAbsMessageEntity> getEntities() {
        return entities;
    }

    public void setEntities(TLVector<TLAbsMessageEntity> entities) {
        this.entities = entities;
    }

    /**
     * 
     * @return True if a prompt must be showed to the user, in order to accept the new terms.
     */
    public boolean hasMinAgeConfirm() {
        return this.isFlagSet(FLAG_MIN_AGE_CONFIRM);
    }
    
    public int getMinAgeConfirm() {
        return minAgeConfirm;
    }

    public void setMinAgeConfirm(int minAgeConfirm) {
        this.minAgeConfirm = minAgeConfirm;
        if (this.minAgeConfirm != 0) {
            this.flags |= FLAG_MIN_AGE_CONFIRM;
        } else {
            this.flags &= ~FLAG_MIN_AGE_CONFIRM;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.id, stream);
        StreamingUtils.writeTLString(this.text, stream);
        StreamingUtils.writeTLVector(this.entities, stream);
        if (this.hasMinAgeConfirm()) {
            StreamingUtils.writeInt(this.minAgeConfirm, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readTLObject(stream, context, TLDataJSON.class);
        this.text = StreamingUtils.readTLString(stream);
        this.entities = StreamingUtils.readTLVector(stream, context, TLAbsMessageEntity.class);
        if (this.hasMinAgeConfirm()) {
            this.minAgeConfirm = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "help.termsOfService#780a0310";
    }

}