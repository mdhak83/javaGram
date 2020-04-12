package org.javagram.api.lang.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;

/**
 * Changes to the app's localization pack
 * langPackDifference#f385c1f6 lang_code:string from_version:int version:int strings:Vector&lt;LangPackString&gt; = LangPackDifference;
 */
public class TLLangPackDifference extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf385c1f6;

    /**
     * Language code
     */
    private String langCode;
    
    /**
     * Previous version number
     */
    private int fromVersion;
    
    /**
     * New version number
     */
    private int version;
    
    /**
     * Localized strings
     */
    private TLVector<TLAbsLangPackString> strings;
    
    public TLLangPackDifference() {
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

    public int getFromVersion() {
        return fromVersion;
    }

    public void setFromVersion(int fromVersion) {
        this.fromVersion = fromVersion;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public TLVector<TLAbsLangPackString> getStrings() {
        return strings;
    }

    public void setStrings(TLVector<TLAbsLangPackString> strings) {
        this.strings = strings;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.langCode, stream);
        StreamingUtils.writeInt(this.fromVersion, stream);
        StreamingUtils.writeInt(this.version, stream);
        StreamingUtils.writeTLVector(this.strings, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.langCode = StreamingUtils.readTLString(stream);
        this.fromVersion = StreamingUtils.readInt(stream);
        this.version = StreamingUtils.readInt(stream);
        this.strings = StreamingUtils.readTLVector(stream, context, TLAbsLangPackString.class);
    }

    @Override
    public String toString() {
        return "langPackDifference#f385c1f6";
    }

}
