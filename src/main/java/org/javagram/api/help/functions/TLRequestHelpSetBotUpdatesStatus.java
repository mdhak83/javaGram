package org.javagram.api.help.functions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.utils.StreamingUtils;

public class TLRequestHelpSetBotUpdatesStatus extends TLMethod<TLBool> {
    public static final int CLASS_ID = 0xec22cfcd;

    private int pendingUpdatesCount;
    private String message;

    public TLRequestHelpSetBotUpdatesStatus() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if (res instanceof TLBool)
            return (TLBool) res;
        throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName()
                + ", got: " + res.getClass().getCanonicalName());
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(pendingUpdatesCount, stream);
        StreamingUtils.writeTLString(message, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        pendingUpdatesCount = StreamingUtils.readInt(stream);
        message = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "help.setBotUpdatesStatus#ec22cfcd";
    }

}