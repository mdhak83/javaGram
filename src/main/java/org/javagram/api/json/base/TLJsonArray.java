package org.javagram.api.json.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;

/**
 * JSON array
 * jsonArray#f7444763 value:Vector&lt;JSONValue&gt; = JSONValue;
 */
public class TLJsonArray extends TLAbsJSONValue {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf7444763;
    
    /**
     * JSON values
     */
    private TLVector<TLAbsJSONValue> value;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsJSONValue> getValue() {
        return value;
    }

    public void setValue(TLVector<TLAbsJSONValue> value) {
        this.value = value;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.value, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.value = StreamingUtils.readTLVector(stream, context, TLAbsJSONValue.class);
    }

    @Override
    public String toString() {
        return "jsonArray#f7444763";
    }

}