package org.telegram.api.secure.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.secure.base.valuetype.TLAbsSecureValueType;

/**
 * Required type
 * secureRequiredType#829d99da flags:# native_names:flags.0?true selfie_required:flags.1?true translation_required:flags.2?true type:SecureValueType = SecureRequiredType;
 */
public class TLSecureRequiredType extends TLAbsSecureRequiredType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x829d99da;

    private static final int FLAG_NATIVE_NAMES          = 0x00000001; // 0 : 
    private static final int FLAG_SELFIE_REQUIRED       = 0x00000002; // 1 : 
    private static final int FLAG_TRANSLATION_REQUIRED  = 0x00000004; // 2 : 
    
    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;
    
    /**
     * Secure @see <a href="https://core.telegram.org/passport">passport</a> value type
     */
    private TLAbsSecureValueType type;
    

    public TLSecureRequiredType() {
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

    public TLAbsSecureValueType getType() {
        return type;
    }

    public void setType(TLAbsSecureValueType type) {
        this.type = type;
    }

    public boolean areNamesNative() {
        return this.isFlagSet(FLAG_NATIVE_NAMES);
    }
    
    public void setNamesNative(boolean value) {
        this.setFlag(FLAG_NATIVE_NAMES, value);
    }
    
    public boolean isSelfieRequired() {
        return this.isFlagSet(FLAG_SELFIE_REQUIRED);
    }
    
    public void setSelfieRequired(boolean value) {
        this.setFlag(FLAG_SELFIE_REQUIRED, value);
    }
    
    public boolean isTranslationRequired() {
        return this.isFlagSet(FLAG_TRANSLATION_REQUIRED);
    }
    
    public void setTranslationRequired(boolean value) {
        this.setFlag(FLAG_TRANSLATION_REQUIRED, value);
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.type, stream);
   }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.type = StreamingUtils.readTLObject(stream, context, TLAbsSecureValueType.class);
    }

    @Override
    public String toString() {
        return "secureRequiredType#829d99da";
    }

}