package org.telegram.api.lang.functions;

import org.telegram.api._primitives.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.lang.base.TLLangPackDifference;
import org.telegram.utils.StreamingUtils;

/**
 * Get new strings in languagepack
 * langpack.getDifference#cd984aa5 lang_pack:string lang_code:string from_version:int = LangPackDifference;
 */
public class TLRequestLangPackGetDifference extends TLMethod<TLLangPackDifference> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xcd984aa5;

    /**
     * Language pack
     */
    private String langPack;

    /**
     * Language code
     */
    private String langCode;

    /**
     * Previous localization pack version
     */
    private int fromVersion;

    public TLRequestLangPackGetDifference() {
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

    public int getFromVersion() {
        return fromVersion;
    }

    public void setFromVersion(int fromVersion) {
        this.fromVersion = fromVersion;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.langPack, stream);
        StreamingUtils.writeTLString(this.langCode, stream);
        StreamingUtils.writeInt(this.fromVersion, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.langPack = StreamingUtils.readTLString(stream);
        this.langCode = StreamingUtils.readTLString(stream);
        this.fromVersion = StreamingUtils.readInt(stream);
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
        return "langpack.getDifference#cd984aa5";
    }

}