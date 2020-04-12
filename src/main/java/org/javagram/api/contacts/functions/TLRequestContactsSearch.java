package org.javagram.api.contacts.functions;

import org.javagram.api.contacts.base.TLContactsFound;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Returns users found by username substring.
 * contacts.search#11f812d8 q:string limit:int = contacts.Found;
 */
public class TLRequestContactsSearch extends TLMethod<TLContactsFound> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x11f812d8;

    /**
     * Target substring
     */
    private String q;
    
    /**
     * Maximum number of users to be returned
     */
    private int limit;

    public TLRequestContactsSearch() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getQ() {
        return this.q;
    }

    public void setQ(String value) {
        this.q = value;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int value) {
        this.limit = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.q, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.q = StreamingUtils.readTLString(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    @Override
    public TLContactsFound deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if (res instanceof TLContactsFound)
            return (TLContactsFound) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.contacts.TLFound, got: " + res.getClass().getCanonicalName());
    }

    @Override
    public String toString() {
        return "contacts.search#11f812d8";
    }

}