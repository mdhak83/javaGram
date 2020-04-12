package org.telegram.api.channel.base.admin.logevent.action;

import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.message.base.TLAbsMessage;
import org.telegram.utils.StreamingUtils;

/**
 * A message was pinned
 * channelAdminLogEventActionUpdatePinned#e9e82c18 message:Message = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionUpdatePinned extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe9e82c18;

    /**
     * The message that was pinned
     */
    private TLAbsMessage message;

    public TLChannelAdminLogEventActionUpdatePinned() {
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
        return "channelAdminLogEventActionUpdatePinned#e9e82c18";
    }

}