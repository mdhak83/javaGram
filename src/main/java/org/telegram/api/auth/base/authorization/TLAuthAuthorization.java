package org.telegram.api.auth.base.authorization;

import org.telegram.api.user.base.TLAbsUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Contains user authorization info.
 * auth.authorization#cd050916 flags:# tmp_sessions:flags.0?int user:User = auth.Authorization;
 */
public class TLAuthAuthorization extends TLAbsAuthAuthorization {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xcd050916;

    private static final int FLAG_TMP_SESSIONS     = 0x00000001; // 0 : Temporary passport sessions

    /**
     * Temporary @see <a href="https://core.telegram.org/passport">passport</a> sessions
     */
    private int tmpSessions;

    public TLAuthAuthorization() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean hasTmpSessions() {
        return this.isFlagSet(FLAG_TMP_SESSIONS);
    }

    public int getTmpSessions() {
        return tmpSessions;
    }

    public void setTmpSessions(int tmpSessions) {
        this.tmpSessions = tmpSessions;
        if (tmpSessions != 0) {
            this.flags |= FLAG_TMP_SESSIONS;
        } else {
            this.flags &= ~FLAG_TMP_SESSIONS;
        }
    }

    public void setUser(TLAbsUser user) {
        this.user = user;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.tmpSessions, stream);
        StreamingUtils.writeTLObject(this.user, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.hasTmpSessions()) {
            this.tmpSessions = StreamingUtils.readInt(stream);
        }
        this.user = StreamingUtils.readTLObject(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "auth.authorization#cd050916";
    }

}