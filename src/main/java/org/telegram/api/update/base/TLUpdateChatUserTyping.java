package org.telegram.api.update.base;

import org.telegram.api.sendmessage.base.action.TLAbsSendMessageAction;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.chat.base.TLAbsChat;
import org.telegram.api.chat.base.TLChannel;
import org.telegram.api.chat.base.TLChat;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.api.user.base.TLUser;
import org.telegram.client.handlers.AbstractUpdatesHandler;

/**
 * The type TL update chat user typing.
 */
public class TLUpdateChatUserTyping extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9a65ea1f;

    private int chatId;
    private TLAbsSendMessageAction action;
    private int userId;

    public TLUpdateChatUserTyping() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getChatId() {
        return chatId;
    }

    public TLAbsSendMessageAction getAction() {
        return action;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.chatId, stream);
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeTLObject(this.action, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.chatId = StreamingUtils.readInt(stream);
        this.userId = StreamingUtils.readInt(stream);
        this.action = StreamingUtils.readTLObject(stream, context, TLAbsSendMessageAction.class);
    }

    @Override
    public String toString() {
        return "updateChatUserTyping#9a65ea1f";
    }

    public String toLog() {
        String sAction = (this.action != null ? this.action.toLog() : "---");
        return "Chat: " + this.chatId + " - User: " + this.userId + " - Action: " + sAction;
    }
    
}