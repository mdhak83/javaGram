package org.telegram.api.message.base.action;

import org.telegram.api.photo.base.TLAbsPhoto;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Group profile photo changed
 * messageActionChatEditPhoto#7fcb13a8 photo:Photo = MessageAction;
 */
public class TLMessageActionChatEditPhoto extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7fcb13a8;

    /**
     * New group profile photo
     */
    private TLAbsPhoto photo;

    public TLMessageActionChatEditPhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsPhoto getPhoto() {
        return this.photo;
    }

    public void setPhoto(TLAbsPhoto photo) {
        this.photo = photo;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.photo, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.photo = StreamingUtils.readTLObject(stream, context, TLAbsPhoto.class);
    }

    @Override
    public String toString() {
        return "messageActionChatEditPhoto#7fcb13a8";
    }
    
}