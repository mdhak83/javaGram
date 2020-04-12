package org.telegram.api.update.base;

import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;

/**
 * A message was pinned in a @see <a href="https://core.telegram.org/api/channel">legacy group</a>
 * updateChatPinnedMessage#e10db349 chat_id:int id:int version:int = Update;
 */
public class TLUpdateChatPinnedMessage extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe10db349;

    /**
     * @see <a href="https://core.telegram.org/api/channel">Legacy</a> group ID
     */
    private int chatId;

    /**
     * ID of pinned message
     */
    private int id;

    /**
     * Used to reorder updates in legacy groups
     */
    private int version;

    public TLUpdateChatPinnedMessage() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.chatId, stream);
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeInt(this.version, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.chatId = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        this.version = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updateChatPinnedMessage#e10db349";
    }

}