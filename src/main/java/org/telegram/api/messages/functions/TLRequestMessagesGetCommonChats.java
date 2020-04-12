package org.telegram.api.messages.functions;

import org.telegram.api.user.base.input.TLAbsInputUser;
import org.telegram.api.messages.base.chats.TLAbsMessagesChats;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLRequestMessagesGetCommonChats extends TLMethod<TLAbsMessagesChats> {
    public static final int CLASS_ID = 0xd0a48c4;

    private TLAbsInputUser userId;
    private int maxId;
    private int limit;

    public TLRequestMessagesGetCommonChats() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAbsMessagesChats deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if (res instanceof TLAbsMessagesChats)
            return (TLAbsMessagesChats) res;
        throw new IOException("Incorrect response type. Expected " + TLAbsMessagesChats.class.getCanonicalName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    public TLAbsInputUser getUserId() {
        return userId;
    }

    public void setUserId(TLAbsInputUser userId) {
        this.userId = userId;
    }

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.userId, stream);
        StreamingUtils.writeInt(this.maxId, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
        this.maxId = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messages.getCommonChats#d0a48c4";
    }

}