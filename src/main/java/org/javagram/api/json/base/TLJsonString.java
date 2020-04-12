package org.javagram.api.json.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;

/**
 * JSON string
 * jsonString#b71e767a value:string = JSONValue;
 */
public class TLJsonString extends TLAbsJSONValue {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb71e767a;
    
    /**
     * Value
     */
    private String value;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.value, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.value = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "jsonString#b71e767a";
    }

}