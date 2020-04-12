package org.javagram.api.file.base.input.encrypted;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input encrypted file.
 */
public class TLInputEncryptedFile extends TLAbsInputEncryptedFile {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5a17b5e5;

    public TLInputEncryptedFile() {
        super();
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
        return "inputEncryptedFile#5a17b5e5";
    }

}