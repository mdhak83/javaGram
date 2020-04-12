package org.telegram.api.update.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;

/**
 * A language pack has changed, the client should manually fetch the changed strings using @see <a href="https://core.telegram.org/method/langpack.getDifference">langpack.getDifference</a>
 * updateLangPackTooLong#46560264 lang_code:string = Update;
 */
public class TLUpdateLangPackTooLong extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x46560264;

    /**
     * Language code
     */
    private String langCode;

    public TLUpdateLangPackTooLong() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.langCode, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.langCode = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "updateLangPackTooLong#46560264";
    }

}
