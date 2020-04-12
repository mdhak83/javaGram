package org.telegram.api.messages.functions;

import org.telegram.api.peer.base.input.TLAbsInputPeer;
import org.telegram.api.messages.base.TLMessagesEditData;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Find out if a media message's caption can be edited
 * messages.getMessageEditData#fda68d36 peer:InputPeer id:int = messages.MessageEditData;
 */
public class TLRequestMessagesGetMessageEditData extends TLMethod<TLMessagesEditData> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfda68d36;

    /**
     * Peer where the media was sent
     */
    private TLAbsInputPeer peer;

    /**
     * ID of message
     */
    private int id;

    public TLRequestMessagesGetMessageEditData() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.id = StreamingUtils.readInt(stream);
    }

    @Override
    public TLMessagesEditData deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLMessagesEditData) {
            return (TLMessagesEditData) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLMessagesEditData.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "messages.getMessageEditData#fda68d36";
    }

}