package org.telegram.api.contacts.functions;

import org.telegram.api.contacts.base.blocked.TLAbsBlocked;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Returns the list of blocked users.
 * contacts.getBlocked#f57c350f offset:int limit:int = contacts.Blocked;
 */
public class TLRequestContactsGetBlocked extends TLMethod<TLAbsBlocked> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf57c350f;

    /**
     * The number of list elements to be skipped
     */
    private int offset;
    
    /**
     * The number of list elements to be returned
     */
    private int limit;

    public TLRequestContactsGetBlocked() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int value) {
        this.offset = value;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int value) {
        this.limit = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.offset, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.offset = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsBlocked deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsBlocked) {
            return (TLAbsBlocked) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.contacts.blocked.TLAbsBlocked, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "contacts.getBlocked#f57c350f";
    }

}