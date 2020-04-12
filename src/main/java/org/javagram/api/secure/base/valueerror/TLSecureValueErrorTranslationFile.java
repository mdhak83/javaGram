package org.javagram.api.secure.base.valueerror;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api.secure.base.valuetype.TLAbsSecureValueType;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;

/**
 * secureValueErrorTranslationFile
 * Represents an issue with one of the files that constitute the translation of a document. The error is considered resolved when the file changes.
 * secureValueErrorTranslationFile#a1144770 type:SecureValueType file_hash:bytes text:string = SecureValueError;
 */
public class TLSecureValueErrorTranslationFile extends TLAbsSecureValueError {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa1144770;

    /**
     * One of secureValueTypePersonalDetails, secureValueTypePassport, secureValueTypeDriverLicense, secureValueTypeIdentityCard, secureValueTypeInternalPassport, secureValueTypeUtilityBill, secureValueTypeBankStatement, secureValueTypeRentalAgreement, secureValueTypePassportRegistration, secureValueTypeTemporaryRegistration
     */
    private TLAbsSecureValueType type;
    
    /**
     * File hash
     */
    private TLBytes fileHash;
    
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

    public TLBytes getFileHash() {
        return fileHash;
    }

    public void setFileHash(TLBytes fileHash) {
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
        StreamingUtils.writeTLBytes(this.fileHash, stream);
        StreamingUtils.writeTLString(this.text, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.type = StreamingUtils.readTLObject(stream, context, TLAbsSecureValueType.class);
        this.fileHash = StreamingUtils.readTLBytes(stream, context);
        this.text = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "secureValueErrorTranslationFile#a1144770";
    }

}