package org.telegram.api.file.base.input;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Preuploaded @see <a href="https://core.telegram.org/passport">passport</a> file, for more info @see <a href="https://core.telegram.org/passport/encryption#inputsecurefile">see the passport docs »</a>
 * inputSecureFile#5367e5be id:long access_hash:long = InputSecureFile;
 */
public class TLInputSecureFile extends TLAbsInputSecureFile {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5367e5be;

    /**
     * Secure file ID
     */
    private long id;
    
    /**
     * Secure file access hash
     */
    private long accessHash;
    

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
        return "inputSecureFile#5367e5be";
    }

}