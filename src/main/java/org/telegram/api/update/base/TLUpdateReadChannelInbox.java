package org.telegram.api.update.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static org.telegram.api.message.base.media.TLMessageMediaPhoto.CLASS_ID;

/**
 * The type TL update channel new message
 */
public class TLUpdateReadChannelInbox extends TLAbsUpdate implements TLItfChannelUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x330b5424;

    private static final int FLAG_FOLDER_ID = 0x00000001; // 0

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;
    private int folderId;
    private int channelId;
    private int maxId;
    private int stillUnreadCount;
    private int pts;

    public TLUpdateReadChannelInbox() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    @Override
    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    public int getStillUnreadCount() {
        return stillUnreadCount;
    }

    public void setStillUnreadCount(int stillUnreadCount) {
        this.stillUnreadCount = stillUnreadCount;
    }

    @Override
    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if ((flags & FLAG_FOLDER_ID) != 0) {
            StreamingUtils.writeInt(this.folderId, stream);
        }
        StreamingUtils.writeInt(this.channelId, stream);
        StreamingUtils.writeInt(this.maxId, stream);
        StreamingUtils.writeInt(this.stillUnreadCount, stream);
        StreamingUtils.writeInt(this.pts, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_FOLDER_ID) != 0) {
            this.folderId = StreamingUtils.readInt(stream);
        }
        this.channelId = StreamingUtils.readInt(stream);
        this.maxId = StreamingUtils.readInt(stream);
        this.stillUnreadCount = StreamingUtils.readInt(stream);
        this.pts = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updateReadChannelInbox330b5424";
    }

}