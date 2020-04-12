package org.telegram.api.lang.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Identifies a localization pack
 * langPackLanguage#eeca5ce3 flags:# official:flags.0?true rtl:flags.2?true beta:flags.3?true name:string native_name:string lang_code:string base_lang_code:flags.1?string plural_code:string strings_count:int translated_count:int translations_url:string = LangPackLanguage;
 */
public class TLLangPackLanguage extends TLAbsLangPackString {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xeeca5ce3;

    private static final int FLAG_OFFICIAL          = 0x00000001; // 0 : Whether the language pack is official
    private static final int FLAG_BASE_LANG_CODE    = 0x00000002; // 1 : Identifier of a base language pack; may be empty. If a string is missed in the language pack, then it should be fetched from base language pack. Unsupported in custom language packs
    private static final int FLAG_RTL               = 0x00000004; // 2 : Is this a localization pack for an RTL language
    private static final int FLAG_BETA              = 0x00000008; // 3 : Is this a beta localization pack?

    /**
     * Language name
     */
    private String name;
    
    /**
     * Language name in the language itself
     */
    private String nativeName;
    
    /**
     * Language code (pack identifier)
     */
    private String langCode;
    
    /**
     * Identifier of a base language pack : may be empty.
     * If a string is missed in the language pack, then it should be fetched from base language pack.
     * Unsupported in custom language packs.
     */
    private String baseLangCode;
    
    /**
     * A language code to be used to apply plural forms.
     * @see <a href="https://www.unicode.org/cldr/charts/latest/supplemental/language_plural_rules.html">See for more info</a>
     */
    private String pluralCode;
    
    /**
     * Total number of non-deleted strings from the language pack
     */
    private int stringsCount;
    
    /**
     * Total number of translated strings from the language pack
     */
    private int translatedCount;
    
    /**
     * Link to language translation interface; empty for custom local language packs
     */
    private String translationsUrl;
    
    public TLLangPackLanguage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public boolean hasBaseLangCode() {
        return this.isFlagSet(FLAG_BASE_LANG_CODE);
    }
    
    public String getBaseLangCode() {
        return baseLangCode;
    }

    public void setBaseLangCode(String baseLangCode) {
        this.baseLangCode = baseLangCode;
        if (this.baseLangCode != null && !this.baseLangCode.trim().isEmpty()) {
            this.flags |= FLAG_BASE_LANG_CODE;
        } else {
            this.flags &= ~FLAG_BASE_LANG_CODE;
        }
    }

    public String getPluralCode() {
        return pluralCode;
    }

    public void setPluralCode(String pluralCode) {
        this.pluralCode = pluralCode;
    }

    public int getStringsCount() {
        return stringsCount;
    }

    public void setStringsCount(int stringsCount) {
        this.stringsCount = stringsCount;
    }

    public int getTranslatedCount() {
        return translatedCount;
    }

    public void setTranslatedCount(int translatedCount) {
        this.translatedCount = translatedCount;
    }

    public String getTranslationsUrl() {
        return translationsUrl;
    }

    public void setTranslationsUrl(String translationsUrl) {
        this.translationsUrl = translationsUrl;
    }
    
    public boolean isOfficial() {
        return this.isFlagSet(FLAG_OFFICIAL);
    }

    public boolean isRTL() {
        return this.isFlagSet(FLAG_RTL);
    }

    public boolean isBeta() {
        return this.isFlagSet(FLAG_BETA);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.name, stream);
        StreamingUtils.writeTLString(this.nativeName, stream);
        StreamingUtils.writeTLString(this.langCode, stream);
        if (this.hasBaseLangCode()) {
            StreamingUtils.writeTLString(this.baseLangCode, stream);
        }
        StreamingUtils.writeTLString(this.pluralCode, stream);
        StreamingUtils.writeInt(this.stringsCount, stream);
        StreamingUtils.writeInt(this.translatedCount, stream);
        StreamingUtils.writeTLString(this.translationsUrl, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.name = StreamingUtils.readTLString(stream);
        this.nativeName = StreamingUtils.readTLString(stream);
        this.langCode = StreamingUtils.readTLString(stream);
        if (this.hasBaseLangCode()) {
            this.baseLangCode = StreamingUtils.readTLString(stream);
        }
        this.pluralCode = StreamingUtils.readTLString(stream);
        this.stringsCount = StreamingUtils.readInt(stream);
        this.translatedCount = StreamingUtils.readInt(stream);
        this.translationsUrl = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "langPackLanguage#eeca5ce3";
    }

}