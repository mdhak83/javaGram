package org.telegram.api.help.base;

import org.telegram.api.user.base.TLAbsUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Info on support user.
 * help.support#17c6b5f6 phone_number:string user:User = help.Support;
 */
public class TLHelpSupport extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x17c6b5f6;

    /**
     * Phone number
     */
    private String phoneNumber;
    
    /**
     * User
     */
    private TLAbsUser user;

    public TLHelpSupport() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    public TLAbsUser getUser() {
        return this.user;
    }

    public void setUser(TLAbsUser value) {
        this.user = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.phoneNumber, stream);
        StreamingUtils.writeTLObject(this.user, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.phoneNumber = StreamingUtils.readTLString(stream);
        this.user = StreamingUtils.readTLObject(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "help.support#17c6b5f6";
    }

}