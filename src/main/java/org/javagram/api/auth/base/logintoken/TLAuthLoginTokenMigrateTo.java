package org.javagram.api.auth.base.logintoken;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;

/**
 * auth.loginTokenMigrateTo
 * Repeat the query to the specified DC
 * auth.loginTokenMigrateTo#68e9916 dc_id:int token:bytes = auth.LoginToken;
 */
public class TLAuthLoginTokenMigrateTo extends TLAbsAuthLoginToken {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x68e9916;

    /**
     * DC ID
     */
    private int dcId;
    
    /**
     * Token to use for login
     */
    private TLBytes token;

    public TLAuthLoginTokenMigrateTo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getDcId() {
        return dcId;
    }

    public void setDcId(int dcId) {
        this.dcId = dcId;
    }

    public TLBytes getToken() {
        return token;
    }

    public void setToken(TLBytes token) {
        this.token = token;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.dcId, stream);
        StreamingUtils.writeTLBytes(this.token, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.dcId = StreamingUtils.readInt(stream);
        this.token = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "auth.loginTokenMigrateTo#68e9916";
    }

}