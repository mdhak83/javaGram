package org.telegram.api.input;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.json.base.TLAbsJSONValue;

/**
 * inputAppEvent
 * Event that occured in the application.
 * inputAppEvent#1d1b1245 time:double type:string peer:long data:JSONValue = InputAppEvent;
 */
public class TLInputAppEvent extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1d1b1245;

    /**
     * Client's exact timestamp for the event
     */
    private double time;
    
    /**
     * Type of event
     */
    private String type;
    
    /**
     * Arbitrary numeric value for more convenient selection of certain event types, or events referring to a certain object
     */
    private long peer;
    
    /**
     * Details of the event
     */
    private TLAbsJSONValue data;

    public TLInputAppEvent() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getPeer() {
        return peer;
    }

    public void setPeer(long peer) {
        this.peer = peer;
    }

    public TLAbsJSONValue getData() {
        return data;
    }

    public void setData(TLAbsJSONValue data) {
        this.data = data;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeDouble(this.time, stream);
        StreamingUtils.writeTLString(this.type, stream);
        StreamingUtils.writeLong(this.peer, stream);
        StreamingUtils.writeTLObject(this.data, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.time = StreamingUtils.readDouble(stream);
        this.type = StreamingUtils.readTLString(stream);
        this.peer = StreamingUtils.readLong(stream);
        this.data = StreamingUtils.readTLObject(stream, context, TLAbsJSONValue.class);
    }

    @Override
    public String toString() {
        return "inputAppEvent#1d1b1245";
    }

}