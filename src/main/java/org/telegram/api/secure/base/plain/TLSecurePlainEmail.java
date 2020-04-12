package org.telegram.api.secure.base.plain;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;

/**
 * Email address to use in @see <a href="https://core.telegram.org/passport">telegram passport</a>: @see <a href="https://core.telegram.org/passport/encryption#secureplaindata">it must be verified, first »</a>.
 * securePlainEmail#21ec5a5f email:string = SecurePlainData;
 */
public class TLSecurePlainEmail extends TLAbsSecurePlainData {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x21ec5a5f;
    
    /**
     * Email address
     */
    private String email;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.email, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.email = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "securePlainEmail#21ec5a5f";
    }

}