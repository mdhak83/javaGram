package org.telegram.api.chat.base.invite;

import org.telegram.api.photo.base.TLAbsPhoto;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Chat invite info
 * chatInvite#dfc2f58e flags:# channel:flags.0?true broadcast:flags.1?true public:flags.2?true megagroup:flags.3?true title:string photo:Photo participants_count:int participants:flags.4?Vector&lt;User&gt; = ChatInvite;
 */
public class TLChatInvite extends TLAbsChatInvite {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xdfc2f58e;

    private static final int FLAG_CHANNEL      = 0x00000001; // 0
    private static final int FLAG_BROADCAST    = 0x00000002; // 1
    private static final int FLAG_PUBLIC       = 0x00000004; // 2
    private static final int FLAG_MEGAGROUP    = 0x00000008; // 3
    private static final int FLAG_PARTICIPANTS = 0x00000010; // 4

    private int flags;
    private String title; ///< Title of the chat
    private TLAbsPhoto photo;
    private int participantsCount;
    private TLVector<TLAbsUser> participants;

    public TLChatInvite() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getTitle() {
        return title;
    }

    public TLAbsPhoto getPhoto() {
        return photo;
    }

    public int getParticipantsCount() {
        return participantsCount;
    }

    public TLVector<TLAbsUser> getParticipants() {
        return participants;
    }

    public boolean isChannel() {
        return (flags & FLAG_CHANNEL) != 0;
    }

    public boolean isBroadcast() {
        return (flags & FLAG_BROADCAST) != 0;
    }

    public boolean isPublic() {
        return (flags & FLAG_PUBLIC) != 0;
    }

    public boolean isMegagroup() {
        return (flags & FLAG_MEGAGROUP) != 0;
    }

    public boolean hasParticipants() {
        return (flags & FLAG_PARTICIPANTS) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeTLObject(this.photo, stream);
        StreamingUtils.writeInt(this.participantsCount, stream);
        if ((this.flags & FLAG_PARTICIPANTS) != 0) {
            StreamingUtils.writeTLVector(this.participants, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.title = StreamingUtils.readTLString(stream);
        this.photo = StreamingUtils.readTLObject(stream, context, TLAbsPhoto.class);
        this.participantsCount = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_PARTICIPANTS) != 0) {
            this.participants = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
        }
    }

    @Override
    public String toString() {
        return "chatInvite#dfc2f58e";
    }

}
