package org.telegram.api.message.base.action;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Indicates the channel was @see <a href="https://core.telegram.org/api/channel">migrated</a> from the specified chat
 * messageActionChannelMigrateFrom#b055eaee title:string chat_id:int = MessageAction;
 */
public class TLMessageActionChannelMigratedFrom extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb055eaee;

    /**
     * The old chat title
     */
    private String title;
    
    /**
     * The old chat ID
     */
    private int chatId;

    public TLMessageActionChannelMigratedFrom() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeInt(this.chatId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.title = StreamingUtils.readTLString(stream);
        this.chatId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messageActionChannelMigratedFrom#b055eaee";
    }
    
}