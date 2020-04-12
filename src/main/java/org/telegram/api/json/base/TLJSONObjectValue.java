package org.telegram.api.json.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * JSON key:value pair
 * jsonObjectValue#c0de1bd9 key:string value:JSONValue = JSONObjectValue;
 */
public class TLJSONObjectValue extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc0de1bd9;

    /**
     * Key
     */
    private String key;

    /**
     * Value
     */
    private TLAbsJSONValue value;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public TLAbsJSONValue getValue() {
        return value;
    }

    public void setValue(TLAbsJSONValue value) {
        this.value = value;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.key, stream);
        StreamingUtils.writeTLObject(this.value, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.key = StreamingUtils.readTLString(stream);
        this.value = StreamingUtils.readTLObject(stream, context, TLAbsJSONValue.class);
    }

    @Override
    public String toString() {
        return "jsonObjectValue#c0de1bd9";
    }

}