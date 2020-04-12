package org.telegram.api.secure.base.valueerror;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.secure.base.valuetype.TLAbsSecureValueType;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;

/**
 * Represents an issue with the translated version of a document. The error is considered resolved when a file with the document translation changes.
 * secureValueErrorTranslationFiles#34636dd8 type:SecureValueType file_hash:Vector&lt;bytes&gt; text:string = SecureValueError;
 */
public class TLSecureValueErrorTranslationFiles extends TLAbsSecureValueError {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x34636dd8;

    /**
     * One of secureValueTypePersonalDetails, secureValueTypePassport, secureValueTypeDriverLicense, secureValueTypeIdentityCard, secureValueTypeInternalPassport, secureValueTypeUtilityBill, secureValueTypeBankStatement, secureValueTypeRentalAgreement, secureValueTypePassportRegistration, secureValueTypeTemporaryRegistration
     */
    private TLAbsSecureValueType type;
    
    /**
     * File hash
     */
    private TLVector<TLBytes> fileHash;
    
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

    public TLVector<TLBytes> getFileHash() {
        return fileHash;
    }

    public void setFileHash(TLVector<TLBytes> fileHash) {
        this.fileHash = fileHash;
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
        StreamingUtils.writeTLVector(this.fileHash, stream);
        StreamingUtils.writeTLString(this.text, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.type = StreamingUtils.readTLObject(stream, context, TLAbsSecureValueType.class);
        this.fileHash = StreamingUtils.readTLVector(stream, context, TLBytes.class);
        this.text = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "secureValueErrorTranslationFiles#34636dd8";
    }

}