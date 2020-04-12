package org.javagram.api.messages.base;

import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.message.base.TLAbsMessage;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Channel messages
 * messages.channelMessages#99262e37 flags:# inexact:flags.1?true pts:int count:int messages:Vector&lt;Message&gt; chats:Vector&lt;Chat&gt; users:Vector&lt;User&gt; = messages.Messages;
 */
public class TLChannelMessages extends TLAbsMessages {

    /**
     * 
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x99262e37;

    private static final int FLAG_INEXACT   = 0x00000001; // 0
    
    /**
     * @see <a href="https://core.telegram.org/api/updates">Event count after generation</a>
     */
    private int pts;
    
    /**
     * Total number of results were found server-side (may not be all included here)
     */
    private int count;

    public TLChannelMessages() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    /**
     * 
     * @return true if returned results may be inexact
     */
    public boolean isInexact() {
        return this.isFlagSet(FLAG_INEXACT);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.count, stream);
        StreamingUtils.writeTLVector(this.messages, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.pts = StreamingUtils.readInt(stream);
        this.count = StreamingUtils.readInt(stream);
        this.messages = StreamingUtils.readTLVector(stream, context, TLAbsMessage.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "messages.channelMessages#99262e37";
    }

}