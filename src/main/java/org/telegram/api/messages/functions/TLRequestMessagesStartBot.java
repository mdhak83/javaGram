package org.telegram.api.messages.functions;

import org.telegram.api.peer.base.input.TLAbsInputPeer;
import org.telegram.api.user.base.input.TLAbsInputUser;
import org.telegram.api.updates.base.TLAbsUpdates;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Start a conversation with a bot using a @see <a href="https://core.telegram.org/bots#deep-linking">deep linking parameter</a>
 * messages.startBot#e6df7378 bot:InputUser peer:InputPeer random_id:long start_param:string = Updates;
 */
public class TLRequestMessagesStartBot extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe6df7378;

    /**
     * The bot
     */
    private TLAbsInputUser bot;

    /**
     * The chat where to start the bot, can be the bot's private chat or a group
     */
    private TLAbsInputPeer peer;

    /**
     * Random ID to avoid resending the same message
     */
    private long randomId;

    /**
     * <a href="https://core.telegram.org/bots#deep-linking">Deep linking parameter</a>
     */
    private String startParam;

    public TLRequestMessagesStartBot() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputUser getBot() {
        return bot;
    }

    public void setBot(TLAbsInputUser bot) {
        this.bot = bot;
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public long getRandomId() {
        return randomId;
    }

    public void setRandomId(long randomId) {
        this.randomId = randomId;
    }

    public String getStartParam() {
        return startParam;
    }

    public void setStartParam(String startParam) {
        this.startParam = startParam;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.bot, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeLong(this.randomId, stream);
        StreamingUtils.writeTLString(this.startParam, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.bot = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.randomId = StreamingUtils.readLong(stream);
        this.startParam = StreamingUtils.readTLString(stream);
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "bot.startBot#e6df7378";
    }

}