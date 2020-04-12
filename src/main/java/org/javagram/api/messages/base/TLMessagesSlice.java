package org.javagram.api.messages.base;

import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.message.base.TLAbsMessage;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Incomplete list of messages and auxiliary data.
 * messages.messagesSlice#c8edce1e flags:# inexact:flags.1?true count:int next_rate:flags.0?int messages:Vector&lt;Message&gt; chats:Vector&lt;Chat&gt; users:Vector&lt;User&gt; = messages.Messages;
 */
public class TLMessagesSlice extends TLAbsMessages {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc8edce1e;

    private static final int FLAG_NEXT_RATE     = 0x00000001; // 0 : Rate to use in the offset_rate parameter in the next call to @see <a href="https://core.telegram.org/method/messages.searchGlobal">messages.searchGlobal</a>
    private static final int FLAG_INEXACT       = 0x00000002; // 1 : If set, indicates that the results may be inexact

    /**
     * Total number of messages in the list
     */
    private int count;

    /**
     * Rate to use in the offset_rate parameter in the next call to @see <a href="https://core.telegram.org/method/messages.searchGlobal">messages.searchGlobal</a>
     */
    private int nextRate;
    
    public TLMessagesSlice() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isInexact() {
        return this.isFlagSet(FLAG_INEXACT);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean hasNextRate() {
        return this.isFlagSet(FLAG_NEXT_RATE);
    }
    
    public int getNextRate() {
        return nextRate;
    }

    public void setNextRate(int nextRate) {
        this.nextRate = nextRate;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.count, stream);
        if (this.hasNextRate()) {
            StreamingUtils.writeInt(this.nextRate, stream);
        }
        StreamingUtils.writeTLVector(this.messages, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.count = StreamingUtils.readInt(stream);
        if (this.hasNextRate()) {
            this.nextRate = StreamingUtils.readInt(stream);
        }
        this.messages = StreamingUtils.readTLVector(stream, context, TLAbsMessage.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "messages.messagesSlice#c8edce1e";
    }

}