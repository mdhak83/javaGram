package org.javagram.api.messages.functions;

import org.javagram.api.chat.base.invite.TLAbsChatInvite;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Check the validity of a chat invite link and get basic info about it
 * messages.checkChatInvite#3eadb1bb hash:string = ChatInvite;
 */
public class TLRequestMessagesCheckChatInvite extends TLMethod<TLAbsChatInvite> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3eadb1bb;

    /**
     * Invite hash in <code>t.me/joinchat/hash</code>
     */
    private String hash;

    public TLRequestMessagesCheckChatInvite() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.hash = StreamingUtils.readTLString(stream);
    }

    @Override
    public TLAbsChatInvite deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsChatInvite) {
            return (TLAbsChatInvite) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.chat.TLAbsChatInvite, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "request.checkChatInvite#3eadb1bb";
    }

}