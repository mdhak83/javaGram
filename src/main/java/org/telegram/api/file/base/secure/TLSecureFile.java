package org.telegram.api.file.base.secure;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLBytes;

/**
 * secureFile
 * Secure @see <a href="https://core.telegram.org/passport">passport</a> file, for more info @see <a href="https://core.telegram.org/passport/encryption#securedata">see the passport docs »</a>
 * secureFile#e0277a62 id:long access_hash:long size:int dc_id:int date:int file_hash:bytes secret:bytes = SecureFile;
 */
public class TLSecureFile extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe0277a62;

    /**
     * ID
     */
    private long id;
    
    /**
     * Access hash
     */
    private long accessHash;
    
    /**
     * File size
     */
    private int size;
    
    /**
     * DC ID
     */
    private int dcId;
    
    /**
     * Date of upload
     */
    private int date;
    
    /**
     * File hash
     */
    private TLBytes fileHash;
    
    /**
     * Secret
     */
    private TLBytes secret;
    
    public TLSecureFile() {
        super();
    }

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

    public long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDcId() {
        return dcId;
    }

    public void setDcId(int dcId) {
        this.dcId = dcId;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
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
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeInt(this.size, stream);
        StreamingUtils.writeInt(this.dcId, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLBytes(this.fileHash, stream);
        StreamingUtils.writeTLBytes(this.secret, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.size = StreamingUtils.readInt(stream);
        this.dcId = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
        this.fileHash = StreamingUtils.readTLBytes(stream, context);
        this.secret = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "secureFile#e0277a62";
    }

}