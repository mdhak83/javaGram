package org.telegram.api.channels.functions;

import org.telegram.api.updates.base.TLAbsUpdates;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import org.telegram.api.messages.base.TLMessagesInactiveChats;

/**
 * Get inactive channels and supergroups
 * channels.getInactiveChannels#11e831ee = messages.InactiveChats;
 */
public class TLRequestChannelsGetInactiveChannels extends TLMethod<TLMessagesInactiveChats> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x11e831ee;

    public TLRequestChannelsGetInactiveChannels() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLMessagesInactiveChats deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLMessagesInactiveChats) {
            return (TLMessagesInactiveChats) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "channels.getInactiveChannels#11e831ee";
    }

}