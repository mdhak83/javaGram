package org.javagram.api.account.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;

/**
 * account.tmpPassword
 * Temporary payment password
 * account.tmpPassword#db64fd34 tmp_password:bytes valid_until:int = account.TmpPassword;
 */
public class TLAccountTmpPassword extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xdb64fd34;

    /**
     * Temporary password
     */
    private TLBytes tmpPassword;
    
    /**
     * Validity period
     */
    private int validUntil;

    public TLAccountTmpPassword() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLBytes getTmpPassword() {
        return tmpPassword;
    }

    public void setTmpPassword(TLBytes tmpPassword) {
        this.tmpPassword = tmpPassword;
    }

    public int getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(int validUntil) {
        this.validUntil = validUntil;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBytes(this.tmpPassword, stream);
        StreamingUtils.writeInt(this.validUntil, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.tmpPassword = StreamingUtils.readTLBytes(stream, context);
        this.validUntil = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "account.tmpPassword#db64fd34";
    }

}