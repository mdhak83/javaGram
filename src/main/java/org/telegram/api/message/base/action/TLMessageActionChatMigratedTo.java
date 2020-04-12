package org.telegram.api.message.base.action;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Indicates the chat was @see <a href="https://core.telegram.org/api/channel">migrated</a> to the specified supergroup
 * messageActionChatMigrateTo#51bdb021 channel_id:int = MessageAction;
 */
public class TLMessageActionChatMigratedTo extends TLAbsMessageAction {

    /**
     * 
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x51bdb021;

    /**
     * The supergroup it was migrated to
     */
    private int channelId;

    public TLMessageActionChatMigratedTo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.channelId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channelId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messageActionChatMigrateTo#51bdb021";
    }
    
}