package org.javagram.api.chat.base;

import org.javagram.api.chat.base.photo.TLAbsChatPhoto;
import org.javagram.api.channel.base.input.TLAbsInputChannel;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.chat.base.photo.TLChatPhoto;

/**
 * chat
 * Info about a group
 * chat#3bda1bde flags:# creator:flags.0?true kicked:flags.1?true left:flags.2?true deactivated:flags.5?true id:int title:string photo:ChatPhoto participants_count:int date:int version:int migrated_to:flags.6?InputChannel admin_rights:flags.14?ChatAdminRights default_banned_rights:flags.18?ChatBannedRights = Chat;
 */
public class TLChat extends TLAbsChat {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3bda1bde;

    private static final int FLAG_CREATOR               = 0x00000001; // 00 : Whether the current user is the creator of the group
    private static final int FLAG_KICKED                = 0x00000002; // 01 : Whether the current user was kicked from the group
    private static final int FLAG_LEFT                  = 0x00000004; // 02 : Whether the current user has left the group
    private static final int FLAG_DEACTIVATED           = 0x00000020; // 05 : Whether the group was @see <a href="https://core.telegram.org/api/channel">migrated</a>
    private static final int FLAG_MIGRATED_TO           = 0x00000040; // 06 : Means this chat was @see <a href="https://core.telegram.org/api/channel">upgraded</a> to a supergroup
    private static final int FLAG_ADMIN_RIGHTS          = 0x00004000; // 14 : @see <a href="https://core.telegram.org/api/rights">Admin rights</a> of the user in the group
    private static final int FLAG_DEFAULT_BANNED_RIGHTS = 0x00040000; // 18 : @see <a href="https://core.telegram.org/api/rights">Default banned rights</a> of all users in the group

    /**
     * Title
     */
    private String title;
    
    /**
     * Chat photo
     */
    private TLAbsChatPhoto photo;
    
    /**
     * Participant count
     */
    private int participantsCount;
    
    /**
     * Date of creation of the group
     */
    private int date;
    
    /**
     * Used in basic groups to reorder updates and make sure that all of them were received.
     */
    private int version;
    
    /**
     * Means this chat was @see <a href="https://core.telegram.org/api/channel">upgraded</a> to a supergroup
     */
    private TLAbsInputChannel migratedTo;
    
    /**
     * @see <a href="https://core.telegram.org/api/rights">Admin rights</a> of the user in the group
     */
    private TLChatAdminRights adminRights;
    
    /**
     * @see <a href="https://core.telegram.org/api/rights">Default banned rights</a> of all users in the group
     */
    private TLChatBannedRights defaultBannedRights;

    public TLChat() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public boolean isChannel() {
        return false;
    }

    public boolean isCreator() {
        return this.isFlagSet(FLAG_CREATOR);
    }
    
    public void setCreator(boolean value) {
        this.setFlag(FLAG_CREATOR, value);
    }

    public boolean isKicked() {
        return this.isFlagSet(FLAG_KICKED);
    }
    
    public void setKicked(boolean value) {
        this.setFlag(FLAG_KICKED, value);
    }

    public boolean isLeft() {
        return this.isFlagSet(FLAG_LEFT);
    }
    
    public void setLeft(boolean value) {
        this.setFlag(FLAG_LEFT, value);
    }

    public boolean isDeactivated() {
        return this.isFlagSet(FLAG_DEACTIVATED);
    }
    
    public void setDeactivated(boolean value) {
        this.setFlag(FLAG_DEACTIVATED, value);
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public TLChatPhoto getLogo() {
        TLChatPhoto ret = null;
        if (this.photo != null && this.photo instanceof TLChatPhoto) {
            ret = (TLChatPhoto) this.photo;
        }
        return ret;
    }
    
    public TLAbsChatPhoto getPhoto() {
        return this.photo;
    }

    public void setPhoto(TLAbsChatPhoto photo) {
        this.photo = photo;
    }

    public int getParticipantsCount() {
        return this.participantsCount;
    }

    public void setParticipantsCount(int participantsCount) {
        this.participantsCount = participantsCount;
    }

    public int getDate() {
        return this.date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean hasMigratedTo() {
        return this.isFlagSet(FLAG_MIGRATED_TO);
    }
    
    public TLAbsInputChannel getMigratedTo() {
        return migratedTo;
    }

    public void setMigratedTo(TLAbsInputChannel migratedTo) {
        this.migratedTo = migratedTo;
        this.setFlag(FLAG_MIGRATED_TO, this.migratedTo != null);
    }

    public boolean hasAdminRights() {
        return this.isFlagSet(FLAG_ADMIN_RIGHTS);
    }
    
    public TLChatAdminRights getAdminRights() {
        return adminRights;
    }

    public void setAdminRights(TLChatAdminRights adminRights) {
        this.adminRights = adminRights;
        this.setFlag(FLAG_ADMIN_RIGHTS, this.adminRights != null);
    }

    public boolean hasDefaultBannedRights() {
        return this.isFlagSet(FLAG_DEFAULT_BANNED_RIGHTS);
    }
    
    public TLChatBannedRights getDefaultBannedRights() {
        return defaultBannedRights;
    }

    public void setDefaultBannedRights(TLChatBannedRights defaultBannedRights) {
        this.defaultBannedRights = defaultBannedRights;
        this.setFlag(FLAG_DEFAULT_BANNED_RIGHTS, this.defaultBannedRights != null);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeTLObject(this.photo, stream);
        StreamingUtils.writeInt(this.participantsCount, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeInt(this.version, stream);
        if (this.hasMigratedTo()) {
            StreamingUtils.writeTLObject(this.migratedTo, stream);
        }
        if (this.hasAdminRights()) {
            StreamingUtils.writeTLObject(this.adminRights, stream);
        }
        if (this.hasDefaultBannedRights()) {
            StreamingUtils.writeTLObject(this.defaultBannedRights, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        this.title = StreamingUtils.readTLString(stream);
        this.photo = StreamingUtils.readTLObject(stream, context, TLAbsChatPhoto.class);
        this.participantsCount = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
        this.version = StreamingUtils.readInt(stream);
        if (this.hasMigratedTo()) {
            this.migratedTo = StreamingUtils.readTLObject(stream, context, TLAbsInputChannel.class);
        }
        if (this.hasAdminRights()) {
            this.adminRights = StreamingUtils.readTLObject(stream, context, TLChatAdminRights.class);
        }
        if (this.hasDefaultBannedRights()) {
            this.defaultBannedRights = StreamingUtils.readTLObject(stream, context, TLChatBannedRights.class);
        }
    }

    @Override
    public String toString() {
        return "chat#3bda1bde";
    }

    @Override
    public String toLog() {
        return ("Chat: " + this.title + " (id=" + this.id + ")");
    }
    
}