package org.telegram.api.messages.base;

import org.telegram.api.chat.base.TLAbsChat;
import org.telegram.api.dialog.base.TLDialog;
import org.telegram.api.message.base.TLAbsMessage;
import org.telegram.api.updates.base.TLUpdatesState;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL messages.
 */
public class TLMessagesPeerDialogs extends TLObject {
    public static final int CLASS_ID = 0x3371c354;

    private TLVector<TLDialog> dialogs;
    private TLVector<TLAbsMessage> messages;
    private TLVector<TLAbsChat> chats;
    private TLVector<TLAbsUser> users;
    private TLUpdatesState state;

    public TLMessagesPeerDialogs() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLDialog> getDialogs() {
        return dialogs;
    }

    public TLVector<TLAbsMessage> getMessages() {
        return messages;
    }

    public TLVector<TLAbsChat> getChats() {
        return chats;
    }

    public TLVector<TLAbsUser> getUsers() {
        return users;
    }

    public TLUpdatesState getState() {
        return state;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(dialogs, stream);
        StreamingUtils.writeTLVector(messages, stream);
        StreamingUtils.writeTLVector(chats, stream);
        StreamingUtils.writeTLVector(users, stream);
        StreamingUtils.writeTLObject(state, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        dialogs = StreamingUtils.readTLVector(stream, context, TLDialog.class);
        messages = StreamingUtils.readTLVector(stream, context, TLAbsMessage.class);
        chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
        state = StreamingUtils.readTLObject(stream, context, TLUpdatesState.class);
    }

    @Override
    public String toString() {
        return "messages.peerDialogs#3371c354";
    }

}