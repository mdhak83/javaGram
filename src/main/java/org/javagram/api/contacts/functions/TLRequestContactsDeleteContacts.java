package org.javagram.api.contacts.functions;

import org.javagram.api.user.base.input.TLAbsInputUser;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.updates.base.TLAbsUpdates;

/**
 * Deletes several contacts from the list.
 * contacts.deleteContacts#96a0e00 id:Vector&lt;InputUser&gt; = Updates;
 */
public class TLRequestContactsDeleteContacts extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x96a0e00;

    /**
     * User ID list
     */
    private TLVector<TLAbsInputUser> id;

    public TLRequestContactsDeleteContacts() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsInputUser> getId() {
        return this.id;
    }

    public void setId(TLVector<TLAbsInputUser> value) {
        this.id = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readTLVector(stream, context, TLAbsInputUser.class);
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "contacts.deleteContacts#96a0e00";
    }

}