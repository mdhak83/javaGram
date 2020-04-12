package org.javagram.api.lang.functions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLStringVector;
import org.javagram.api._primitives.TLVector;
import org.javagram.api.lang.base.TLAbsLangPackString;
import org.javagram.utils.StreamingUtils;

/**
 * Get strings from a language pack
 * langpack.getStrings#efea3803 lang_pack:string lang_code:string keys:Vector&lt;string&gt; = Vector&lt;LangPackString&gt;;
 */
public class TLRequestLangPackGetStrings extends TLMethod<TLVector<TLAbsLangPackString>> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xefea3803;

    /**
     * Language pack name
     */
    private String langPack;

    /**
     * Language code
     */
    private String langCode;

    /**
     * Language code
     */
    private TLStringVector keys;

    public TLRequestLangPackGetStrings() {
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

    public TLStringVector getKeys() {
        return keys;
    }

    public void setKeys(TLStringVector keys) {
        this.keys = keys;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.langPack, stream);
        StreamingUtils.writeTLString(this.langCode, stream);
        StreamingUtils.writeTLVector(this.keys, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.langPack = StreamingUtils.readTLString(stream);
        this.langCode = StreamingUtils.readTLString(stream);
        this.keys = StreamingUtils.readTLStringVector(stream, context);
    }

    @Override
    public TLVector<TLAbsLangPackString> deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLVector) {
            return (TLVector<TLAbsLangPackString>) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "langpack.getStrings#efea3803";
    }

}