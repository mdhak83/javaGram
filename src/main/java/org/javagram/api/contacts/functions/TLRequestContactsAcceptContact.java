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
 * If the @see <a href="https://core.telegram.org/constructor/peerSettings">peer settings</a> of a new user allow us to add him as contact, add that user as contact
 * contacts.acceptContact#f831a20f id:InputUser = Updates;
 */
public class TLRequestContactsAcceptContact extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf831a20f;

    /**
     * The user to add as contact
     */
    private TLAbsInputUser id;
    
    public TLRequestContactsAcceptContact() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputUser getId() {
        return id;
    }

    public void setId(TLAbsInputUser id) {
        this.id = id;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
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
        return "contacts.acceptContact#f831a20f";
    }

}