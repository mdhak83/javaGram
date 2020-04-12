package org.javagram.api.file.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import org.javagram.utils.StreamingUtils;

/**
 * FileHash
 * SHA256 Hash of an uploaded file, to be checked for validity after download
 * fileHash#6242c773 offset:int limit:int hash:bytes = FileHash;
 */
public class TLFileHash extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6242c773;

    /**
     * Offset from where to start computing SHA-256 hash
     */
    private int offset;
    
    /**
     * Length
     */
    private int limit;
    
    /**
     * SHA-256 Hash of file chunk, to be checked for validity after download
     */
    private TLBytes hash;
    

    public TLFileHash() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.offset, stream);
        StreamingUtils.writeInt(this.limit, stream);
        StreamingUtils.writeTLBytes(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.offset = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
        this.hash = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "fileHash#6242c773";
    }

}