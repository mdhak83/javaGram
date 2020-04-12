package org.javagram.api.update.base;

import org.javagram.api.sendmessage.base.action.TLAbsSendMessageAction;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.chat.base.TLChannel;
import org.javagram.api.chat.base.TLChat;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.api.user.base.TLUser;
import org.javagram.client.handlers.AbstractUpdatesHandler;

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