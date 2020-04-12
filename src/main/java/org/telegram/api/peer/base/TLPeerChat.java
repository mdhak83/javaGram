package org.telegram.api.peer.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.chat.base.TLAbsChat;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.client.handlers.AbstractUpdatesHandler;

/**
 * Group.
 * peerChat#bad0e5bb chat_id:int = Peer;
 */
public class TLPeerChat extends TLAbsPeer {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbad0e5bb;
    
    /**
     * Group identifier
     */
    private int chatId;

    public TLPeerChat() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.chatId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.chatId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "peerChat#bad0e5bb";
    }

    @Override
    public String toLog() {
        return this.chatId + "";
    }

}