package org.javagram.api.channel.base.admin.logevent.action;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A user left the channel/supergroup (in the case of big groups, info of the user that has joined isn't shown)
 * channelAdminLogEventActionParticipantLeave#f89777f2 = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionParticipantLeave extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf89777f2;

    public TLChannelAdminLogEventActionParticipantLeave() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
    }

    @Override
    public String toString() {
        return "channelAdminLogEventActionParticipantLeave#f89777f2";
    }

}