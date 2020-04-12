package org.javagram.api.user.base.input;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Defines a user for further interaction.
 * inputUser#d8292816 user_id:int access_hash:long = InputUser;
 */
public class TLInputUser extends TLAbsInputUser {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd8292816;

    /**
     * User identifier
     */
    private int userId;
    
    /**
     * <strong>access_hash</strong> value from the @see <a href="https://core.telegram.org/constructor/user">user</a> constructor
     */
    private long accessHash;

    public TLInputUser() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getAccessHash() {
        return this.accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.userId = StreamingUtils.readInt(stream);
        this.accessHash = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "inputUser#d8292816";
    }

}