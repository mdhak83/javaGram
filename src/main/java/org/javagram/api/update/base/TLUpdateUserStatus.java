package org.javagram.api.update.base;

import org.javagram.api.user.base.status.TLAbsUserStatus;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.client.handlers.AbstractUpdatesHandler;

/**
 * The type TL update user status.
 */
public class TLUpdateUserStatus extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1bfbd823;

    private int userId;
    private TLAbsUserStatus status;

    public TLUpdateUserStatus() {
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

    /**
     * Gets status.
     *
     * @return the status
     */
    public TLAbsUserStatus getStatus() {
        return this.status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(TLAbsUserStatus status) {
        this.status = status;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeTLObject(this.status, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.userId = StreamingUtils.readInt(stream);
        this.status = StreamingUtils.readTLObject(stream, context, TLAbsUserStatus.class);
    }

    @Override
    public String toString() {
        return "updateUserStatus#1bfbd823";
    }

    public String toLog() {
        String ret = null;
        if (this.status != null) {
            String sStatus = (this.status != null ? this.status.toLog() : null);
            ret = "User: " + this.userId + " - Status: " + (sStatus != null ? sStatus : "---");
        }
        return ret;
    }

}