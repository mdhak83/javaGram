package org.telegram.api.secure.base.plain;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;

/**
 * Phone number to use in @see <a href="https://core.telegram.org/passport">telegram passport</a>: @see <a href="https://core.telegram.org/passport/encryption#secureplaindata">it must be verified, first »</a>.
 * securePlainPhone#7d6099dd phone:string = SecurePlainData;
 */
public class TLSecurePlainPhone extends TLAbsSecurePlainData {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7d6099dd;
    
    /**
     * Phone number
     */
    private String phone;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.phone, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.phone = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "securePlainPhone#7d6099dd";
    }

}