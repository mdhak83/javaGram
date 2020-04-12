package org.javagram.api.emoji.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLVector;

/**
 * Deleted emoji keyword
 * emojiKeywordDeleted#236df622 keyword:string emoticons:Vector&lt;string&gt; = EmojiKeyword;
 */
public class TLEmojiKeywordDeleted extends TLAbsEmojiKeyword {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x236df622;

    /**
     * Keyword
     */
    private String keyword;
    
    /**
     * Emojis that were associated to keyword
     */
    private TLVector<String> emoticons;

    public TLEmojiKeywordDeleted() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public TLVector<String> getEmoticons() {
        return emoticons;
    }

    public void setEmoticons(TLVector<String> emoticons) {
        this.emoticons = emoticons;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.keyword, stream);
        StreamingUtils.writeTLVector(this.emoticons, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.keyword = StreamingUtils.readTLString(stream);
        this.emoticons = StreamingUtils.readTLVector(stream, context, String.class);
    }

    @Override
    public String toString() {
        return "emojiKeywordDeleted#236df622";
    }

}