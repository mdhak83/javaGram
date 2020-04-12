package org.telegram.api.messages.functions;

import org.telegram.api.messages.base.chats.TLAbsMessagesChats;
import org.telegram.api._primitives.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.messages.base.TLMessagesSearchCounter;
import org.telegram.api.messages.base.input.filter.TLAbsMessagesFilter;
import org.telegram.api.peer.base.input.TLAbsInputPeer;
import org.telegram.utils.StreamingUtils;

/**
 * Get the number of results that would be found by a @see <a href="https://core.telegram.org/method/messages.search">messages.search</a> call with the same parameters
 * messages.getSearchCounters#732eef00 peer:InputPeer filters:Vector&lt;MessagesFilter&gt; = Vector&lt;messages.SearchCounter&gt;;
 */
public class TLRequestMessagesGetSearchCounter extends TLMethod<TLVector<TLMessagesSearchCounter>> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x732eef00;
    
    /**
     * Peer where to search
     */
    private TLAbsInputPeer peer;
    
    /**
     * Search filters
     */
    private TLVector<TLAbsMessagesFilter> filters;

    public TLRequestMessagesGetSearchCounter() {
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

    public TLVector<TLAbsMessagesFilter> getFilters() {
        return filters;
    }

    public void setFilters(TLVector<TLAbsMessagesFilter> filters) {
        this.filters = filters;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLVector(this.filters, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.filters = StreamingUtils.readTLVector(stream, context, TLAbsMessagesFilter.class);
    }

    @Override
    public TLVector<TLMessagesSearchCounter> deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLVector) {
            return (TLVector<TLMessagesSearchCounter>) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsMessagesChats.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getSearchCounters#732eef00";
    }

}