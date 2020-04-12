package org.telegram.api.contacts.base;

import org.telegram.api.contact.base.TLImportedContact;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLLongVector;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.contact.base.TLPopularContact;

/**
 * Info on succesfully imported contacts.
 * contacts.importedContacts#77d01c3b imported:Vector&lt;ImportedContact&gt; popular_invites:Vector&lt;PopularContact&gt; retry_contacts:Vector&lt;long&gt; users:Vector&lt;User&gt; = contacts.ImportedContacts;
 */
public class TLContactsImportedContacts extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x77d01c3b;

    /**
     * List of succesfully imported contacts
     */
    private TLVector<TLImportedContact> imported;
    
    /**
     * Popular contacts
     */
    private TLVector<TLPopularContact> popularInvites;
    
    /**
     * 	List of contact ids that could not be imported due to system limitation and will need to be imported at a later date.
     *  Parameter added in Layer 13
     */
    private TLLongVector retryContacts = new TLLongVector();
    
    /**
     * List of users
     */
    private TLVector<TLAbsUser> users;

    public TLContactsImportedContacts() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLImportedContact> getImported() {
        return this.imported;
    }

    public void setImported(TLVector<TLImportedContact> value) {
        this.imported = value;
    }

    public TLVector<TLPopularContact> getPopularInvites() {
        return popularInvites;
    }

    public void setPopularInvites(TLVector<TLPopularContact> popularInvites) {
        this.popularInvites = popularInvites;
    }

    public TLLongVector getRetryContacts() {
        return this.retryContacts;
    }

    public void setRetryContacts(TLLongVector retryContacts) {
        this.retryContacts = retryContacts;
    }

    public TLVector<TLAbsUser> getUsers() {
        return this.users;
    }

    public void setUsers(TLVector<TLAbsUser> value) {
        this.users = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.imported, stream);
        StreamingUtils.writeTLVector(this.popularInvites, stream);
        StreamingUtils.writeTLVector(this.retryContacts, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.imported = StreamingUtils.readTLVector(stream, context, TLImportedContact.class);
        this.popularInvites = StreamingUtils.readTLVector(stream, context, TLPopularContact.class);
        this.retryContacts = StreamingUtils.readTLLongVector(stream, context);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "contacts.importedContacts#77d01c3b";
    }

}