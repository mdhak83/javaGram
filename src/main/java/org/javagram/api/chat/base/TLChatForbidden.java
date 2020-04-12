package org.javagram.api.chat.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * chatForbidden
 * A group to which the user has no access. E.g., because the user was kicked from the group.
 * chatForbidden#7328bdb id:int title:string = Chat;
 */
public class TLChatForbidden extends TLAbsChat {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7328bdb;

    /**
     * Group name
     */
    private String title;

    public TLChatForbidden() {
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

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeTLString(this.title, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readInt(stream);
        this.title = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "chatForbidden#7328bdb";
    }

    @Override
    public String toLog() {
        return ("ChatForbidden: " + this.title + " (id=" + String.format("%08x", this.id) + ")");
    }
    
}