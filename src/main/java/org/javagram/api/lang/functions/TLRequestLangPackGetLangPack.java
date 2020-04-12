package org.javagram.api.lang.functions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api.lang.base.TLLangPackDifference;
import org.javagram.utils.StreamingUtils;

/**
 * Get localization pack strings
 * langpack.getLangPack#f2f2330a lang_pack:string lang_code:string = LangPackDifference;
 */
public class TLRequestLangPackGetLangPack extends TLMethod<TLLangPackDifference> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf2f2330a;

    /**
     * Language pack name
     */
    private String langPack;

    /**
     * Language code
     */
    private String langCode;

    public TLRequestLangPackGetLangPack() {
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
    public TLLangPackDifference deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLLangPackDifference) {
            return (TLLangPackDifference) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "langpack.getLangPack#f2f2330a";
    }

}