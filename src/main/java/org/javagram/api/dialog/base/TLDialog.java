package org.javagram.api.dialog.base;

import org.javagram.api.message.base.draft.TLAbsDraftMessage;
import org.javagram.api.peer.base.TLAbsPeer;
import org.javagram.api.peer.base.notify.settings.TLAbsPeerNotifySettings;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Chat
 * dialog#2c171f72 flags:# pinned:flags.2?true unread_mark:flags.3?true peer:Peer top_message:int read_inbox_max_id:int read_outbox_max_id:int unread_count:int unread_mentions_count:int notify_settings:PeerNotifySettings pts:flags.0?int draft:flags.1?DraftMessage folder_id:flags.4?int = Dialog;
 */
public class TLDialog extends TLAbsDialog {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2c171f72;

    private static final int FLAG_PTS               = 0x00000001; // 0
    private static final int FLAG_DRAFT             = 0x00000002; // 1
    private static final int FLAG_UNREAD_MARK       = 0x00000008; // 3
    private static final int FLAG_FOLDER_ID         = 0x00000010; // 4

    /**
     * The chat
     */
    private TLAbsPeer peer;
    
    /**
     * The latest message ID
     */
    private int topMessage;
    
    /**
     * Position up to which all incoming messages are read.
     */
    private int readInboxMaxId;
    
    /**
     * Position up to which all outgoing messages are read.
     */
    private int readOutboxMaxId;
    
    /**
     * Number of unread messages
     */
    private int unreadCount;
    
    /**
     * Number of unread mentions
     */
    private int unreadMentionsCount;
    
    /**
     * Notification settings
     */
    private TLAbsPeerNotifySettings notifySettings;
    
    /**
     * @see <a href="https://core.telegram.org/api/updates">PTS</a>
     */
    private int pts;
    
    /**
     * Message draft
     */
    private TLAbsDraftMessage draft;
    
    /**
     * Folder ID
     */
    private int folderId;

    public TLDialog() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isUnreadMark() {
        return this.isFlagSet(FLAG_UNREAD_MARK);
    }
    
    public void setUnreadMark(boolean value) {
        this.setFlag(FLAG_UNREAD_MARK, value);
    }

    public TLAbsPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsPeer peer) {
        this.peer = peer;
    }

    public int getTopMessage() {
        return topMessage;
    }

    public void setTopMessage(int topMessage) {
        this.topMessage = topMessage;
    }

    public int getReadInboxMaxId() {
        return readInboxMaxId;
    }

    public void setReadInboxMaxId(int readInboxMaxId) {
        this.readInboxMaxId = readInboxMaxId;
    }

    public int getReadOutboxMaxId() {
        return readOutboxMaxId;
    }

    public void setReadOutboxMaxId(int readOutboxMaxId) {
        this.readOutboxMaxId = readOutboxMaxId;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }

    public int getUnreadMentionsCount() {
        return unreadMentionsCount;
    }

    public void setUnreadMentionsCount(int unreadMentionsCount) {
        this.unreadMentionsCount = unreadMentionsCount;
    }

    public TLAbsPeerNotifySettings getNotifySettings() {
        return notifySettings;
    }

    public void setNotifySettings(TLAbsPeerNotifySettings notifySettings) {
        this.notifySettings = notifySettings;
    }

    public boolean hasPts() {
        return this.isFlagSet(FLAG_PTS);
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
        this.setFlag(FLAG_PTS, true);
    }

    public boolean hasDraft() {
        return this.isFlagSet(FLAG_DRAFT);
    }

    public TLAbsDraftMessage getDraft() {
        return draft;
    }

    public void setDraft(TLAbsDraftMessage draft) {
        this.draft = draft;
        this.setFlag(FLAG_DRAFT, draft != null);
    }

    public boolean hasFolderId() {
        return this.isFlagSet(FLAG_FOLDER_ID);
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
        this.setFlag(FLAG_FOLDER_ID, true);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.topMessage, stream);
        StreamingUtils.writeInt(this.readInboxMaxId, stream);
        StreamingUtils.writeInt(this.readOutboxMaxId, stream);
        StreamingUtils.writeInt(this.unreadCount, stream);
        StreamingUtils.writeInt(this.unreadMentionsCount, stream);
        StreamingUtils.writeTLObject(this.notifySettings, stream);
        if (this.hasPts()) {
            StreamingUtils.writeInt(this.pts, stream);
        }
        if (this.hasDraft()) {
            StreamingUtils.writeTLObject(this.draft, stream);
        }
        if (this.hasFolderId()) {
            StreamingUtils.writeInt(this.folderId, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsPeer.class);
        this.topMessage = StreamingUtils.readInt(stream);
        this.readInboxMaxId = StreamingUtils.readInt(stream);
        this.readOutboxMaxId = StreamingUtils.readInt(stream);
        this.unreadCount = StreamingUtils.readInt(stream);
        this.unreadMentionsCount = StreamingUtils.readInt(stream);
        this.notifySettings = StreamingUtils.readTLObject(stream, context, TLAbsPeerNotifySettings.class);
        if (this.hasPts()) {
            this.pts = StreamingUtils.readInt(stream);
        }
        if (this.hasDraft()) {
            this.draft = StreamingUtils.readTLObject(stream, context, TLAbsDraftMessage.class);
        }
        if (this.hasFolderId()) {
            this.folderId = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "dialog#2c171f72";
    }

}