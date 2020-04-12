package org.telegram.api.message.base.action;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A user joined the chat via an invite link
 * messageActionChatJoinedByLink#f89cf5e8 inviter_id:int = MessageAction;
 */
public class TLMessageActionChatJoinedByLink extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf89cf5e8;

    /**
     * ID of the user that created the invite link
     */
    private int inviterId;

    public TLMessageActionChatJoinedByLink() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getInviterId() {
        return this.inviterId;
    }

    public void setInviterId(int inviterId) {
        this.inviterId = inviterId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.inviterId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.inviterId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messageaction.messageActionChatJoinedByLink#f89cf5e8";
    }

}