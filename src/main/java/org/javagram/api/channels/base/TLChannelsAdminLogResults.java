package org.javagram.api.channels.base;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.channel.base.admin.logevent.TLChannelAdminLogEvent;
import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;

/**
 * Admin log events
 * channels.adminLogResults#ed8af74d events:Vector&lt;ChannelAdminLogEvent&gt; chats:Vector&lt;Chat&gt; users:Vector&lt;User&gt; = channels.AdminLogResults;
 */
public class TLChannelsAdminLogResults extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xed8af74d;

    /**
     * Admin log events
     */
    private TLVector<TLChannelAdminLogEvent> events;

    /**
     * Chats mentioned in events
     */
    private TLVector<TLAbsChat> chats;

    /**
     * Users mentioned in events
     */
    private TLVector<TLAbsUser> users;

    public TLChannelsAdminLogResults() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLChannelAdminLogEvent> getEvents() {
        return events;
    }

    public void setEvents(TLVector<TLChannelAdminLogEvent> events) {
        this.events = events;
    }

    public TLVector<TLAbsChat> getChats() {
        return chats;
    }

    public void setChats(TLVector<TLAbsChat> chats) {
        this.chats = chats;
    }

    public TLVector<TLAbsUser> getUsers() {
        return users;
    }

    public void setUsers(TLVector<TLAbsUser> users) {
        this.users = users;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.events, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.events = StreamingUtils.readTLVector(stream, context, TLChannelAdminLogEvent.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "channels.adminLogResults#ed8af74d";
    }

}