package org.javagram.api.password.base.input.checkpassword;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;

/**
 * inputCheckPasswordSRP
 * Constructor for checking the validity of a 2FA SRP password (see @see <a href="https://core.telegram.org/api/srp">SRP</a>)
 * inputCheckPasswordSRP#d27ff082 srp_id:long A:bytes M1:bytes = InputCheckPasswordSRP;
 */
public class TLInputCheckPasswordSRP extends TLAbsInputCheckPasswordSRP {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd27ff082;
    
    /**
     * @see <a href="https://core.telegram.org/api/srp">SRP ID</a>
     */
    private long srpId;
    
    /**
     * <code>A</code> parameter (see @see <a href="https://core.telegram.org/api/srp">SRP</a>)
     */
    private TLBytes a;
    
    /**
     * <code>M1</code> parameter (see @see <a href="https://core.telegram.org/api/srp">SRP</a>)
     */
    private TLBytes m1;
    
    public TLInputCheckPasswordSRP() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getSrpId() {
        return srpId;
    }

    public void setSrpId(long srpId) {
        this.srpId = srpId;
    }

    public TLBytes getA() {
        return a;
    }

    public void setA(TLBytes a) {
        this.a = a;
    }

    public TLBytes getM1() {
        return m1;
    }

    public void setM1(TLBytes m1) {
        this.m1 = m1;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.srpId, stream);
        StreamingUtils.writeTLBytes(this.a, stream);
        StreamingUtils.writeTLBytes(this.m1, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.srpId = StreamingUtils.readLong(stream);
        this.a = StreamingUtils.readTLBytes(stream, context);
        this.m1 = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "inputCheckPasswordSRP#d27ff082";
    }

}