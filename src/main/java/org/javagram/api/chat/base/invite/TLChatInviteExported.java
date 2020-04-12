package org.javagram.api.chat.base.invite;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Exported chat invite
 * chatInviteExported#fc2e05bc link:string = ExportedChatInvite;
 */
public class TLChatInviteExported extends TLAbsExportedChatInvite {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfc2e05bc;

    /**
     * Chat invitation link
     */
    private String link;

    public TLChatInviteExported() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.link, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.link = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "chat.chatInviteExported#fc2e05bc";
    }

}
