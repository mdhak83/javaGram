package org.javagram.api.contacts.base;

import org.javagram.api.contact.base.TLContact;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The current user's contact list and info on users.
 */
public class TLContacts extends TLAbsContacts {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xeae87e42;

    /**
     * Contact list
     */
    private TLVector<TLContact> contacts;

    /**
     * Number of contacts that were saved successfully
     */
    private int savedCount;

    /**
     * User list
     */
    private TLVector<TLAbsUser> users;

    public TLContacts() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets contacts.
     *
     * @return the contacts
     */
    public TLVector<TLContact> getContacts() {
        return this.contacts;
    }

    /**
     * Sets contacts.
     *
     * @param contacts the contacts
     */
    public void setContacts(TLVector<TLContact> contacts) {
        this.contacts = contacts;
    }

     public int getSavedCount() {
        return savedCount;
    }

    public void setSavedCount(int savedCount) {
        this.savedCount = savedCount;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public TLVector<TLAbsUser> getUsers() {
        return this.users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(TLVector<TLAbsUser> users) {
        this.users = users;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.contacts, stream);
        StreamingUtils.writeInt(this.savedCount, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.contacts = StreamingUtils.readTLVector(stream, context, TLContact.class);
        this.savedCount = StreamingUtils.readInt(stream);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "contacts.contacts#eae87e42";
    }

}