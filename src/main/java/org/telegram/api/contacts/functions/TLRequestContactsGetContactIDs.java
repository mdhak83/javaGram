package org.telegram.api.contacts.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLIntVector;

/**
 * Get contact by telegram IDs
 * contacts.getContactIDs#2caa4a42 hash:int = Vector&lt;int&gt;;
 */
public class TLRequestContactsGetContactIDs extends TLMethod<TLIntVector> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2caa4a42;

    /**
     * 	@see <a href="https://core.telegram.org/api/offsets#hash-generation">Hash for pagination, for more info click here</a>
     */
    private int hash;

    public TLRequestContactsGetContactIDs() {
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
    public TLIntVector deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLIntVector) {
            return (TLIntVector) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLAbsUser, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "contacts.getContactIDs#2caa4a42";
    }

}