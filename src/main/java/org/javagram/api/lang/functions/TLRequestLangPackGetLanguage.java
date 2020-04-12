package org.javagram.api.lang.functions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api.lang.base.TLLangPackLanguage;
import org.javagram.utils.StreamingUtils;

/**
 * Get information about a language in a localization pack
 * langpack.getLanguage#6a596502 lang_pack:string lang_code:string = LangPackLanguage;
 */
public class TLRequestLangPackGetLanguage extends TLMethod<TLLangPackLanguage> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6a596502;

    /**
     * Language pack
     */
    private String langPack;

    /**
     * Language code
     */
    private String langCode;

    public TLRequestLangPackGetLanguage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getLangPack() {
        return langPack;
    }

    public void setLangPack(String langPack) {
        this.langPack = langPack;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.langPack, stream);
        StreamingUtils.writeTLString(this.langCode, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.langPack = StreamingUtils.readTLString(stream);
        this.langCode = StreamingUtils.readTLString(stream);
    }

    @Override
    public TLLangPackLanguage deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLLangPackLanguage) {
            return (TLLangPackLanguage) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "langpack.getLanguage#6a596502";
    }

}