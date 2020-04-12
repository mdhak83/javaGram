package org.telegram.api.messages.base.sentencrypted;

import org.telegram.api.file.base.encrypted.TLAbsEncryptedFile;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL sent encrypted file.
 */
public class TLSentEncryptedFile extends TLAbsSentEncryptedMessage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9493ff32;

    public TLSentEncryptedFile() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLObject(this.file, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.date = StreamingUtils.readInt(stream);
        this.file = StreamingUtils.readTLObject(stream, context, TLAbsEncryptedFile.class);
    }

    @Override
    public String toString() {
        return "messages.sentEncryptedFile#9493ff32";
    }

}