package org.telegram.api.messages.functions;

import org.telegram.api.messages.base.stickers.TLAbsAllStickers;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.messages.base.stickers.TLAbsStickers;

/**
 * Get stickers by emoji
 * messages.getStickers#43d4f2c emoticon:string hash:int = messages.Stickers;
 */
public class TLRequestMessagesGetStickers extends TLMethod<TLAbsStickers> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x43d4f2c;

    /**
     * The emoji
     */
    private String emoticon;
    
    /**
     * @see <a href="https://core.telegram.org/api/offsets#hash-generation">Hash for pagination, for more info click here</a>
     */
    protected int hash;

    public TLRequestMessagesGetStickers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getEmoticon() {
        return emoticon;
    }

    public void setEmoticon(String emoticon) {
        this.emoticon = emoticon;
    }

    public int getHash() {
        return this.hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.emoticon, stream);
        StreamingUtils.writeInt(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.emoticon = StreamingUtils.readTLString(stream);
        this.hash = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsStickers deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsStickers) {
            return (TLAbsStickers) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsAllStickers.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getStickers#43d4f2c";
    }

}