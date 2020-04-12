package org.javagram.api.channels.functions;

import org.javagram.api.messages.base.chats.TLMessagesChats;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.messages.base.stickers.faved.TLAbsFavedStickers;

/**
 * Get faved stickers
 * messages.getFavedStickers#21ce0b0e hash:int = messages.FavedStickers;
 */
public class TLRequestChannelsGetFavedStickers extends TLMethod<TLAbsFavedStickers> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x21ce0b0e;

    /**
     * @see <a href="https://core.telegram.org/api/offsets#hash-generation">Hash for pagination, for more info click here</a>
     */
    private int hash;
    
    public TLRequestChannelsGetFavedStickers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.hash = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsFavedStickers deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsFavedStickers) {
            return (TLAbsFavedStickers) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLMessagesChats.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "messages.getFavedStickers#21ce0b0e";
    }

}