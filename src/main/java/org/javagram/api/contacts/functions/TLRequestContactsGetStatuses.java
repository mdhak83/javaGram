package org.javagram.api.contacts.functions;

import org.javagram.api.contact.base.TLContactStatus;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;

/**
 * Returns the list of contact statuses.
 * contacts.getStatuses#c4a353ee = Vector&lt;ContactStatus&gt;;
 */
public class TLRequestContactsGetStatuses extends TLMethod<TLVector<TLContactStatus>> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc4a353ee;

    public TLRequestContactsGetStatuses() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLVector<TLContactStatus> deserializeResponse(InputStream stream, TLContext context) throws IOException {
        return StreamingUtils.readTLVector(stream, context, TLContactStatus.class);
    }

    @Override
    public String toString() {
        return "contacts.getStatuses#c4a353ee";
    }

}