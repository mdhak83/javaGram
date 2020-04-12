package org.javagram.api.message.base.action;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Custom action (most likely not supported by the current layer, an upgrade might be needed)
 * messageActionCustomAction#fae69f56 message:string = MessageAction;
 */
public class TLMessageActionCustomAction extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfae69f56;

    /**
     * Action message
     */
    private String message;

    public TLMessageActionCustomAction() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.message, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.message = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "messageActionCustomAction#fae69f56";
    }

}