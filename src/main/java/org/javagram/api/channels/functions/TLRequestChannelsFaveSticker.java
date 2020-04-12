package org.javagram.api.channels.functions;

import org.javagram.api.messages.base.chats.TLMessagesChats;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.document.base.input.TLAbsInputDocument;
import org.javagram.api._primitives.TLBool;

/**
 * Mark a sticker as favorite
 * messages.faveSticker#b9ffc55b id:InputDocument unfave:Bool = Bool;
 */
public class TLRequestChannelsFaveSticker extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb9ffc55b;

    /**
     * Sticker to mark as favorite
     */
    private TLAbsInputDocument id;
    
    /**
     * Unfavorite
     */
    private boolean unfave;
    
    public TLRequestChannelsFaveSticker() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputDocument getId() {
        return id;
    }

    public void setId(TLAbsInputDocument id) {
        this.id = id;
    }

    public boolean isUnfave() {
        return unfave;
    }

    public void setUnfave(boolean unfave) {
        this.unfave = unfave;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.id, stream);
        StreamingUtils.writeTLBool(this.unfave, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readTLObject(stream, context, TLAbsInputDocument.class);
        this.unfave = StreamingUtils.readTLBool(stream);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLMessagesChats.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "messages.faveSticker#b9ffc55b";
    }

}