package org.telegram.api.messages.functions;

import org.telegram.api.input.media.TLAbsInputMedia;
import org.telegram.api.peer.base.input.TLAbsInputPeer;
import org.telegram.api.updates.base.TLAbsUpdates;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Upload a file and associate it to a chat (without actually sending it to the chat)
 * messages.uploadMedia#519bc2b1 peer:InputPeer media:InputMedia = MessageMedia;
 */
public class TLRequestMessagesUploadMedia extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x519bc2b1;

     /**
      * The chat, can be an @see <a href="https://core.telegram.org/constructor/inputPeerEmpty">inputPeerEmpty</a> for bots
      */
    private TLAbsInputPeer peer;

    /**
     * File uploaded in chunks as described in @see <a href="https://core.telegram.org/api/files">files »</a>
     */
    private TLAbsInputMedia media;
    
    public TLRequestMessagesUploadMedia() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public TLAbsInputMedia getMedia() {
        return media;
    }

    public void setMedia(TLAbsInputMedia media) {
        this.media = media;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLObject(this.media, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.media = StreamingUtils.readTLObject(stream, context, TLAbsInputMedia.class);
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.updates.TLAbsUpdates, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.uploadMedia#519bc2b1";
    }

}