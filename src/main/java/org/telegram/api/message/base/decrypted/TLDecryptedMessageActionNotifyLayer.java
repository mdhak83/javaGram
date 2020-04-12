package org.telegram.api.message.base.decrypted;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLLayer;

/**
 * The type TL decrypted message action notify layer.
 */
public class TLDecryptedMessageActionNotifyLayer extends TLDecryptedMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf3048883;

    private int layer = TLLayer.VALUE;

    public TLDecryptedMessageActionNotifyLayer() {
        super();
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.layer, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.layer = StreamingUtils.readInt(stream);
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets layer.
     *
     * @return the layer
     */
    public int getLayer() {
        return this.layer;
    }

    /**
     * Sets layer.
     *
     * @param layer the layer
     */
    public void setLayer(int layer) {
        this.layer = layer;
    }

    @Override
    public String toString() {
        return "decryptedMessageActionNotifyLayer#f3048883";
    }

}