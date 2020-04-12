package org.telegram.api.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.message.base.TLMessageRange;

/**
 * Invoke with the given message range
 * invokeWithMessagesRange#365275f2 {X:Type} range:MessageRange query:!X = X;
 */
public class TLRequestInvokeWithMessagesRange extends TLMethod<TLObject> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x365275f2;

    /**
     * Message range
     */
    private TLMessageRange range;
    
    /**
     * The query
     */
    private TLMethod query;

    public TLRequestInvokeWithMessagesRange() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLMessageRange getRange() {
        return range;
    }

    public void setRange(TLMessageRange range) {
        this.range = range;
    }

    public TLMethod getQuery() {
        return this.query;
    }

    public void setQuery(TLMethod query) {
        this.query = query;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.range, stream);
        StreamingUtils.writeTLMethod(this.query, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.range = StreamingUtils.readTLObject(stream, context, TLMessageRange.class);
        this.query = StreamingUtils.readTLMethod(stream, context);
    }

    @Override
    public TLObject deserializeResponse(InputStream stream, TLContext context) throws IOException {
        return this.query.deserializeResponse(stream, context);
    }

    @Override
    public String toString() {
        return "invokeWithMessagesRange#365275f2";
    }

}