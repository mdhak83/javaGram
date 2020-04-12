package org.telegram.api.json.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represents a json-encoded object
 * dataJSON#7d748d04 data:string = DataJSON;
 */
public class TLDataJSON extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7d748d04;

    /**
     * JSON-encoded object
     */
    private String data;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.data, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.data = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "dataJSON#7d748d04";
    }
    
}