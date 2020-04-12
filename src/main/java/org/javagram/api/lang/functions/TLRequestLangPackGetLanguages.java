package org.javagram.api.lang.functions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import org.javagram.api.lang.base.TLLangPackLanguage;
import org.javagram.utils.StreamingUtils;

/**
 * Get information about all languages in a localization pack
 * langpack.getLanguages#42c6978f lang_pack:string = Vector&lt;LangPackLanguage&gt;;
 */
public class TLRequestLangPackGetLanguages extends TLMethod<TLVector<TLLangPackLanguage>> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x42c6978f;

    /**
     * Language pack name
     */
    private String langPack;

    public TLRequestLangPackGetLanguages() {
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

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.langPack, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.langPack = StreamingUtils.readTLString(stream);
    }

    @Override
    public TLVector<TLLangPackLanguage> deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLVector) {
            return (TLVector<TLLangPackLanguage>) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "langpack.getLanguages#42c6978f";
    }

}