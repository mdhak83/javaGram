package org.telegram.api.message.base.encrypted;

import org.telegram.api.file.base.encrypted.TLAbsEncryptedFile;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL encrypted message.
 */
public class TLEncryptedMessage extends TLAbsEncryptedMessage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xed18c118;

    public TLEncryptedMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.randomId, stream);
        StreamingUtils.writeInt(this.chatId, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLBytes(this.bytes, stream);
        StreamingUtils.writeTLObject(this.file, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.randomId = StreamingUtils.readLong(stream);
        this.chatId = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
        this.bytes = StreamingUtils.readTLBytes(stream, context);
        this.file = StreamingUtils.readTLObject(stream, context, TLAbsEncryptedFile.class);
    }

    @Override
    public String toString() {
        return "encryptedMessage#ed18c118";
    }

}