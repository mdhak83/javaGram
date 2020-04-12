package org.javagram.api.contacts.functions;

import org.javagram.api.contacts.base.TLContactsImportedContacts;
import org.javagram.api.phone.base.contact.input.TLInputPhoneContact;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Imports contacts: saves a full list on the server, adds already registered contacts to the contact list, returns added contacts and their info.
 * contacts.importContacts#2c800be5 contacts:Vector&lt;InputContact&gt; = contacts.ImportedContacts;
 */
public class TLRequestContactsImportContacts extends TLMethod<TLContactsImportedContacts> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2c800be5;

    /**
     * List of contacts to import
     */
    private TLVector<TLInputPhoneContact> contacts;
    
    public TLRequestContactsImportContacts() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLInputPhoneContact> getContacts() {
        return this.contacts;
    }

    public void setContacts(TLVector<TLInputPhoneContact> value) {
        this.contacts = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.contacts, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.contacts = StreamingUtils.readTLVector(stream, context);
    }

    @Override
    public TLContactsImportedContacts deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLContactsImportedContacts) { 
            return (TLContactsImportedContacts) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.contacts.TLImportedContacts, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "contacts.importContacts#2c800be5";
    }

}