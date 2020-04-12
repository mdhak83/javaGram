package org.telegram.api.phone.base.contact.input;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input phone contact.
 */
public class TLInputPhoneContact extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf392b7f4;
    /**
     * The Client id.
     */
    protected long clientId;
    /**
     * The Phone.
     */
    protected String phone;
    /**
     * The First name.
     */
    protected String firstName;
    /**
     * The Last name.
     */
    protected String lastName;

    public TLInputPhoneContact() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public long getClientId() {
        return this.clientId;
    }

    /**
     * Sets client id.
     *
     * @param value the value
     */
    public void setClientId(long value) {
        this.clientId = value;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Sets phone.
     *
     * @param value the value
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets first name.
     *
     * @param value the value
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets last name.
     *
     * @param value the value
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.clientId, stream);
        StreamingUtils.writeTLString(this.phone, stream);
        StreamingUtils.writeTLString(this.firstName, stream);
        StreamingUtils.writeTLString(this.lastName, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.clientId = StreamingUtils.readLong(stream);
        this.phone = StreamingUtils.readTLString(stream);
        this.firstName = StreamingUtils.readTLString(stream);
        this.lastName = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "inputPhoneContact#f392b7f4";
    }

}
