package org.javagram.api.update.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLIntVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.client.handlers.AbstractUpdatesHandler;

/**
 * The type TL update delete channel messages
 */
public class TLUpdateDeleteChannelMessages extends TLAbsUpdate implements TLItfChannelUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc37521c9;

    private int channelId;
    private TLIntVector messages;
    private int pts;
    private int ptsCount;

    public TLUpdateDeleteChannelMessages() {
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

    public TLIntVector getMessages() {
        return messages;
    }

    public void setMessages(TLIntVector messages) {
        this.messages = messages;
    }

    @Override
    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    @Override
    public int getPtsCount() {
        return ptsCount;
    }

    public void setPtsCount(int ptsCount) {
        this.ptsCount = ptsCount;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.channelId, stream);
        StreamingUtils.writeTLVector(this.messages, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channelId = StreamingUtils.readInt(stream);
        this.messages = StreamingUtils.readTLIntVector(stream, context);
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "update.TLUpdateDeleteChannelMessages#c37521c9";
    }

    public String toLog() {
        String ret = null;
        if (this.messages != null && !this.messages.isEmpty()) {
            String sChannel = this.channelId + "";
            String sIds = "";
            if (this.messages != null) {
                Iterator<Integer> it = this.messages.iterator();
                while (it.hasNext()) {
                    sIds += it.next();
                    if (it.hasNext()) {
                        sIds += ";";
                    }
                }
            }
            ret = "Channel: " + (sChannel != null ? sChannel : "---") + " - IDs : {" + sIds + "}";
        }
        return ret;
    }

}