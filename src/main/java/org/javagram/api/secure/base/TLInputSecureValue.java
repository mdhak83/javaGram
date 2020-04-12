package org.javagram.api.secure.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.file.base.input.TLAbsInputSecureFile;
import org.javagram.api.secure.base.plain.TLAbsSecurePlainData;
import org.javagram.api.secure.base.valuetype.TLAbsSecureValueType;
import org.javagram.api._primitives.TLVector;

/**
 * Secure value, @see <a href="https://core.telegram.org/passport/encryption#encryption">for more info see the passport docs »</a>
 * inputSecureValue#db21d0a7 flags:# type:SecureValueType data:flags.0?SecureData front_side:flags.1?InputSecureFile reverse_side:flags.2?InputSecureFile selfie:flags.3?InputSecureFile translation:flags.6?Vector&lt;InputSecureFile&gt; files:flags.4?Vector&lt;InputSecureFile&gt; plain_data:flags.5?SecurePlainData = InputSecureValue;
 */
public class TLInputSecureValue extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xdb21d0a7;

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
    private TLAbsInputSecureFile frontSide;
    
    /**
     * Encrypted @see <a href="https://core.telegram.org/passport">passport</a> file with the reverse side of the document
     */
    private TLAbsInputSecureFile reverseSide;
    
    /**
     * Encrypted @see <a href="https://core.telegram.org/passport">passport</a> file with a selfie of the user holding the document
     */
    private TLAbsInputSecureFile selfie;
    
    /**
     * Array of encrypted @see <a href="https://core.telegram.org/passport">passport</a> files with translated versions of the provided documents
     */
    private TLVector<TLAbsInputSecureFile> translation;
    
    /**
     * Array of encrypted @see <a href="https://core.telegram.org/passport">passport</a> files with photos the of the documents
     */
    private TLVector<TLAbsInputSecureFile> files;
    
    /**
     * Plaintext verified @see <a href="https://core.telegram.org/passport">passport</a> data
     */
    private TLAbsSecurePlainData plainData;
    
    public TLInputSecureValue() {
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

    public TLAbsInputSecureFile getFrontSide() {
        return frontSide;
    }

    public void setFrontSide(TLAbsInputSecureFile frontSide) {
        this.frontSide = frontSide;
    }

    public TLAbsInputSecureFile getReverseSide() {
        return reverseSide;
    }

    public void setReverseSide(TLAbsInputSecureFile reverseSide) {
        this.reverseSide = reverseSide;
    }

    public TLAbsInputSecureFile getSelfie() {
        return selfie;
    }

    public void setSelfie(TLAbsInputSecureFile selfie) {
        this.selfie = selfie;
    }

    public TLVector<TLAbsInputSecureFile> getTranslation() {
        return translation;
    }

    public void setTranslation(TLVector<TLAbsInputSecureFile> translation) {
        this.translation = translation;
    }

    public TLVector<TLAbsInputSecureFile> getFiles() {
        return files;
    }

    public void setFiles(TLVector<TLAbsInputSecureFile> files) {
        this.files = files;
    }

    public TLAbsSecurePlainData getPlainData() {
        return plainData;
    }

    public void setPlainData(TLAbsSecurePlainData plainData) {
        this.plainData = plainData;
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
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.type = StreamingUtils.readTLObject(stream, context, TLAbsSecureValueType.class);
        if (this.hasData()) {
            this.data = StreamingUtils.readTLObject(stream, context, TLSecureData.class);
        }
        if (this.hasFrontSide()) {
            this.frontSide = StreamingUtils.readTLObject(stream, context, TLAbsInputSecureFile.class);
        }
        if (this.hasReverseSide()) {
            this.reverseSide = StreamingUtils.readTLObject(stream, context, TLAbsInputSecureFile.class);
        }
        if (this.hasSelfie()) {
            this.selfie = StreamingUtils.readTLObject(stream, context, TLAbsInputSecureFile.class);
        }
        if (this.hasTranslation()) {
            this.translation = StreamingUtils.readTLVector(stream, context, TLAbsInputSecureFile.class);
        }
        if (this.hasFiles()) {
            this.files = StreamingUtils.readTLVector(stream, context, TLAbsInputSecureFile.class);
        }
        if (this.hasPlainData()) {
            this.plainData = StreamingUtils.readTLObject(stream, context, TLAbsSecurePlainData.class);
        }
    }

    @Override
    public String toString() {
        return "inputSecureValue#db21d0a7";
    }

}