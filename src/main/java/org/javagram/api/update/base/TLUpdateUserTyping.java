package org.javagram.api.update.base;

import org.javagram.api.sendmessage.base.action.TLAbsSendMessageAction;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.client.handlers.AbstractUpdatesHandler;

/**
 * The type TL update user typing.
 */
public class TLUpdateUserTyping extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5c486927;

    private int userId;
    private TLAbsSendMessageAction action;

    public TLUpdateUserTyping() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public TLAbsSendMessageAction getAction() {
        return this.action;
    }

    public void setAction(TLAbsSendMessageAction action) {
        this.action = action;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeTLObject(this.action, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.userId = StreamingUtils.readInt(stream);
        this.action = StreamingUtils.readTLObject(stream, context, TLAbsSendMessageAction.class);
    }

    @Override
    public String toString() {
        return "updateUserTyping#5c486927";
    }

    public String toLog() {
        String ret = null;
        if (this.action != null) {
            String sAction = (this.action != null ? this.action.toLog() : null);
            ret = "User: " + this.userId + " - Action: " + (sAction != null ? sAction : "---");
        }
        return ret;
    }

}