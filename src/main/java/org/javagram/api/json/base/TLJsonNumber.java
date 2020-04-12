package org.javagram.api.json.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;

/**
 * JSON numeric value
 * jsonNumber#2be0dfa4 value:double = JSONValue;
 */
public class TLJsonNumber extends TLAbsJSONValue {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2be0dfa4;
    
    /**
     * Value
     */
    private double value;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeDouble(this.value, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.value = StreamingUtils.readDouble(stream);
    }

    @Override
    public String toString() {
        return "jsonNumber#2be0dfa4";
    }

}