package org.javagram.api.chat.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLObject;

public class TLChatBannedRights extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9f120418;

    private static final int FLAG_VIEW_MESSAGES     = 0x00000001; // 0
    private static final int FLAG_SEND_MESSAGES     = 0x00000002; // 1
    private static final int FLAG_SEND_MEDIA        = 0x00000004; // 2
    private static final int FLAG_SEND_STICKERS     = 0x00000008; // 3
    private static final int FLAG_SEND_GIFS         = 0x00000010; // 4
    private static final int FLAG_SEND_GAMES        = 0x00000020; // 5
    private static final int FLAG_SEND_INLINE       = 0x00000040; // 6
    private static final int FLAG_EMBED_LINKS       = 0x00000080; // 7
    private static final int FLAG_SEND_POLLS        = 0x00000100; // 8
    private static final int FLAG_CHANGE_INFO       = 0x00000400; // 10
    private static final int FLAG_INVITE_USERS      = 0x00008000; // 15
    private static final int FLAG_PIN_MESSAGES      = 0x00020000; // 17

    private int untilDate;

    public TLChatBannedRights() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(int untilDate) {
        this.untilDate = untilDate;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.untilDate, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.untilDate = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "chatBannedRights#9f120418";
    }

}