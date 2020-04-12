package org.javagram.api.update.base;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.StreamingUtils;

/**
 * A message was pinned in a private chat with a user
 * updateUserPinnedMessage#4c43da18 user_id:int id:int = Update;
 */
public class TLUpdateUserPinnedMessage extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4c43da18;

    /**
     * User that pinned the message
     */
    private int userId;

    /**
     * Message ID that was pinned
     */
    private int id;

    public TLUpdateUserPinnedMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeInt(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.userId = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updateUserPinnedMessage#4c43da18";
    }

}