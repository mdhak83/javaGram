package org.telegram.api.secure.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLVector;

/**
 * One of
 * secureRequiredTypeOneOf#27477b4 types:Vector&lt;SecureRequiredType&gt; = SecureRequiredType;
 */
public class TLSecureRequiredTypeOneOf extends TLAbsSecureRequiredType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x27477b4;

    /**
     * Secure required value types
     */
    private TLVector<TLAbsSecureRequiredType> types;
    

    public TLSecureRequiredTypeOneOf() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsSecureRequiredType> getTypes() {
        return types;
    }

    public void setType(TLVector<TLAbsSecureRequiredType> types) {
        this.types = types;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.types, stream);
   }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.types = StreamingUtils.readTLVector(stream, context, TLAbsSecureRequiredType.class);
    }

    @Override
    public String toString() {
        return "secureRequiredTypeOneOf#27477b4";
    }

}