package org.javagram.api.file.base.input.location;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Location of encrypted secret chat file.
 * inputEncryptedFileLocation#f5235d55 id:long access_hash:long = InputFileLocation;
 */
public class TLInputEncryptedFileLocation extends TLAbsInputFileLocation {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf5235d55;

    /**
     * File ID, <strong>id</strong> parameter value from @see <a href="https://core.telegram.org/constructor/encryptedFile">encryptedFile</a>
     */
    private long id;
    
    /**
     * Checksum, <strong>access_hash</strong> parameter value from @see <a href="https://core.telegram.org/constructor/encryptedFile">encryptedFile</a>
     */
    private long accessHash;

    public TLInputEncryptedFileLocation() {
        super();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccessHash() {
        return this.accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.accessHash = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "inputEncryptedFileLocation#f5235d55";
    }

}