package org.javagram.api.file.base.input;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;

/**
 * inputSecureFileUploaded
 * Uploaded secure file, for more info @see <a href="https://core.telegram.org/passport/encryption#inputsecurefile">see the passport docs »</a>
 * inputSecureFileUploaded#3334b0f0 id:long parts:int md5_checksum:string file_hash:bytes secret:bytes = InputSecureFile;
 */
public class TLInputSecureFileUploaded extends TLAbsInputSecureFile {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3334b0f0;

    /**
     * Secure file ID
     */
    private long id;
    
    /**
     * Secure file part count
     */
    private int parts;
    
    /**
     * MD5 hash of encrypted uploaded file, to be checked server-side
     */
    private String md5Checksum;
    
    /**
     * File hash
     */
    private TLBytes fileHash;
    
    /**
     * Secret
     */
    private TLBytes secret;
    

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getParts() {
        return parts;
    }

    public void setParts(int parts) {
        this.parts = parts;
    }

    public String getMd5Checksum() {
        return md5Checksum;
    }

    public void setMd5Checksum(String md5Checksum) {
        this.md5Checksum = md5Checksum;
    }

    public TLBytes getFileHash() {
        return fileHash;
    }

    public void setFileHash(TLBytes fileHash) {
        this.fileHash = fileHash;
    }

    public TLBytes getSecret() {
        return secret;
    }

    public void setSecret(TLBytes secret) {
        this.secret = secret;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeInt(this.parts, stream);
        StreamingUtils.writeTLString(this.md5Checksum, stream);
        StreamingUtils.writeTLBytes(this.fileHash, stream);
        StreamingUtils.writeTLBytes(this.secret, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.parts = StreamingUtils.readInt(stream);
        this.md5Checksum = StreamingUtils.readTLString(stream);
        this.fileHash = StreamingUtils.readTLBytes(stream, context);
        this.secret = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "inputSecureFileUploaded#3334b0f0";
    }

}