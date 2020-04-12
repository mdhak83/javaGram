package org.javagram.api.channel.base.admin.logevent;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLObject;

/**
 * Filter only certain admin log events
 * channelAdminLogEventsFilter#ea107ae4 flags:# join:flags.0?true leave:flags.1?true invite:flags.2?true ban:flags.3?true unban:flags.4?true kick:flags.5?true unkick:flags.6?true promote:flags.7?true demote:flags.8?true info:flags.9?true settings:flags.10?true pinned:flags.11?true edit:flags.12?true delete:flags.13?true = ChannelAdminLogEventsFilter;
 */
public class TLChannelAdminLogEventsFilter extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xea107ae4;

    private static final int FLAG_JOIN      = 0x00000001; //  0 : @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionParticipantJoin">Join events</a>
    private static final int FLAG_LEAVE     = 0x00000002; //  1 : @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionParticipantLeave">Leave events</a> 
    private static final int FLAG_INVITE    = 0x00000004; //  2 : @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionParticipantInvite">Invite events</a>
    private static final int FLAG_BAN       = 0x00000008; //  3 : @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionParticipantToggleBan">Ban events</a>
    private static final int FLAG_UNBAN     = 0x00000010; //  4 : @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionParticipantToggleBan">Unban events</a>
    private static final int FLAG_KICK      = 0x00000020; //  5 : @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionParticipantToggleBan">Kick events</a>
    private static final int FLAG_UNKICK    = 0x00000040; //  6 : @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionParticipantToggleBan">Unkick events</a>
    private static final int FLAG_PROMOTE   = 0x00000080; //  7 : @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionParticipantToggleAdmin">Admin promotion events</a>
    private static final int FLAG_DEMOTE    = 0x00000100; //  8 : @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionParticipantToggleAdmin">Admin demotion events</a>
    private static final int FLAG_INFO      = 0x00000200; //  9 : Info change events (when @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionChangeAbout">about</a>, @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionChangeLinkedChat">linked chat</a>, @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionChangeLocation">location</a>, @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionChangePhoto">photo</a>, @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionChangeStickerSet">stickerset</a>, @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionChangeTitle">title</a> or @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionChangeUsername">username</a> data of a channel gets modified)
    private static final int FLAG_SETTINGS  = 0x00000400; // 10 : Settings change events (@see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionToggleInvites">invites</a>, @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionTogglePreHistoryHidden">hidden prehistory</a>, @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionToggleSignatures">signatures</a>, @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionDefaultBannedRights">default banned rights</a>)
    private static final int FLAG_PINNED    = 0x00000800; // 11 : @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionUpdatePinned">Message pin events</a>
    private static final int FLAG_EDIT      = 0x00001000; // 12 : @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionEditMessage">Message edit events</a>
    private static final int FLAG_DELETE    = 0x00002000; // 13 : @see <a href="https://core.telegram.org/constructor/channelAdminLogEventActionDeleteMessage">Message deletion events</a>

    public TLChannelAdminLogEventsFilter() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "channelAdminLogEventsFilter#ea107ae4";
    }

}