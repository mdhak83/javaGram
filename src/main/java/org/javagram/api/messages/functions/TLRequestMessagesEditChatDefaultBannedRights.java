package org.javagram.api.messages.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.chat.base.TLChatBannedRights;
import org.javagram.api.peer.base.input.TLAbsInputPeer;
import org.javagram.api.updates.base.TLAbsUpdates;

/**
 * Edit the default banned rights of a @see <a href="https://core.telegram.org/api/channel">group/supergroup/channel</a>.
 * messages.editChatDefaultBannedRights#a5866b41 peer:InputPeer banned_rights:ChatBannedRights = Updates;
 */
public class TLRequestMessagesEditChatDefaultBannedRights extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa5866b41;

    /**
     * The <a href="https://core.telegram.org/api/channel">group/supergroup/channel</a>
     */
    private TLAbsInputPeer peer;
    
    /**
     * The new global rights
     */
    private TLChatBannedRights bannedRights;

    public TLRequestMessagesEditChatDefaultBannedRights() {
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

    public TLChatBannedRights getBannedRights() {
        return bannedRights;
    }

    public void setBannedRights(TLChatBannedRights bannedRights) {
        this.bannedRights = bannedRights;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLObject(this.bannedRights, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.bannedRights = StreamingUtils.readTLObject(stream, context, TLChatBannedRights.class);
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.editChatDefaultBannedRights#a5866b41";
    }

}