package org.telegram.api.messages.functions;

import org.telegram.api.peer.base.input.TLAbsInputPeer;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.messages.base.TLAbsMessages;

/**
 * Get live location history of a certain user
 * messages.getRecentLocations#bbc45b09 peer:InputPeer limit:int hash:int = messages.Messages;
 */
public class TLRequestMessagesGetRecentLocations extends TLMethod<TLAbsMessages> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbbc45b09;

    /**
     * Target user or group
     */
    private TLAbsInputPeer peer;

    /**
     * Maximum number of results to return, @see <a href="https://core.telegram.org/api/offsets">see pagination</a>
     */
    private int limit;
    
    /**
     * @see <a href="https://core.telegram.org/api/offsets">Hash for pagination, for more info click here</a>
     */
    private int hash;

    public TLRequestMessagesGetRecentLocations() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputPeer getPeer() {
        return this.peer;
    }

    public void setPeer(TLAbsInputPeer value) {
        this.peer = value;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }
    
    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.limit, stream);
        StreamingUtils.writeInt(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.limit = StreamingUtils.readInt(stream);
        this.hash = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsMessages deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsMessages) {
            return (TLAbsMessages) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getRecentLocations#bbc45b09";
    }

}