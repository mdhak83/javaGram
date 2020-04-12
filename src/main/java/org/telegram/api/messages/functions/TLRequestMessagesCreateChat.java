package org.telegram.api.messages.functions;

import org.telegram.api.user.base.input.TLAbsInputUser;
import org.telegram.api.updates.base.TLAbsUpdates;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Creates a new chat.
 * messages.createChat#9cb126e users:Vector&lt;InputUser&gt; title:string = Updates;
 */
public class TLRequestMessagesCreateChat extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9cb126e;

    /**
     * List of user IDs to be invited
     */
    private TLVector<TLAbsInputUser> users;
    
    /**
     * Chat name
     */
    private String title;

    public TLRequestMessagesCreateChat() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsInputUser> getUsers() {
        return this.users;
    }

    public void setUsers(TLVector<TLAbsInputUser> value) {
        this.users = value;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.users, stream);
        StreamingUtils.writeTLString(this.title, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.users = StreamingUtils.readTLVector(stream, context);
        this.title = StreamingUtils.readTLString(stream);
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if (res instanceof TLAbsUpdates)
            return (TLAbsUpdates) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.updates.TLAbsUpdates, got: " + res.getClass().getCanonicalName());
    }

    @Override
    public String toString() {
        return "messages.createChat#9cb126e";
    }

}