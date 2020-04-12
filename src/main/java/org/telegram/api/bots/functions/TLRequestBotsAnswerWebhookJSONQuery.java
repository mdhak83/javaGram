package org.telegram.api.bots.functions;

import org.telegram.api.json.base.TLDataJSON;
import org.telegram.api._primitives.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;

/**
 * bots.answerWebhookJSONQuery
 * Answers a custom query; for bots only
 * bots.answerWebhookJSONQuery#e6213f4d query_id:long data:DataJSON = Bool;
 */
public class TLRequestBotsAnswerWebhookJSONQuery extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe6213f4d;

    /**
     * Identifier of a custom query
     */
    private long queryId;
    
    /**
     * JSON-serialized answer to the query
     */
    private TLDataJSON data;

    public TLRequestBotsAnswerWebhookJSONQuery() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getQueryId() {
        return queryId;
    }

    public void setQueryId(long queryId) {
        this.queryId = queryId;
    }

    public TLDataJSON getData() {
        return data;
    }

    public void setData(TLDataJSON data) {
        this.data = data;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.queryId, stream);
        StreamingUtils.writeTLObject(this.data, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.queryId = StreamingUtils.readLong(stream);
        this.data = StreamingUtils.readTLObject(stream, context, TLDataJSON.class);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "bots.answerWebhookJSONQuery#e6213f4d";
    }

}