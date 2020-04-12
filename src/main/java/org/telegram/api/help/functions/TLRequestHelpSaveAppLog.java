package org.telegram.api.help.functions;

import org.telegram.api.input.TLInputAppEvent;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBool;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Saves logs of application on the server.
 * help.saveAppLog#6f02f748 events:Vector&lt;InputAppEvent&gt; = Bool;
 */
public class TLRequestHelpSaveAppLog extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6f02f748;

    /**
     * List of input events
     */
    private TLVector<TLInputAppEvent> events;

    public TLRequestHelpSaveAppLog() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLInputAppEvent> getEvents() {
        return this.events;
    }

    public void setEvents(TLVector<TLInputAppEvent> value) {
        this.events = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.events, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.events = StreamingUtils.readTLVector(stream, context);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if (res instanceof TLBool)
            return (TLBool) res;
        throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
    }

    @Override
    public String toString() {
        return "help.saveAppLog#6f02f748";
    }

}