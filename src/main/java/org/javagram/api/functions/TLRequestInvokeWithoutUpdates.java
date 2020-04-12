package org.javagram.api.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Invoke a request without subscribing the used connection for @see <a href="https://core.telegram.org/api/updates">updates</a> (this is enabled by default for @see <a href="https://core.telegram.org/api/files">file queries</a>).
 * invokeWithoutUpdates#bf9459b7 {X:Type} query:!X = X;
 */
public class TLRequestInvokeWithoutUpdates extends TLMethod<TLObject> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbf9459b7;

    /**
     * The query
     */
    private TLMethod query;

    public TLRequestInvokeWithoutUpdates() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLMethod getQuery() {
        return this.query;
    }

    public void setQuery(TLMethod query) {
        this.query = query;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLMethod(this.query, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.query = StreamingUtils.readTLMethod(stream, context);
    }

    @Override
    public TLObject deserializeResponse(InputStream stream, TLContext context) throws IOException {
        return this.query.deserializeResponse(stream, context);
    }

    @Override
    public String toString() {
        return "invokeWithoutUpdates#bf9459b7";
    }

}