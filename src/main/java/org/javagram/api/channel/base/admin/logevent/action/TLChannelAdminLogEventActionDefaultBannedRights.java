package org.javagram.api.channel.base.admin.logevent.action;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.chat.base.TLChatBannedRights;
import org.javagram.utils.StreamingUtils;

/**
 * The default banned rights were modified
 * channelAdminLogEventActionDefaultBannedRights#2df5fc0a prev_banned_rights:ChatBannedRights new_banned_rights:ChatBannedRights = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionDefaultBannedRights extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2df5fc0a;

    /**
     * Previous global @see <a href="https://core.telegram.org/api/rights">banned rights</a>
     */
    private TLChatBannedRights prevBannedRights;

    /**
     * New global @see <a href="https://core.telegram.org/api/rights">banned rights</a>
     */
    private TLChatBannedRights newBannedRights;

    public TLChannelAdminLogEventActionDefaultBannedRights() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLChatBannedRights getPrevBannedRights() {
        return prevBannedRights;
    }

    public void setPrevBannedRights(TLChatBannedRights prevBannedRights) {
        this.prevBannedRights = prevBannedRights;
    }

    public TLChatBannedRights getNewBannedRights() {
        return newBannedRights;
    }

    public void setNewBannedRights(TLChatBannedRights newBannedRights) {
        this.newBannedRights = newBannedRights;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.prevBannedRights, stream);
        StreamingUtils.writeTLObject(this.newBannedRights, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.prevBannedRights = StreamingUtils.readTLObject(stream, context, TLChatBannedRights.class);
        this.newBannedRights = StreamingUtils.readTLObject(stream, context, TLChatBannedRights.class);
    }

    @Override
    public String toString() {
        return "channelAdminLogEventActionDefaultBannedRights#2df5fc0a";
    }

}