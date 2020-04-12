package org.telegram.api.users.functions;

import org.telegram.api.user.base.input.TLAbsInputUser;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Returns basic user info according to their identifiers.
 * users.getUsers#d91a548 id:Vector&lt;InputUser&gt; = Vector&lt;User&gt;;
 */
public class TLRequestUsersGetUsers extends TLMethod<TLVector<TLAbsUser>> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd91a548;

    /**
     * List of user identifiers
     */
    protected TLVector<TLAbsInputUser> id;

    public TLRequestUsersGetUsers() {
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
    public TLVector<TLAbsUser> deserializeResponse(InputStream stream, TLContext context) throws IOException {
        return StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "users.getUsers#d91a548";
    }

}