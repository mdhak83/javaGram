package org.telegram.api.contacts.functions;

import org.telegram.api.contacts.base.TLContactsResolvedPeer;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Resolve a @username to get peer info
 * contacts.resolveUsername#f93ccba3 username:string = contacts.ResolvedPeer;
 */
public class TLRequestContactsResolveUsername extends TLMethod<TLContactsResolvedPeer> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf93ccba3;

    /**
     * username to resolve
     */
    private String username;

    public TLRequestContactsResolveUsername() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.username, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.username = StreamingUtils.readTLString(stream);
    }

    @Override
    public TLContactsResolvedPeer deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLContactsResolvedPeer) {
            return (TLContactsResolvedPeer) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLContactsResolvedPeer.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "contacts.resolveUsername#f93ccba3";
    }

}