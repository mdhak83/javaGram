package org.javagram.api.message.base.action;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Group name changed.
 * messageActionChatEditTitle#b5a1ce5a title:string = MessageAction;
 */
public class TLMessageActionChatEditTitle extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb5a1ce5a;

    /**
     * New group name
     */
    private String title;

    public TLMessageActionChatEditTitle() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.title, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.title = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "messageActionChatEditTitle#b5a1ce5a";
    }
    
}