package org.javagram.api.messages.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.emoji.base.TLEmojiLanguage;
import org.javagram.api._primitives.TLStringVector;

/**
 * Get info about an emoji keyword localization
 * messages.getEmojiKeywordsLanguages#4e9963b2 lang_codes:Vector&lt;string&gt; = Vector&lt;EmojiLanguage&gt;;
 */
public class TLRequestMessagesGetEmojiKeywordsLanguage extends TLMethod<TLEmojiLanguage> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4e9963b2;

    /**
     * Language codes
     */
    private TLStringVector langCodes;
    
    public TLRequestMessagesGetEmojiKeywordsLanguage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLStringVector getLangCodes() {
        return langCodes;
    }

    public void setLangCodes(TLStringVector langCodes) {
        this.langCodes = langCodes;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.langCodes, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.langCodes = StreamingUtils.readTLStringVector(stream, context);
    }

    @Override
    public TLEmojiLanguage deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLEmojiLanguage) {
            return (TLEmojiLanguage) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getEmojiKeywordsLanguages#4e9963b2";
    }

}