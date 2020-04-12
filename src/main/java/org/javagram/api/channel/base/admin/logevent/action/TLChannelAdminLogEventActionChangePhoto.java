package org.javagram.api.channel.base.admin.logevent.action;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.photo.base.TLAbsPhoto;
import org.javagram.utils.StreamingUtils;

/**
 * The channel/supergroup's picture was changed
 * channelAdminLogEventActionChangePhoto#434bd2af prev_photo:Photo new_photo:Photo = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionChangePhoto extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x434bd2af;

    /**
     * Previous picture
     */
    private TLAbsPhoto prevPhoto;

    /**
     * New picture
     */
    private TLAbsPhoto newPhoto;

    public TLChannelAdminLogEventActionChangePhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsPhoto getPrevPhoto() {
        return prevPhoto;
    }

    public void setPrevPhoto(TLAbsPhoto prevPhoto) {
        this.prevPhoto = prevPhoto;
    }

    public TLAbsPhoto getNewPhoto() {
        return newPhoto;
    }

    public void setNewPhoto(TLAbsPhoto newPhoto) {
        this.newPhoto = newPhoto;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.prevPhoto, stream);
        StreamingUtils.writeTLObject(this.newPhoto, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.prevPhoto = StreamingUtils.readTLObject(stream, context, TLAbsPhoto.class);
        this.newPhoto = StreamingUtils.readTLObject(stream, context, TLAbsPhoto.class);
    }

    @Override
    public String toString() {
        return "channelAdminLogEventActionChangePhoto#434bd2af";
    }

}