package org.javagram.api.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Invoke a method within a takeout session
 * invokeWithTakeout#aca9fd2e {X:Type} takeout_id:long query:!X = X;
 */
public class TLRequestInvokeWithTakeout extends TLMethod<TLObject> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xaca9fd2e;

    /**
     * Takeout session ID
     */
    private long takeoutId;
    
    /**
     * The query
     */
    private TLMethod query;

    public TLRequestInvokeWithTakeout() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getTakeoutId() {
        return takeoutId;
    }

    public void setTakeoutId(long takeoutId) {
        this.takeoutId = takeoutId;
    }

    public TLMethod getQuery() {
        return this.query;
    }

    public void setQuery(TLMethod query) {
        this.query = query;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.takeoutId, stream);
        StreamingUtils.writeTLMethod(this.query, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.takeoutId = StreamingUtils.readLong(stream);
        this.query = StreamingUtils.readTLMethod(stream, context);
    }

    @Override
    public TLObject deserializeResponse(InputStream stream, TLContext context) throws IOException {
        return this.query.deserializeResponse(stream, context);
    }

    @Override
    public String toString() {
        return "invokeWithTakeout#aca9fd2e";
    }

}