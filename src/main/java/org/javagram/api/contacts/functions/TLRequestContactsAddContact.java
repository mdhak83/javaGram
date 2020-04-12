package org.javagram.api.contacts.functions;

import org.javagram.api.contacts.base.toppeers.TLAbsContactsTopPeers;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.updates.base.TLAbsUpdates;
import org.javagram.api.user.base.input.TLAbsInputUser;

/**
 * Add an existing telegram user as contact
 * contacts.addContact#e8f463d0 flags:# add_phone_privacy_exception:flags.0?true id:InputUser first_name:string last_name:string phone:string = Updates;
 */
public class TLRequestContactsAddContact extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe8f463d0;

    private static final int FLAG_ADD_PHONE_PRIVACY_EXCEPTION   = 0x00000001; // 0 : Allow the other user to see our phone number?

    /**
     * Telegram ID of the other user
     */
    private TLAbsInputUser id;
    
    /**
     * First name
     */
    private String firstName;
    
    /**
     * Last name
     */
    private String lastName;
    
    /**
     * User's phone number
     */
    private String phone;
    
    public TLRequestContactsAddContact() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean hasAddPhonePrivacyException() {
        return this.isFlagSet(FLAG_ADD_PHONE_PRIVACY_EXCEPTION);
    }

    public void enableAddPhonePrivacyException(boolean value) {
        this.setFlag(FLAG_ADD_PHONE_PRIVACY_EXCEPTION, value);
    }

    public TLAbsInputUser getId() {
        return id;
    }

    public void setId(TLAbsInputUser id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.id, stream);
        StreamingUtils.writeTLString(this.firstName, stream);
        StreamingUtils.writeTLString(this.lastName, stream);
        StreamingUtils.writeTLString(this.phone, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
        this.firstName = StreamingUtils.readTLString(stream);
        this.lastName = StreamingUtils.readTLString(stream);
        this.phone = StreamingUtils.readTLString(stream);
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsContactsTopPeers.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "contacts.addContact#e8f463d0";
    }

}