package org.javagram.api.file.base.input.location;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Location of encrypted telegram @see <a href="https://core.telegram.org/passport">passport</a> file.
 * inputSecureFileLocation#cbc7ee28 id:long access_hash:long = InputFileLocation;
 */
public class TLInputSecureFileLocation extends TLAbsInputFileLocation {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xcbc7ee28;

    /**
     * File ID, id parameter value from @see <a href="https://core.telegram.org/constructor/secureFile">secureFile</a>
     */
    private long id;
    
    /**
     * Checksum, access_hash parameter value from @see <a href="https://core.telegram.org/constructor/secureFile">secureFile</a>
     */
    private long accessHash;

    public TLInputSecureFileLocation() {
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
        return "inputSecureFileLocation#cbc7ee28";
    }

}