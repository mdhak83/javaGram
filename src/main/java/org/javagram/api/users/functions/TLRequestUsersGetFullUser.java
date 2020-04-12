package org.javagram.api.users.functions;

import org.javagram.api.user.base.input.TLAbsInputUser;
import org.javagram.api.user.base.TLUserFull;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Returns extended user info by ID.
 * users.getFullUser#ca30a5b1 id:InputUser = UserFull;
 */
public class TLRequestUsersGetFullUser extends TLMethod<TLUserFull> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xca30a5b1;

    /**
     * User ID
     */
    private TLAbsInputUser id;

    public TLRequestUsersGetFullUser() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputUser getId() {
        return this.id;
    }

    public void setId(TLAbsInputUser value) {
        this.id = value;
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
    public TLUserFull deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLUserFull)  {
            return (TLUserFull) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.user.TLUserFull, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "users.getFullUser#ca30a5b1";
    }

}