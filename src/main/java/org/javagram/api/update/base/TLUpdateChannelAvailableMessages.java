package org.javagram.api.update.base;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.StreamingUtils;

/**
 * The history of a @see <a href="https://core.telegram.org/api/channel">channel/supergroup</a> was hidden.
 * updateChannelAvailableMessages#70db6837 channel_id:int available_min_id:int = Update;
 */
public class TLUpdateChannelAvailableMessages extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x70db6837;

    /**
     * Channel/supergroup ID
     */
    private int channelID;

    /**
     * Identifier of a maximum unavailable message in a channel due to hidden history.
     */
    private int availableMinID;
    
    public TLUpdateChannelAvailableMessages() {
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

    public int getAvailableMinID() {
        return availableMinID;
    }

    public void setAvailableMinID(int availableMinID) {
        this.availableMinID = availableMinID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.channelID, stream);
        StreamingUtils.writeInt(this.availableMinID, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channelID = StreamingUtils.readInt(stream);
        this.availableMinID = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updateChannelAvailableMessages#70db6837";
    }

}