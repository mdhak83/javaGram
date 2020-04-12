package org.telegram.api.contacts.functions;

import org.telegram.api.contacts.base.TLAbsContacts;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Returns the current user's contact list.
 * contacts.getContacts#c023849f hash:int = contacts.Contacts;
 */
public class TLRequestContactsGetContacts extends TLMethod<TLAbsContacts> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc023849f;

    /**
     * If there already is a full contact list on the client, a @see <a href="https://core.telegram.org/api/offsets#hash-generation">hash</a> of a the list of contact IDs in ascending order may be passed in this parameter. If the contact set was not changed, (@see <a href="https://core.telegram.org/constructor/contacts.contactsNotModified">contacts.contactsNotModified</a>) will be returned.
     */
    private int hash;

    public TLRequestContactsGetContacts() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.hash = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsContacts deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsContacts) {
            return (TLAbsContacts) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.contacts.TLAbsContacts, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "contacts.getContacts#c023849f";
    }

}