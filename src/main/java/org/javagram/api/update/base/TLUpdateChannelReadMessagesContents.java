package org.javagram.api.update.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLIntVector;

/**
 * The specified @see <a href="https://core.telegram.org/api/channel">channel/supergroup</a> messages were read
 * updateChannelReadMessagesContents#89893b45 channel_id:int messages:Vector&lt;int&gt; = Update;
 */
public class TLUpdateChannelReadMessagesContents extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x89893b45;

    /**
     * @see <a href="https://core.telegram.org/api/channel">Channel/supergroup</a> ID
     */
    private int channelID;
    
    /**
     * IDs of messages that were read
     */
    private TLIntVector messages;

    public TLUpdateChannelReadMessagesContents() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getChannelID() {
        return channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    public TLIntVector getMessages() {
        return messages;
    }

    public void setMessages(TLIntVector messages) {
        this.messages = messages;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.channelID, stream);
        StreamingUtils.writeTLVector(this.messages, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channelID = StreamingUtils.readInt(stream);
        this.messages = StreamingUtils.readTLIntVector(stream, context);
    }

    @Override
    public String toString() {
        return "updateChannelReadMessagesContents#89893b45";
    }

}