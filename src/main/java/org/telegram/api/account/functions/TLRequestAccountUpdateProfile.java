package org.telegram.api.account.functions;

import org.telegram.api.user.base.TLAbsUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Updates user profile.
 * account.updateProfile#78515775 flags:# first_name:flags.0?string last_name:flags.1?string about:flags.2?string = User;
 */
public class TLRequestAccountUpdateProfile extends TLMethod<TLAbsUser> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x78515775;

    private static final int FLAG_FIRSTNAME  = 0x00000001; // 0
    private static final int FLAG_LASTNAME   = 0x00000002; // 1
    private static final int FLAG_ABOUT      = 0x00000004; // 2

    /**
     * New user first name
     */
    private String firstName;
    
    /**
     * New user last name
     */
    private String lastName;
    
    /**
     * New user bio
     */
    private String about;

    public TLRequestAccountUpdateProfile() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public boolean hasFirstName() {
        return this.isFlagSet(FLAG_FIRSTNAME);
    }
    
    public void setFirstName(String value) {
        this.firstName = value;
        this.setFlag(FLAG_FIRSTNAME, (value != null) && !value.isEmpty());
    }

    public boolean hasLastName() {
        return this.isFlagSet(FLAG_LASTNAME);
    }
    
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String value) {
        this.lastName = value;
        this.setFlag(FLAG_LASTNAME, (value != null) && !value.isEmpty());
    }

    public boolean hasAbout() {
        return this.isFlagSet(FLAG_ABOUT);
    }
    
    public String getAbout() {
        return about;
    }

    public void setAbout(String value) {
        this.about = value;
        this.setFlag(FLAG_ABOUT, value != null);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if (this.isFlagSet(FLAG_FIRSTNAME)) {
            StreamingUtils.writeTLString(this.firstName, stream);
        }
        if (this.isFlagSet(FLAG_LASTNAME)) {
            StreamingUtils.writeTLString(this.lastName, stream);
        }
        if (this.isFlagSet(FLAG_ABOUT)) {
            StreamingUtils.writeTLString(this.about, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.isFlagSet(FLAG_FIRSTNAME)) {
            this.firstName = StreamingUtils.readTLString(stream);
        }
        if (this.isFlagSet(FLAG_LASTNAME)) {
            this.lastName = StreamingUtils.readTLString(stream);
        }
        if (this.isFlagSet(FLAG_ABOUT)) {
            this.about = StreamingUtils.readTLString(stream);
        }
    }

    @Override
    public TLAbsUser deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUser) {
            return (TLAbsUser) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsUser.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.updateProfile#78515775";
    }

}