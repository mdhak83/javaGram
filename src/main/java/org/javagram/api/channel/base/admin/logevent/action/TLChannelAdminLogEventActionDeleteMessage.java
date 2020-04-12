package org.javagram.api.channel.base.admin.logevent.action;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.message.base.TLAbsMessage;
import org.javagram.utils.StreamingUtils;

/**
 * A message was deleted
 * channelAdminLogEventActionDeleteMessage#42e047bb message:Message = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionDeleteMessage extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x42e047bb;

    /**
     * The message that was deleted
     */
    private TLAbsMessage message;

    public TLChannelAdminLogEventActionDeleteMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsMessage getMessage() {
        return message;
    }

    public void setMessage(TLAbsMessage message) {
        this.message = message;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.message, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.message = StreamingUtils.readTLObject(stream, context, TLAbsMessage.class);
    }

    @Override
    public String toString() {
        return "channelAdminLogEventActionDeleteMessage#42e047bb";
    }

}