package org.javagram.api.secure.base.valueerror;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api.secure.base.valuetype.TLAbsSecureValueType;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;

/**
 * secureValueErrorReverseSide
 * Represents an issue with the reverse side of a document. The error is considered resolved when the file with reverse side of the document changes.
 * secureValueErrorReverseSide#868a2aa5 type:SecureValueType file_hash:bytes text:string = SecureValueError;
 */
public class TLSecureValueErrorReverseSide extends TLAbsSecureValueError {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x868a2aa5;

    /**
     * One of secureValueTypeDriverLicense, secureValueTypeIdentityCard
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
        return "secureValueErrorReverseSide#868a2aa5";
    }

}