package org.javagram.api.messages.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.emoji.base.TLEmojiKeywordsDifference;

/**
 * Get localized emoji keywords
 * messages.getEmojiKeywords#35a0e062 lang_code:string = EmojiKeywordsDifference;
 */
public class TLRequestMessagesGetEmojiKeywords extends TLMethod<TLEmojiKeywordsDifference> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x35a0e062;

    /**
     * Language code
     */
    private String langCode;

    public TLRequestMessagesGetEmojiKeywords() {
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
    public TLEmojiKeywordsDifference deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLEmojiKeywordsDifference) {
            return (TLEmojiKeywordsDifference) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getEmojiKeywords#35a0e062";
    }

}