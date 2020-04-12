package org.telegram.api.lang.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A language pack string which has different forms based on the number of some object it mentions.
 * @see <a href="https://www.unicode.org/cldr/charts/latest/supplemental/language_plural_rules.html">See here for more info</a>
 * langPackStringPluralized#6c47ac9f flags:# key:string zero_value:flags.0?string one_value:flags.1?string two_value:flags.2?string few_value:flags.3?string many_value:flags.4?string other_value:string = LangPackString;
 */
public class TLLangPackStringPluralized extends TLAbsLangPackString {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6c47ac9f;

    private static final int FLAG_ZERO_VALUE    = 0x00000001; // 0 : Value for zero object
    private static final int FLAG_ONE_VALUE     = 0x00000002; // 1 : Value for one object
    private static final int FLAG_TWO_VALUE     = 0x00000004; // 2 : Value for two objects
    private static final int FLAG_FEW_VALUE     = 0x00000008; // 3 : Value for a few objects
    private static final int FLAG_MANY_VALUE    = 0x00000010; // 4 : Value for many objects

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;
    
    /**
     * Localization key
     */
    private String key;
    
    /**
     * Value for zero object
     */
    private String zeroValue;
    
    /**
     * Value for one object
     */
    private String oneValue;
    
    /**
     * Value for two objects
     */
    private String twoValue;
    
    /**
     * Value for a few objects
     */
    private String fewValue;
    
    /**
     * Value for many objects
     */
    private String manyValue;
    
    /**
     * Default value
     */
    private String otherValue;
    
    public TLLangPackStringPluralized() {
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean hasZeroValue() {
        return this.isFlagSet(FLAG_ZERO_VALUE);
    }
    
    public String getZeroValue() {
        return zeroValue;
    }

    public void setZeroValue(String zeroValue) {
        this.zeroValue = zeroValue;
        if (this.zeroValue != null && !this.zeroValue.trim().isEmpty()) {
            this.flags |= FLAG_ZERO_VALUE;
        } else {
            this.flags &= ~FLAG_ZERO_VALUE;
        }
    }

    public boolean hasOneValue() {
        return this.isFlagSet(FLAG_ONE_VALUE);
    }
    
    public String getOneValue() {
        return oneValue;
    }

    public void setOneValue(String oneValue) {
        this.oneValue = oneValue;
        if (this.oneValue != null && !this.oneValue.trim().isEmpty()) {
            this.flags |= FLAG_ONE_VALUE;
        } else {
            this.flags &= ~FLAG_ONE_VALUE;
        }
    }

    public boolean hasTwoValue() {
        return this.isFlagSet(FLAG_TWO_VALUE);
    }
    
    public String getTwoValue() {
        return twoValue;
    }

    public void setTwoValue(String twoValue) {
        this.twoValue = twoValue;
        if (this.twoValue != null && !this.twoValue.trim().isEmpty()) {
            this.flags |= FLAG_TWO_VALUE;
        } else {
            this.flags &= ~FLAG_TWO_VALUE;
        }
    }

    public boolean hasFewValue() {
        return this.isFlagSet(FLAG_FEW_VALUE);
    }
    
    public String getFewValue() {
        return fewValue;
    }

    public void setFewValue(String fewValue) {
        this.fewValue = fewValue;
        if (this.fewValue != null && !this.fewValue.trim().isEmpty()) {
            this.flags |= FLAG_FEW_VALUE;
        } else {
            this.flags &= ~FLAG_FEW_VALUE;
        }
    }

    public boolean hasManyValue() {
        return this.isFlagSet(FLAG_MANY_VALUE);
    }
    
    public String getManyValue() {
        return manyValue;
    }

    public void setManyValue(String manyValue) {
        this.manyValue = manyValue;
        if (this.manyValue != null && !this.manyValue.trim().isEmpty()) {
            this.flags |= FLAG_MANY_VALUE;
        } else {
            this.flags &= ~FLAG_MANY_VALUE;
        }
    }

    public String getOtherValue() {
        return otherValue;
    }

    public void setOtherValue(String otherValue) {
        this.otherValue = otherValue;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.key, stream);
        if (this.hasZeroValue()) {
            StreamingUtils.writeTLString(this.zeroValue, stream);
        }
        if (this.hasOneValue()) {
            StreamingUtils.writeTLString(this.oneValue, stream);
        }
        if (this.hasTwoValue()) {
            StreamingUtils.writeTLString(this.twoValue, stream);
        }
        if (this.hasFewValue()) {
            StreamingUtils.writeTLString(this.fewValue, stream);
        }
        if (this.hasManyValue()) {
            StreamingUtils.writeTLString(this.manyValue, stream);
        }
        StreamingUtils.writeTLString(this.otherValue, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.key = StreamingUtils.readTLString(stream);
        if (this.hasZeroValue()) {
            this.zeroValue = StreamingUtils.readTLString(stream);
        }
        if (this.hasOneValue()) {
            this.oneValue = StreamingUtils.readTLString(stream);
        }
        if (this.hasTwoValue()) {
            this.twoValue = StreamingUtils.readTLString(stream);
        }
        if (this.hasFewValue()) {
            this.fewValue = StreamingUtils.readTLString(stream);
        }
        if (this.hasManyValue()) {
            this.manyValue = StreamingUtils.readTLString(stream);
        }
        this.otherValue = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "langPackStringPluralized#6c47ac9f";
    }

}