package org.javagram.api.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLLayer;

/**
 * Invoke the specified query using the specified API @see <a href="https://core.telegram.org/api/invoking#layers">layer</a>
 * invokeWithLayer#da9b0d0d {X:Type} layer:int query:!X = X;
 */
public class TLRequestInvokeWithLayer extends TLMethod<TLObject> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xda9b0d0d;

    private int layer = TLLayer.VALUE;

    private TLMethod query;

    public TLRequestInvokeWithLayer() {
        super();
    }

    public TLRequestInvokeWithLayer(TLMethod query) {
        super();
        this.setQuery(query);
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLMethod getQuery() {
        return this.query;
    }

    public final void setQuery(TLMethod value) {
        this.query = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.layer, stream);
        StreamingUtils.writeTLMethod(this.query, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        int _layer = StreamingUtils.readInt(stream);
        if (_layer != this.layer) {
            int k = 0;
        }
        this.query = StreamingUtils.readTLMethod(stream, context);
    }

    @Override
    public TLObject deserializeResponse(InputStream stream, TLContext context) throws IOException {
        return this.query.deserializeResponse(stream, context);
    }

    @Override
    public String toString() {
        return "invokeWithLayer#da9b0d0d";
    }

}