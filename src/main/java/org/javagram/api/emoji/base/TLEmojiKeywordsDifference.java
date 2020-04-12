package org.javagram.api.emoji.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLVector;

/**
 * Changes to emoji keywords
 * emojiKeywordsDifference#5cc761bd lang_code:string from_version:int version:int keywords:Vector&lt;EmojiKeyword&gt; = EmojiKeywordsDifference;
 */
public class TLEmojiKeywordsDifference extends TLAbsEmojiKeyword {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5cc761bd;

    /**
     * Language code for keywords
     */
    private String langCode;
    
    /**
     * Previous emoji keyword list version
     */
    private int fromVersion;
    
    /**
     * Current version of emoji keyword list
     */
    private int version;
    
    /**
     * Emojis associated to keywords
     */
    private TLVector<TLAbsEmojiKeyword> keywords;

    public TLEmojiKeywordsDifference() {
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

    public TLVector<TLAbsEmojiKeyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(TLVector<TLAbsEmojiKeyword> keywords) {
        this.keywords = keywords;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.langCode, stream);
        StreamingUtils.writeInt(this.fromVersion, stream);
        StreamingUtils.writeInt(this.version, stream);
        StreamingUtils.writeTLVector(this.keywords, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.langCode = StreamingUtils.readTLString(stream);
        this.fromVersion = StreamingUtils.readInt(stream);
        this.version = StreamingUtils.readInt(stream);
        this.keywords = StreamingUtils.readTLVector(stream, context, TLAbsEmojiKeyword.class);
    }

    @Override
    public String toString() {
        return "emojiKeywordsDifference#5cc761bd";
    }

}