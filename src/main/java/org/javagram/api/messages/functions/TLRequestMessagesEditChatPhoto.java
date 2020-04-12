package org.javagram.api.messages.functions;

import org.javagram.api.chat.base.input.photo.TLAbsInputChatPhoto;
import org.javagram.api.updates.base.TLAbsUpdates;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Changes chat photo and sends a service message on it
 * messages.editChatPhoto#ca4c79d8 chat_id:int photo:InputChatPhoto = Updates;
 */
public class TLRequestMessagesEditChatPhoto extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xca4c79d8;

    /**
     * Chat ID
     */
    private int chatId;
    
    /**
     * Photo to be set
     */
    private TLAbsInputChatPhoto photo;

    public TLRequestMessagesEditChatPhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public TLAbsInputChatPhoto getPhoto() {
        return this.photo;
    }

    public void setPhoto(TLAbsInputChatPhoto value) {
        this.photo = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.chatId, stream);
        StreamingUtils.writeTLObject(this.photo, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.chatId = StreamingUtils.readInt(stream);
        this.photo = StreamingUtils.readTLObject(stream, context, TLAbsInputChatPhoto.class);
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.editChatPhoto#ca4c79d8";
    }

}