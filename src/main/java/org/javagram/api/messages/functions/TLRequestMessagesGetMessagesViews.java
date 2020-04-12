package org.javagram.api.messages.functions;

import org.javagram.api.peer.base.input.TLAbsInputPeer;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLIntVector;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Get and increase the view counter of a message sent or forwarded from a @see <a href="https://core.telegram.org/api/channel">channel</a>
 * messages.getMessagesViews#c4c8a55d peer:InputPeer id:Vector&lt;int&gt; increment:Bool = Vector&lt;int&gt;;
 */
public class TLRequestMessagesGetMessagesViews extends TLMethod<TLIntVector> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc4c8a55d;

    /**
     * Peer where the message was found
     */
    private TLAbsInputPeer peer;

    /**
     * ID of message
     */
    private TLIntVector id;

    /**
     * Whether to mark the message as viewed and increment the view counter
     */
    private boolean increment;

    public TLRequestMessagesGetMessagesViews() {
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

    public TLIntVector getId() {
        return this.id;
    }

    public void setId(TLIntVector value) {
        this.id = value;
    }

    public boolean isIncrement() {
        return increment;
    }

    public void setIncrement(boolean increment) {
        this.increment = increment;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLVector(this.id, stream);
        StreamingUtils.writeTLBool(this.increment, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.id = StreamingUtils.readTLIntVector(stream, context);
        this.increment = StreamingUtils.readTLBool(stream);
    }

    @Override
    public TLIntVector deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLIntVector) {
            return (TLIntVector) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLIntVector.class.getName() + ", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "messages.getMessagesViews#c4c8a55d";
    }

}