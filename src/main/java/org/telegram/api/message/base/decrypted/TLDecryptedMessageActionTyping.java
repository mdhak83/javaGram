package org.telegram.api.message.base.decrypted;

import org.telegram.api.sendmessage.base.action.TLAbsSendMessageAction;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL decrypted message action typing.
 */
public class TLDecryptedMessageActionTyping extends TLDecryptedMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc4f40be;

    private TLAbsSendMessageAction action;

    public TLDecryptedMessageActionTyping() {
    }

    /**
     * Instantiates a new TL decrypted message action typing.
     *
     * @param action the action
     */
    public TLDecryptedMessageActionTyping(TLAbsSendMessageAction action) {
        this.action = action;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets action.
     *
     * @return the action
     */
    public TLAbsSendMessageAction getAction() {
        return this.action;
    }

    /**
     * Sets action.
     *
     * @param action the action
     */
    public void setAction(TLAbsSendMessageAction action) {
        this.action = action;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.action, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.action = StreamingUtils.readTLObject(stream, context, TLAbsSendMessageAction.class);
    }

    @Override
    public String toString() {
        return "decryptedMessageActionSetMessageTTL#c4f40be";
    }

}