package org.javagram.api.messages.functions;

import org.javagram.api.messages.base.chats.TLAbsMessagesChats;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLIntVector;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.utils.StreamingUtils;

public class TLRequestMessagesGetAllChats extends TLMethod<TLAbsMessagesChats> {

    public static final int CLASS_ID = 0xeba80ff0;

    private TLIntVector exceptIds;

    public TLRequestMessagesGetAllChats() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAbsMessagesChats deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsMessagesChats) {
            return (TLAbsMessagesChats) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsMessagesChats.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    public TLIntVector getExceptIds() {
        return exceptIds;
    }

    public void setExceptIds(TLIntVector exceptIds) {
        this.exceptIds = exceptIds;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.exceptIds, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.exceptIds = StreamingUtils.readTLIntVector(stream, context);
    }

    @Override
    public String toString() {
        return "messages.getAllChats#eba80ff0";
    }

}