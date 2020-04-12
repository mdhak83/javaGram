package org.javagram.api.secure.base.valueerror;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api.secure.base.valuetype.TLAbsSecureValueType;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;

/**
 * secureValueErrorData
 * Represents an issue in one of the data fields that was provided by the user. The error is considered resolved when the field's value changes.
 * secureValueErrorData#e8a40bd9 type:SecureValueType data_hash:bytes field:string text:string = SecureValueError;
 */
public class TLSecureValueErrorData extends TLAbsSecureValueError {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe8a40bd9;

    /**
     * The section of the user's Telegram Passport which has the error, one of secureValueTypePersonalDetails, secureValueTypePassport, secureValueTypeDriverLicense, secureValueTypeIdentityCard, secureValueTypeInternalPassport, secureValueTypeAddress
     */
    private TLAbsSecureValueType type;
    
    /**
     * Data hash
     */
    private TLBytes dataHash;
    
    /**
     * Name of the data field which has the error
     */
    private String field;
    
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

    public TLBytes getDataHash() {
        return dataHash;
    }

    public void setDataHash(TLBytes dataHash) {
        this.dataHash = dataHash;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
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
        StreamingUtils.writeTLBytes(this.dataHash, stream);
        StreamingUtils.writeTLString(this.field, stream);
        StreamingUtils.writeTLString(this.text, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.type = StreamingUtils.readTLObject(stream, context, TLAbsSecureValueType.class);
        this.dataHash = StreamingUtils.readTLBytes(stream, context);
        this.field = StreamingUtils.readTLString(stream);
        this.text = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "secureValueErrorData#e8a40bd9";
    }

}