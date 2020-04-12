package org.javagram.api.channel.base.admin.logevent;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.channel.base.admin.logevent.action.TLAbsChannelAdminLogEventAction;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLObject;

/**
 * Admin log event
 * channelAdminLogEvent#3b5a3e40 id:long date:int user_id:int action:ChannelAdminLogEventAction = ChannelAdminLogEvent;
 */
public class TLChannelAdminLogEvent extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3b5a3e40;

    /**
     * Event ID
     */
    private long id;

    /**
     * Date
     */
    private int date;

    /**
     * User ID
     */
    private int userId;

    /**
     * Action
     */
    private TLAbsChannelAdminLogEventAction action;

    public TLChannelAdminLogEvent() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public TLAbsChannelAdminLogEventAction getAction() {
        return action;
    }

    public void setAction(TLAbsChannelAdminLogEventAction action) {
        this.action = action;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeTLObject(this.action, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.date = StreamingUtils.readInt(stream);
        this.userId = StreamingUtils.readInt(stream);
        this.action = StreamingUtils.readTLObject(stream, context, TLAbsChannelAdminLogEventAction.class);
    }

    @Override
    public String toString() {
        return "channelAdminLogEvent#3b5a3e40";
    }

}