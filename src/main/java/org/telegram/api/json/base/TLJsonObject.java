package org.telegram.api.json.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;

/**
 * JSON object value
 * jsonObject#99c1d49d value:Vector&lt;JSONObjectValue&gt; = JSONValue;
 */
public class TLJsonObject extends TLAbsJSONValue {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x99c1d49d;
    
    /**
     * JSON values
     */
    private TLVector<TLJSONObjectValue> value;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLJSONObjectValue> getValue() {
        return value;
    }

    public void setValue(TLVector<TLJSONObjectValue> value) {
        this.value = value;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.value, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.value = StreamingUtils.readTLVector(stream, context, TLJSONObjectValue.class);
    }

    @Override
    public String toString() {
        return "jsonObject#99c1d49d";
    }

}