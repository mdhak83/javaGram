package org.telegram.api.json.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;

/**
 * JSON boolean value
 * jsonBool#c7345e6a value:Bool = JSONValue;
 */
public class TLJsonBool extends TLAbsJSONValue {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc7345e6a;
    
    /**
     * Value
     */
    private boolean value;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBool(this.value, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.value = StreamingUtils.readTLBool(stream);
    }

    @Override
    public String toString() {
        return "jsonBool#c7345e6a";
    }

}