package org.javagram.api.channel.base.admin.logevent.action;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.StreamingUtils;

/**
 * The linked chat was changed
 * channelAdminLogEventActionChangeLinkedChat#a26f881b prev_value:int new_value:int = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionChangeLinkedChat extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa26f881b;

    /**
     * Previous linked chat
     */
    private int prevValue;

    /**
     * New linked chat
     */
    private int newValue;

    public TLChannelAdminLogEventActionChangeLinkedChat() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getPrevValue() {
        return prevValue;
    }

    public void setPrevValue(int prevValue) {
        this.prevValue = prevValue;
    }

    public int getNewValue() {
        return newValue;
    }

    public void setNewValue(int newValue) {
        this.newValue = newValue;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.prevValue, stream);
        StreamingUtils.writeInt(this.newValue, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.prevValue = StreamingUtils.readInt(stream);
        this.newValue = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "channelAdminLogEventActionChangeLinkedChat#a26f881b";
    }

}