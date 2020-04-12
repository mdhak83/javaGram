package org.javagram.api.messages.base.sentencrypted;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL sent encrypted message.
 */
public class TLSentEncryptedMessage extends TLAbsSentEncryptedMessage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x560f8935;

    public TLSentEncryptedMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.date, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.date = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messages.sentEncryptedMessage#560f8935";
    }

}