package org.telegram.api.updates.base.difference;

import org.telegram.api.chat.base.TLAbsChat;
import org.telegram.api.message.base.encrypted.TLAbsEncryptedMessage;
import org.telegram.api.message.base.TLAbsMessage;
import org.telegram.api.update.base.TLAbsUpdate;
import org.telegram.api.updates.base.TLUpdatesState;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL difference.
 */
public class TLDifference extends TLAbsDifference {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf49ca0;

    private TLUpdatesState state;

    public TLDifference() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public TLUpdatesState getState() {
        return this.state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(TLUpdatesState state) {
        this.state = state;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.newMessages, stream);
        StreamingUtils.writeTLVector(this.newEncryptedMessages, stream);
        StreamingUtils.writeTLVector(this.otherUpdates, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
        StreamingUtils.writeTLObject(this.state, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.newMessages = StreamingUtils.readTLVector(stream, context, TLAbsMessage.class);
        this.newEncryptedMessages = StreamingUtils.readTLVector(stream, context, TLAbsEncryptedMessage.class);
        this.otherUpdates = StreamingUtils.readTLVector(stream, context, TLAbsUpdate.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
        this.state = StreamingUtils.readTLObject(stream, context, TLUpdatesState.class);
    }

    @Override
    public String toString() {
        return "updates.difference#f49ca0";
    }

}