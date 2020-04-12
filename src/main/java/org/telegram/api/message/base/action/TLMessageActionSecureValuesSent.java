package org.telegram.api.message.base.action;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.secure.base.valuetype.TLAbsSecureValueType;
import org.telegram.api._primitives.TLVector;

/**
 * Request for secure @see <a href="https://core.telegram.org/passport">telegram passport</a> values was sent
 * messageActionSecureValuesSent#d95c6154 types:Vector&lt;SecureValueType&gt; = MessageAction;
 */
public class TLMessageActionSecureValuesSent extends TLAbsMessageAction {

    /**
     * 
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd95c6154;

    /**
     * Secure value types
     */
    private TLVector<TLAbsSecureValueType> types;
    
    public TLMessageActionSecureValuesSent() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsSecureValueType> getTypes() {
        return types;
    }

    public void setValues(TLVector<TLAbsSecureValueType> types) {
        this.types= types;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.types, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.types = StreamingUtils.readTLVector(stream, context, TLAbsSecureValueType.class);
    }

    @Override
    public String toString() {
        return "messageActionSecureValuesSent#d95c6154";
    }

}