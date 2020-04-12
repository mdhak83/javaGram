package org.javagram.api.secure.base.valueerror;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.secure.base.valuetype.TLAbsSecureValueType;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;

/**
 * Represents an issue with a list of scans. The error is considered resolved when the list of files containing the scans changes.
 * secureValueErrorFiles#666220e9 type:SecureValueType file_hash:Vector&lt;bytes&gt; text:string = SecureValueError;
 */
public class TLSecureValueErrorFiles extends TLAbsSecureValueError {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x666220e9;

    /**
     * One of secureValueTypeUtilityBill, secureValueTypeBankStatement, secureValueTypeRentalAgreement, secureValueTypePassportRegistration, secureValueTypeTemporaryRegistration
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
        return "secureValueErrorFiles#666220e9";
    }

}