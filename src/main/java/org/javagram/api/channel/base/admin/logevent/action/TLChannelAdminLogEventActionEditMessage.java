package org.javagram.api.channel.base.admin.logevent.action;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.message.base.TLAbsMessage;
import org.javagram.utils.StreamingUtils;

/**
 * A message was edited
 * channelAdminLogEventActionEditMessage#709b2405 prev_message:Message new_message:Message = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionEditMessage extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x709b2405;

    /**
     * Old message
     */
    private TLAbsMessage prevMessage;

    /**
     * New message
     */
    private TLAbsMessage newMessage;

    public TLChannelAdminLogEventActionEditMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsMessage getPrevMessage() {
        return prevMessage;
    }

    public void setPrevMessage(TLAbsMessage prevMessage) {
        this.prevMessage = prevMessage;
    }

    public TLAbsMessage getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(TLAbsMessage newMessage) {
        this.newMessage = newMessage;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.prevMessage, stream);
        StreamingUtils.writeTLObject(this.newMessage, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.prevMessage = StreamingUtils.readTLObject(stream, context, TLAbsMessage.class);
        this.newMessage = StreamingUtils.readTLObject(stream, context, TLAbsMessage.class);
    }

    @Override
    public String toString() {
        return "channelAdminLogEventActionEditMessage#709b2405";
    }

}