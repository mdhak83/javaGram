package org.telegram.api.channels.functions;

import org.telegram.api.messages.base.TLMessagesChatFull;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import org.telegram.api.messages.base.chats.TLAbsMessagesChats;

/**
 * Get all groups that can be used as @see <a href="https://telegram.org/blog/privacy-discussions-web-bots">discussion groups</a>
 * channels.getGroupsForDiscussion#f5dad378 = messages.Chats;
 */
public class TLRequestChannelsGetGroupsForDiscussion extends TLMethod<TLAbsMessagesChats> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf5dad378;

    public TLRequestChannelsGetGroupsForDiscussion() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAbsMessagesChats deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsMessagesChats) {
            return (TLAbsMessagesChats) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLMessagesChatFull.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "channels.getGroupsForDiscussion#f5dad378";
    }

}