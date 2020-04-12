package org.telegram.api.peer.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.client.handlers.AbstractUpdatesHandler;

/**
 * Chat partner
 * peerUser#9db1bc6d user_id:int = Peer;
 */
public class TLPeerUser extends TLAbsPeer {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9db1bc6d;

    /**
     * User identifier
     */
    private int userId;
    
    public TLPeerUser() {
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
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.userId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.userId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "peerUser#9db1bc6d";
    }

    @Override
    public String toLog() {
        return this.userId + "";
    }

}