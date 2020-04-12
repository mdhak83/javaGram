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
 * Get changed emoji keywords
 * messages.getEmojiKeywordsDifference#1508b6af lang_code:string from_version:int = EmojiKeywordsDifference;
 */
public class TLRequestMessagesGetEmojiKeywordsDifference extends TLMethod<TLEmojiKeywordsDifference> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1508b6af;

    /**
     * Language code
     */
    private String langCode;
    
    /**
     * Previous emoji keyword localization version
     */
    private int fromVersion;

    public TLRequestMessagesGetEmojiKeywordsDifference() {
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

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.langCode, stream);
        StreamingUtils.writeInt(this.fromVersion, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.langCode = StreamingUtils.readTLString(stream);
        this.fromVersion = StreamingUtils.readInt(stream);
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
        return "messages.getEmojiKeywordsDifference#1508b6af";
    }

}