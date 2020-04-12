package org.telegram.api.channel.base.admin.logevent.action;

import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A user has joined the group (in the case of big groups, info of the user that has joined isn't shown)
 * channelAdminLogEventActionParticipantJoin#183040d3 = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionParticipantJoin extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x183040d3;

    public TLChannelAdminLogEventActionParticipantJoin() {
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
        return "channelAdminLogEventActionParticipantJoin#183040d3";
    }

}