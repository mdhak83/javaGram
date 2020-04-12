package org.javagram.api.updates.base.difference;

import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.message.base.encrypted.TLAbsEncryptedMessage;
import org.javagram.api.message.base.TLAbsMessage;
import org.javagram.api.update.base.TLAbsUpdate;
import org.javagram.api.updates.base.TLUpdatesState;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL difference slice.
 */
public class TLDifferenceSlice extends TLAbsDifference {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa8fb1981;

    private TLUpdatesState intermediateState;

    public TLDifferenceSlice() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets intermediate state.
     *
     * @return the intermediate state
     */
    public TLUpdatesState getIntermediateState() {
        return this.intermediateState;
    }

    /**
     * Sets intermediate state.
     *
     * @param intermediateState the intermediate state
     */
    public void setIntermediateState(TLUpdatesState intermediateState) {
        this.intermediateState = intermediateState;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.newMessages, stream);
        StreamingUtils.writeTLVector(this.newEncryptedMessages, stream);
        StreamingUtils.writeTLVector(this.otherUpdates, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
        StreamingUtils.writeTLObject(this.intermediateState, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.newMessages = StreamingUtils.readTLVector(stream, context, TLAbsMessage.class);
        this.newEncryptedMessages = StreamingUtils.readTLVector(stream, context, TLAbsEncryptedMessage.class);
        this.otherUpdates = StreamingUtils.readTLVector(stream, context, TLAbsUpdate.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
        this.intermediateState = StreamingUtils.readTLObject(stream, context, TLUpdatesState.class);
    }

    @Override
    public String toString() {
        return "updates.differenceSlice#a8fb1981";
    }

}