package org.javagram.api;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.chat.base.photo.TLAbsChatPhoto;

/**
 * Folder
 * folder#ff544e65 flags:# autofill_new_broadcasts:flags.0?true autofill_public_groups:flags.1?true autofill_new_correspondents:flags.2?true id:int title:string photo:flags.3?ChatPhoto = Folder;
 */
public class TLFolder extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xff544e65;

    private static final int FLAG_AUTOFILL_NEW_BROADCASTS       = 0x00000001; // 0 : Automatically add new channels to this folder
    private static final int FLAG_AUTOFILL_PUBLIC_GROUPS        = 0x00000002; // 1 : Automatically add joined new public supergroups to this folder
    private static final int FLAG_AUTOFILL_NEW_CORRESPONDENTS   = 0x00000004; // 2 : Automatically add new private chats to this folder
    private static final int FLAG_PHOTO                         = 0x00000008; // 3 : Folder picture

    /**
     * Folder ID
     */
    private int id;
    
    /**
     * Folder title
     */
    private String title;
    
    /**
     * Folder picture
     */
    private TLAbsChatPhoto photo;
            
    
    public TLFolder() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isAutofillNewBroadcasts() {
        return this.isFlagSet(FLAG_AUTOFILL_NEW_BROADCASTS);
    }
    
    public void setAutofillNewBroadcasts(boolean value) {
        this.setFlag(FLAG_AUTOFILL_NEW_BROADCASTS, value);
    }
    
    public boolean isAutofillPublicGroups() {
        return this.isFlagSet(FLAG_AUTOFILL_PUBLIC_GROUPS);
    }
    
    public void setAutofillPublicGroups(boolean value) {
        this.setFlag(FLAG_AUTOFILL_PUBLIC_GROUPS, value);
    }
    
    public boolean isAutofillNewCorrespondents() {
        return this.isFlagSet(FLAG_AUTOFILL_NEW_CORRESPONDENTS);
    }
    
    public void setAutofillNewCorrespondents(boolean value) {
        this.setFlag(FLAG_AUTOFILL_NEW_CORRESPONDENTS, value);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean hasPhoto() {
        return this.isFlagSet(FLAG_PHOTO);
    }
    
    public TLAbsChatPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(TLAbsChatPhoto photo) {
        this.photo = photo;
        if (this.photo != null) {
            this.flags |= FLAG_PHOTO;
        } else {
            this.flags &= ~FLAG_PHOTO;
        }
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeTLString(this.title, stream);
        if (this.hasPhoto()) {
            StreamingUtils.writeTLObject(this.photo, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        this.title = StreamingUtils.readTLString(stream);
        if (this.hasPhoto()) {
            this.photo = StreamingUtils.readTLObject(stream, context, TLAbsChatPhoto.class);
        }
    }

    @Override
    public String toString() {
        return "folder#ff544e65";
    }

}