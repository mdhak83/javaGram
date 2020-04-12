package org.javagram.api.message.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.peer.base.TLAbsPeer;

/**
 * Info about a forwarded message
 */
public class TLMessageFwdHeader extends TLObject {
    public static final int CLASS_ID = 0xec338270;

    private static final int FLAG_FROM_ID           = 0x00000001; // 0
    private static final int FLAG_CHANNEL_ID        = 0x00000002; // 1
    private static final int FLAG_CHANNEL_POST      = 0x00000004; // 2
    private static final int FLAG_POST_AUTHOR       = 0x00000008; // 3
    private static final int FLAG_SAVED_FROM_PEER   = 0x00000010; // 4
    private static final int FLAG_FROM_NAME         = 0x00000020; // 4

    private int fromId;
    private String fromName;
    private int date;
    private int channelId;
    private int channelPost;
    private String postAuthor;
    private TLAbsPeer savedFromPeer;
    private int savedFromMsgId;

    public TLMessageFwdHeader() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getChannelPost() {
        return channelPost;
    }

    public void setChannelPost(int channelPost) {
        this.channelPost = channelPost;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
    }

    public TLAbsPeer getSavedFromPeer() {
        return savedFromPeer;
    }

    public void setSavedFromPeer(TLAbsPeer savedFromPeer) {
        this.savedFromPeer = savedFromPeer;
    }

    public int getSavedFromMsgId() {
        return savedFromMsgId;
    }

    public void setSavedFromMsgId(int savedFromMsgId) {
        this.savedFromMsgId = savedFromMsgId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if ((this.flags & FLAG_FROM_ID) != 0) {
            StreamingUtils.writeInt(this.fromId, stream);
        }
        if ((this.flags & FLAG_FROM_NAME) != 0) {
            StreamingUtils.writeTLString(this.fromName, stream);
        }
        StreamingUtils.writeInt(this.date, stream);
        if ((this.flags & FLAG_CHANNEL_ID) != 0) {
            StreamingUtils.writeInt(this.channelId, stream);
        }
        if ((this.flags & FLAG_CHANNEL_POST) != 0) {
            StreamingUtils.writeInt(this.channelPost, stream);
        }
        if ((this.flags & FLAG_POST_AUTHOR) != 0) {
            StreamingUtils.writeTLString(this.postAuthor, stream);
        }
        if ((this.flags & FLAG_SAVED_FROM_PEER) != 0) {
            StreamingUtils.writeTLObject(this.savedFromPeer, stream);
            StreamingUtils.writeInt(this.savedFromMsgId, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_FROM_ID) != 0) {
            this.fromId = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_FROM_NAME) != 0) {
            this.fromName = StreamingUtils.readTLString(stream);
        }
        this.date = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_CHANNEL_ID) != 0) {
            this.channelId = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_CHANNEL_POST) != 0) {
            this.channelPost = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_POST_AUTHOR) != 0) {
            this.postAuthor = StreamingUtils.readTLString(stream);
        }
        if ((this.flags & FLAG_SAVED_FROM_PEER) != 0) {
            this.savedFromPeer = StreamingUtils.readTLObject(stream, context, TLAbsPeer.class);
            this.savedFromMsgId = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "messageFwdHeader#ec338270";
    }

}
