package org.telegram.api.messages.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.emoji.base.TLEmojiURL;

/**
 * Returns an HTTP URL which can be used to automatically log in into translation platform and suggest new emoji replacements.
 * The URL will be valid for 30 seconds after generation
 * messages.getEmojiURL#d5b10c26 lang_code:string = EmojiURL;
 */
public class TLRequestMessagesGetEmojiKeywordsURL extends TLMethod<TLEmojiURL> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd5b10c26;

    /**
     * Language code for which the emoji replacements will be suggested
     */
    private String langCode;
    
    public TLRequestMessagesGetEmojiKeywordsURL() {
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
    public TLEmojiURL deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLEmojiURL) {
            return (TLEmojiURL) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getEmojiURL#d5b10c26";
    }

}