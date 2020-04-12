package org.javagram.api.emoji.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLObject;

/**
 * An HTTP URL which can be used to automatically log in into translation platform and suggest new emoji replacements. The URL will be valid for 30 seconds after generation
 * emojiURL#a575739d url:string = EmojiURL;
 */
public class TLEmojiURL extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa575739d;

    /**
     * An HTTP URL which can be used to automatically log in into translation platform and suggest new emoji replacements. The URL will be valid for 30 seconds after generation
     */
    private String url;
    

    public TLEmojiURL() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.url, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.url = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "emojiURL#a575739d";
    }

}