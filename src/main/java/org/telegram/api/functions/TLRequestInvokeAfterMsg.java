package org.telegram.api.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Invokes a query after successfull completion of one of the previous queries.
 * invokeAfterMsg#cb9f372d {X:Type} msg_id:long query:!X = X;
 */
public class TLRequestInvokeAfterMsg extends TLMethod<TLObject> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xcb9f372d;

    /**
     * Message identifier on which a current query depends
     */
    private long msgId;
    
    /**
     * The query itself
     */
    private TLMethod query;

    public TLRequestInvokeAfterMsg() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getMsgId() {
        return this.msgId;
    }

    public void setMsgId(long value) {
        this.msgId = value;
    }

    public TLMethod getQuery() {
        return this.query;
    }

    public void setQuery(TLMethod value) {
        this.query = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.msgId, stream);
        StreamingUtils.writeTLMethod(this.query, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.msgId = StreamingUtils.readLong(stream);
        this.query = StreamingUtils.readTLMethod(stream, context);
    }

    @Override
    public TLObject deserializeResponse(InputStream stream, TLContext context) throws IOException {
        return this.query.deserializeResponse(stream, context);
    }

    @Override
    public String toString() {
        return "invokeAfterMsg#cb9f372d";
    }

}