package org.javagram.api.contact.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Saved contact
 * savedPhoneContact#1142bd56 phone:string first_name:string last_name:string date:int = SavedContact;
 */
public class TLSavedPhoneContact extends TLAbsSavedContact {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1142bd56;

    /**
     * Phone number
     */
    private String phone;
    
    /**
     * First name
     */
    private String firstName;
    
    /**
     * Last name
     */
    private String lastName;
    
    /**
     * Date added
     */
    private int date;
    

    public TLSavedPhoneContact() {
        super();
    }

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

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.phone, stream);
        StreamingUtils.writeTLString(this.firstName, stream);
        StreamingUtils.writeTLString(this.lastName, stream);
        StreamingUtils.writeInt(this.date, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.phone = StreamingUtils.readTLString(stream);
        this.firstName = StreamingUtils.readTLString(stream);
        this.lastName = StreamingUtils.readTLString(stream);
        this.date = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "savedPhoneContact#1142bd56";
    }

}