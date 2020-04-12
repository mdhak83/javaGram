package org.javagram.api.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLLongVector;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Invokes a query after a successfull completion of previous queries
 * invokeAfterMsgs#3dc4b4f0 {X:Type} msg_ids:Vector&lt;long&gt; query:!X = X;
 */
public class TLRequestInvokeAfterMsgs extends TLMethod<TLObject> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3dc4b4f0;

    /**
     * List of messages on which a current query depends
     */
    private TLLongVector msgIds;
    
    /**
     * The query itself
     */
    private TLMethod query;

    public TLRequestInvokeAfterMsgs() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLLongVector getMsgIds() {
        return this.msgIds;
    }

    public void setMsgIds(TLLongVector value) {
        this.msgIds = value;
    }

    public TLMethod getQuery() {
        return this.query;
    }

    public void setQuery(TLMethod value) {
        this.query = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.msgIds, stream);
        StreamingUtils.writeTLMethod(this.query, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.msgIds = StreamingUtils.readTLLongVector(stream, context);
        this.query = StreamingUtils.readTLMethod(stream, context);
    }

    @Override
    public TLObject deserializeResponse(InputStream stream, TLContext context) throws IOException {
        return this.query.deserializeResponse(stream, context);
    }

    @Override
    public String toString() {
        return "invokeAfterMsgs#3dc4b4f0";
    }
    
}