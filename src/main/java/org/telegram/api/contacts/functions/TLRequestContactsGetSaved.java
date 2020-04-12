package org.telegram.api.contacts.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import org.telegram.api.contact.base.TLAbsSavedContact;

/**
 * Get all contacts
 * contacts.getSaved#82f1e39f = Vector&lt;SavedContact&gt;;
 */
public class TLRequestContactsGetSaved extends TLMethod<TLVector<TLAbsSavedContact>> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x82f1e39f;

    public TLRequestContactsGetSaved() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLVector<TLAbsSavedContact> deserializeResponse(InputStream stream, TLContext context) throws IOException {
        return StreamingUtils.readTLVector(stream, context, TLAbsSavedContact.class);
    }

    @Override
    public String toString() {
        return "contacts.getSaved#82f1e39f";
    }

}