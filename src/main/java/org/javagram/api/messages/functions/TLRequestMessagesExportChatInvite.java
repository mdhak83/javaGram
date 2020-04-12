package org.javagram.api.messages.functions;

import org.javagram.api.chat.base.invite.TLChatInviteExported;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.chat.base.invite.TLAbsExportedChatInvite;
import org.javagram.api.peer.base.input.TLAbsInputPeer;

/**
 * Export an invite link for a chat
 * messages.exportChatInvite#df7534c peer:InputPeer = ExportedChatInvite;
 */
public class TLRequestMessagesExportChatInvite extends TLMethod<TLAbsExportedChatInvite> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xdf7534c;

    /**
     * Chat
     */
    private TLAbsInputPeer peer;

    public TLRequestMessagesExportChatInvite() {
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

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
    }

    @Override
    public TLAbsExportedChatInvite deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsExportedChatInvite) {
            return (TLAbsExportedChatInvite) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLChatInviteExported.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.exportChatInvite#df7534c";
    }

}