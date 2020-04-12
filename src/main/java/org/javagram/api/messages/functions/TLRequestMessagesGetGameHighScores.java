package org.javagram.api.messages.functions;

import org.javagram.api.peer.base.input.TLAbsInputPeer;
import org.javagram.api.user.base.input.TLAbsInputUser;
import org.javagram.api.messages.base.TLMessagesHighScores;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages accept encryption.
 */
public class TLRequestMessagesGetGameHighScores extends TLMethod<TLMessagesHighScores> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe822649d;

    private TLAbsInputPeer peer;
    private int id;
    private TLAbsInputUser userId;

    public TLRequestMessagesGetGameHighScores() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLMessagesHighScores deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if (res instanceof TLMessagesHighScores)
            return (TLMessagesHighScores) res;
        throw new IOException("Incorrect response type. Expected " + TLMessagesHighScores.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
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

    public TLAbsInputUser getUserId() {
        return userId;
    }

    public void setUserId(TLAbsInputUser userId) {
        this.userId = userId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(id, stream);
        StreamingUtils.writeTLObject(this.userId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        id = StreamingUtils.readInt(stream);
        userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
    }

    @Override
    public String toString() {
        return "messages.getGameHighScores#e822649d";
    }

}