package org.telegram.api.upload.base.file;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.file.base.TLFileHash;
import org.telegram.api._primitives.TLVector;

/**
 * The file must be downloaded from a @see <a href="https://core.telegram.org/cdn">CDN DC</a>.
 * upload.fileCdnRedirect#f18cda44 dc_id:int file_token:bytes encryption_key:bytes encryption_iv:bytes file_hashes:Vector&lt;FileHash&gt; = upload.File;
 */
public class TLFileCdnRedirect extends TLAbsFile {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf18cda44;

    /**
     * @see <a href="https://core.telegram.org/cdn">CDN DC</a> ID
     */
    private int dcId;
    
    /**
     * File token (@see <a href="https://core.telegram.org/cdn">CDN files</a>)
     */
    private TLBytes fileToken;
    
    /**
     * Encryption key (@see <a href="https://core.telegram.org/cdn">CDN files</a>)
     */
    private TLBytes encryptionKey;
    
    /**
     * Encryption IV (@see <a href="https://core.telegram.org/cdn">CDN files</a>)
     */
    private TLBytes encryptionIv;
    
    /**
     * File hashes (@see <a href="https://core.telegram.org/cdn">CDN files</a>)
     */
    private TLVector<TLFileHash> fileHashes;

    public TLFileCdnRedirect() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getDcId() {
        return dcId;
    }

    public void setDcId(int dcId) {
        this.dcId = dcId;
    }

    public TLBytes getFileToken() {
        return fileToken;
    }

    public void setFileToken(TLBytes fileToken) {
        this.fileToken = fileToken;
    }

    public TLBytes getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(TLBytes encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public TLBytes getEncryptionIv() {
        return encryptionIv;
    }

    public void setEncryptionIv(TLBytes encryptionIv) {
        this.encryptionIv = encryptionIv;
    }

    public TLVector<TLFileHash> getFileHashes() {
        return fileHashes;
    }

    public void setFileHashes(TLVector<TLFileHash> fileHashes) {
        this.fileHashes = fileHashes;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.dcId, stream);
        StreamingUtils.writeTLBytes(this.fileToken, stream);
        StreamingUtils.writeTLBytes(this.encryptionKey, stream);
        StreamingUtils.writeTLBytes(this.encryptionIv, stream);
        StreamingUtils.writeTLVector(this.fileHashes, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.dcId = StreamingUtils.readInt(stream);
        this.fileToken = StreamingUtils.readTLBytes(stream, context);
        this.encryptionKey = StreamingUtils.readTLBytes(stream, context);
        this.encryptionIv = StreamingUtils.readTLBytes(stream, context);
        this.fileHashes = StreamingUtils.readTLVector(stream, context, TLFileHash.class);
    }

    @Override
    public String toString() {
        return "upload.fileCdnRedirect#f18cda44";
    }

}