package org.telegram.api.message.base.decrypted;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL decrypted message layer.
 */
public class TLDecryptedMessageLayer extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1be31789;

    /**
     * The Random bytes.
     */
    public TLBytes randomBytes;
    /**
     * The Layer.
     */
    public int layer;
    /**
     * The In seq no.
     */
    public int inSeqNo;
    /**
     * The Out seq no.
     */
    public int outSeqNo;
    /**
     * The Message.
     */
    public TLAbsDecryptedMessage message;

    public TLDecryptedMessageLayer() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBytes(this.randomBytes, stream);
        StreamingUtils.writeInt(this.layer, stream);
        StreamingUtils.writeInt(this.inSeqNo, stream);
        StreamingUtils.writeInt(this.outSeqNo, stream);
        StreamingUtils.writeTLObject(this.message, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.randomBytes = StreamingUtils.readTLBytes(stream, context);
        this.layer = StreamingUtils.readInt(stream);
        this.inSeqNo = StreamingUtils.readInt(stream);
        this.outSeqNo = StreamingUtils.readInt(stream);
        this.message = StreamingUtils.readTLObject(stream, context, TLAbsDecryptedMessage.class);
    }

    @Override
    public String toString() {
        return "decryptedMessageLayer#1be31789";
    }

}