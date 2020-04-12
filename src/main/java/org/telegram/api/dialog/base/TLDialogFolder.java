package org.telegram.api.dialog.base;

import org.telegram.api.peer.base.TLAbsPeer;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.TLFolder;

/**
 * Dialog in folder
 * dialogFolder#71bd134c flags:# pinned:flags.2?true folder:Folder peer:Peer top_message:int unread_muted_peers_count:int unread_unmuted_peers_count:int unread_muted_messages_count:int unread_unmuted_messages_count:int = Dialog;
 */
public class TLDialogFolder extends TLAbsDialog {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x71bd134c;

    /**
     * Folder
     */
    private TLFolder folder;
    
    /**
     * Peer in folder
     */
    private TLAbsPeer peer;
    
    /**
     * Latest message ID of dialog
     */
    private int topMessage;
    
    /**
     * Number of unread muted peers in folder
     */
    private int unreadMutedPeersCount;
    
    /**
     * Number of unread unmuted peers in folder
     */
    private int unreadUnmutedPeersCount;
    
    /**
     * Number of unread messages from muted peers in folder
     */
    private int unreadMutedMessagesCount;
    
    /**
     * Number of unread messages from unmuted peers in folder
     */
    private int unreadUnmutedMessagesCount;

    public TLDialogFolder() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLFolder getFolder() {
        return folder;
    }

    public void setFolder(TLFolder folder) {
        this.folder = folder;
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

    public int getUnreadMutedPeersCount() {
        return unreadMutedPeersCount;
    }

    public void setUnreadMutedPeersCount(int unreadMutedPeersCount) {
        this.unreadMutedPeersCount = unreadMutedPeersCount;
    }

    public int getUnreadUnmutedPeersCount() {
        return unreadUnmutedPeersCount;
    }

    public void setUnreadUnmutedPeersCount(int unreadUnmutedPeersCount) {
        this.unreadUnmutedPeersCount = unreadUnmutedPeersCount;
    }

    public int getUnreadMutedMessagesCount() {
        return unreadMutedMessagesCount;
    }

    public void setUnreadMutedMessagesCount(int unreadMutedMessagesCount) {
        this.unreadMutedMessagesCount = unreadMutedMessagesCount;
    }

    public int getUnreadUnmutedMessagesCount() {
        return unreadUnmutedMessagesCount;
    }

    public void setUnreadUnmutedMessagesCount(int unreadUnmutedMessagesCount) {
        this.unreadUnmutedMessagesCount = unreadUnmutedMessagesCount;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.folder, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.topMessage, stream);
        StreamingUtils.writeInt(this.unreadMutedPeersCount, stream);
        StreamingUtils.writeInt(this.unreadUnmutedPeersCount, stream);
        StreamingUtils.writeInt(this.unreadMutedMessagesCount, stream);
        StreamingUtils.writeInt(this.unreadUnmutedMessagesCount, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsPeer.class);
        this.topMessage = StreamingUtils.readInt(stream);
        this.unreadMutedPeersCount = StreamingUtils.readInt(stream);
        this.unreadUnmutedPeersCount = StreamingUtils.readInt(stream);
        this.unreadMutedMessagesCount = StreamingUtils.readInt(stream);
        this.unreadUnmutedMessagesCount = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "dialogFolder#71bd134c";
    }

}