package org.javagram.api.chat.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLObject;

public class TLChatAdminRights extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5fb224d5;

    private static final int FLAG_CHANGE_INFO               = 0x00000001; // 0
    private static final int FLAG_POST_MESSAGES             = 0x00000002; // 1
    private static final int FLAG_EDIT_MESSAGES             = 0x00000004; // 2
    private static final int FLAG_DELETE_MESSAGES           = 0x00000008; // 3
    private static final int FLAG_BAN_USERS                 = 0x00000010; // 4
    private static final int FLAG_INVITE_USERS              = 0x00000020; // 5
    private static final int FLAG_PIN_MESSAGES              = 0x00000080; // 7
    private static final int FLAG_ADD_ADMINS                = 0x00000200; // 9

    public TLChatAdminRights() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "chatAdminRights#5fb224d5";
    }

}