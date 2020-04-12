package org.telegram.api.secure.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api.file.base.secure.TLSecureFile;
import org.telegram.api.secure.base.plain.TLAbsSecurePlainData;
import org.telegram.api.secure.base.valuetype.TLAbsSecureValueType;
import org.telegram.api._primitives.TLVector;

/**
 * secureValue
 * Secure Telegram passport value
 * secureValue#187fa0ca flags:# type:SecureValueType data:flags.0?SecureData front_side:flags.1?SecureFile reverse_side:flags.2?SecureFile selfie:flags.3?SecureFile translation:flags.6?Vecto&lt;SecureFile&gt; files:flags.4?Vector&lt;SecureFile&gt; plain_data:flags.5?SecurePlainData hash:bytes = SecureValue;
 */
public class TLSecureValue extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x187fa0ca;

    private static final int FLAG_DATA              = 0x00000001; // 0 : 
    private static final int FLAG_FRONT_SIDE        = 0x00000002; // 1 : 
    private static final int FLAG_REVERSE_SIDE      = 0x00000004; // 2 : 
    private static final int FLAG_SELFIE            = 0x00000008; // 3 : 
    private static final int FLAG_FILES             = 0x00000010; // 4 : 
    private static final int FLAG_PLAIN_DATA        = 0x00000020; // 5 : 
    private static final int FLAG_TRANSLATION       = 0x00000040; // 6 : 
    
    /**
     * Secure @see <a href="https://core.telegram.org/passport">passport</a> value type
     */
    private TLAbsSecureValueType type;
    
    /**
     * Encrypted @see <a href="https://core.telegram.org/passport">Telegram Passport</a> element data
     */
    private TLSecureData data;
    
    /**
     * Encrypted @see <a href="https://core.telegram.org/passport">passport</a> file with the front side of the document
     */
    private TLSecureFile frontSide;
    
    /**
     * Encrypted @see <a href="https://core.telegram.org/passport">passport</a> file with the reverse side of the document
     */
    private TLSecureFile reverseSide;
    
    /**
     * Encrypted @see <a href="https://core.telegram.org/passport">passport</a> file with a selfie of the user holding the document
     */
    private TLSecureFile selfie;
    
    /**
     * Array of encrypted @see <a href="https://core.telegram.org/passport">passport</a> files with translated versions of the provided documents
     */
    private TLVector<TLSecureFile> translation;
    
    /**
     * Array of encrypted @see <a href="https://core.telegram.org/passport">passport</a> files with photos the of the documents
     */
    private TLVector<TLSecureFile> files;
    
    /**
     * Plaintext verified @see <a href="https://core.telegram.org/passport">passport</a> data
     */
    private TLAbsSecurePlainData plainData;
    
    /**
     * Data hash
     */
    private TLBytes hash;

    public TLSecureValue() {
        super();
    }

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

    public TLSecureData getData() {
        return data;
    }

    public void setData(TLSecureData data) {
        this.data = data;
    }

    public TLSecureFile getFrontSide() {
        return frontSide;
    }

    public void setFrontSide(TLSecureFile frontSide) {
        this.frontSide = frontSide;
    }

    public TLSecureFile getReverseSide() {
        return reverseSide;
    }

    public void setReverseSide(TLSecureFile reverseSide) {
        this.reverseSide = reverseSide;
    }

    public TLSecureFile getSelfie() {
        return selfie;
    }

    public void setSelfie(TLSecureFile selfie) {
        this.selfie = selfie;
    }

    public TLVector<TLSecureFile> getTranslation() {
        return translation;
    }

    public void setTranslation(TLVector<TLSecureFile> translation) {
        this.translation = translation;
    }

    public TLVector<TLSecureFile> getFiles() {
        return files;
    }

    public void setFiles(TLVector<TLSecureFile> files) {
        this.files = files;
    }

    public TLAbsSecurePlainData getPlainData() {
        return plainData;
    }

    public void setPlainData(TLAbsSecurePlainData plainData) {
        this.plainData = plainData;
    }

    public TLBytes getHash() {
        return hash;
    }

    public void setHash(TLBytes hash) {
        this.hash = hash;
    }

    public boolean hasData() {
        return this.isFlagSet(FLAG_DATA);
    }
    
    public boolean hasFrontSide() {
        return this.isFlagSet(FLAG_FRONT_SIDE);
    }
    
    public boolean hasReverseSide() {
        return this.isFlagSet(FLAG_REVERSE_SIDE);
    }
    
    public boolean hasTranslation() {
        return this.isFlagSet(FLAG_TRANSLATION);
    }
    
    public boolean hasSelfie() {
        return this.isFlagSet(FLAG_SELFIE);
    }
    
    public boolean hasFiles() {
        return this.isFlagSet(FLAG_FILES);
    }
    
    public boolean hasPlainData() {
        return this.isFlagSet(FLAG_PLAIN_DATA);
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.type, stream);
        if (this.hasData()) {
            StreamingUtils.writeTLObject(this.data, stream);
        }
        if (this.hasFrontSide()) {
            StreamingUtils.writeTLObject(this.frontSide, stream);
        }
        if (this.hasReverseSide()) {
            StreamingUtils.writeTLObject(this.reverseSide, stream);
        }
        if (this.hasSelfie()) {
            StreamingUtils.writeTLObject(this.selfie, stream);
        }
        if (this.hasTranslation()) {
            StreamingUtils.writeTLVector(this.translation, stream);
        }
        if (this.hasFiles()) {
            StreamingUtils.writeTLVector(this.files, stream);
        }
        if (this.hasPlainData()) {
            StreamingUtils.writeTLObject(this.plainData, stream);
        }
        StreamingUtils.writeTLBytes(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.type = StreamingUtils.readTLObject(stream, context, TLAbsSecureValueType.class);
        if (this.hasData()) {
            this.data = StreamingUtils.readTLObject(stream, context, TLSecureData.class);
        }
        if (this.hasFrontSide()) {
            this.frontSide = StreamingUtils.readTLObject(stream, context, TLSecureFile.class);
        }
        if (this.hasReverseSide()) {
            this.reverseSide = StreamingUtils.readTLObject(stream, context, TLSecureFile.class);
        }
        if (this.hasSelfie()) {
            this.selfie = StreamingUtils.readTLObject(stream, context, TLSecureFile.class);
        }
        if (this.hasTranslation()) {
            this.translation = StreamingUtils.readTLVector(stream, context, TLSecureFile.class);
        }
        if (this.hasFiles()) {
            this.files = StreamingUtils.readTLVector(stream, context, TLSecureFile.class);
        }
        if (this.hasPlainData()) {
            this.plainData = StreamingUtils.readTLObject(stream, context, TLAbsSecurePlainData.class);
        }
        this.hash = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "secureValue#187fa0ca";
    }

}