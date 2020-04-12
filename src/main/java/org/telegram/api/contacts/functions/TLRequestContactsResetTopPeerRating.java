package org.telegram.api.contacts.functions;

import org.telegram.api.peer.base.input.TLAbsInputPeer;
import org.telegram.api.toppeer.base.category.TLAbsTopPeerCategory;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBool;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Reset @see <a href="https://core.telegram.org/api/top-rating">rating</a> of top peer
 * contacts.resetTopPeerRating#1ae373ac category:TopPeerCategory peer:InputPeer = Bool;
 */
public class TLRequestContactsResetTopPeerRating extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1ae373ac;

    /**
     * Top peer category
     */
    private TLAbsTopPeerCategory category;
    
    /**
     * Peer whose rating should be reset
     */
    private TLAbsInputPeer peer;

    public TLRequestContactsResetTopPeerRating() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsTopPeerCategory getCategory() {
        return category;
    }

    public void setCategory(TLAbsTopPeerCategory category) {
        this.category = category;
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.category, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.category = StreamingUtils.readTLObject(stream, context, TLAbsTopPeerCategory.class);
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)  {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "contacts.resetTopPeerRating#1ae373ac";
    }

}