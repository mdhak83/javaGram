package org.telegram.api.messages.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.TLStatsURL;
import org.telegram.api.peer.base.input.TLAbsInputPeer;

/**
 * Returns URL with the chat statistics. Currently this method can be used only for channels
 * messages.getStatsURL#812c2ae6 flags:# dark:flags.0?true peer:InputPeer params:string = StatsURL;
 */
public class TLRequestMessagesGetStatsURL extends TLMethod<TLStatsURL> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x812c2ae6;

    private static final int FLAG_DARK  = 0x00000001; //  0 : Pass true if a URL with the dark theme must be returned

    /**
     * Chat identifier
     */
    private TLAbsInputPeer peer;

    /**
     * Parameters from <code>tg://statsrefresh?params=******</code> link
     */
    private String params;

    public TLRequestMessagesGetStatsURL() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isDark() {
        return this.isFlagSet(FLAG_DARK);
    }

    public void setDark(boolean value) {
        this.setFlag(FLAG_DARK, value);
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLString(this.params, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.params = StreamingUtils.readTLString(stream);
    }

    @Override
    public TLStatsURL deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLStatsURL) {
            return (TLStatsURL) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getStatsURL#812c2ae6";
    }

}