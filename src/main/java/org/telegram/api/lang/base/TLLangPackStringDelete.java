package org.telegram.api.lang.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Deleted localization string
 * langPackStringDeleted#2979eeb2 key:string = LangPackString;
 */
public class TLLangPackStringDelete extends TLAbsLangPackString {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2979eeb2;

    /**
     * Language key
     */
    private String key;
    
    public TLLangPackStringDelete() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.key, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.key = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "langPackStringDeleted#2979eeb2";
    }

}