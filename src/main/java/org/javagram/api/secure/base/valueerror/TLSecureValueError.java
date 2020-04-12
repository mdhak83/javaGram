package org.javagram.api.secure.base.valueerror;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api.secure.base.valuetype.TLAbsSecureValueType;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;

/**
 * secureValueError
 * Secure value error
 * secureValueError#869d758f type:SecureValueType hash:bytes text:string = SecureValueError;
 */
public class TLSecureValueError extends TLAbsSecureValueError {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x869d758f;

    /**
     * Type of element which has the issue
     */
    private TLAbsSecureValueType type;
    
    /**
     * File hash
     */
    private TLBytes hash;
    
    /**
     * Error message
     */
    private String text;
    

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsSecureValueType getType() {
        return type;
    }

    public void setType(TLAbsSecureValueType type) {
        this.type = type;
    }

    public TLBytes getHash() {
        return hash;
    }

    public void setHash(TLBytes hash) {
        this.hash = hash;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.type, stream);
        StreamingUtils.writeTLBytes(this.hash, stream);
        StreamingUtils.writeTLString(this.text, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.type = StreamingUtils.readTLObject(stream, context, TLAbsSecureValueType.class);
        this.hash = StreamingUtils.readTLBytes(stream, context);
        this.text = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "secureValueError#869d758f";
    }

}