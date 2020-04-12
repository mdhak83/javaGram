package org.telegram.api.lang.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Translated localization string
 * langPackString#cad181f6 key:string value:string = LangPackString;
 */
public class TLLangPackString extends TLAbsLangPackString {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xcad181f6;

    /**
     * Language key
     */
    private String key;
    
    /**
     * Value
     */
    private String value;
    
    public TLLangPackString() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.key, stream);
        StreamingUtils.writeTLString(this.value, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.key = StreamingUtils.readTLString(stream);
        this.value = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "langPackString#cad181f6";
    }

}
