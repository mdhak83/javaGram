package org.javagram.api.message.base.action;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The channel was created
 * messageActionChannelCreate#95d2ac92 title:string = MessageAction;
 */
public class TLMessageActionChannelCreate extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x95d2ac92;

    /**
     * Original channel/supergroup title
     */
    private String title;

    public TLMessageActionChannelCreate() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.title, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.title = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "messageActionChannelCreate#95d2ac92";
    }

}