package org.javagram.api.account.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.account.base.TLAccountTakeout;

/**
 * Intialize account takeout session
 * account.initTakeoutSession#f05b4804 flags:# contacts:flags.0?true message_users:flags.1?true message_chats:flags.2?true message_megagroups:flags.3?true message_channels:flags.4?true files:flags.5?true file_max_size:flags.5?int = account.Takeout;
 */
public class TLRequestAccountInitTakeoutSession extends TLMethod<TLAccountTakeout> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf05b4804;

    private static final int FLAG_CONTACTS              = 0x00000001; // 0 : Whether to export contacts
    private static final int FLAG_MESSAGE_USERS         = 0x00000002; // 1 : Whether to export messages in private chats
    private static final int FLAG_MESSAGE_CHATS         = 0x00000004; // 2 : Whether to export messages in @see <a href="https://core.telegram.org/api/channel">legacy groups</a>
    private static final int FLAG_MESSAGE_MEGAGROUPS    = 0x00000008; // 3 : Whether to export messages in @see <a href="https://core.telegram.org/api/channel">supergroups</a>
    private static final int FLAG_MESSAGE_CHANNELS      = 0x00000010; // 4 : Whether to export messages in @see <a href="https://core.telegram.org/api/channel">channels</a>
    private static final int FLAG_FILES                 = 0x00000020; // 5 : Whether to export files

    /**
     * Maximum size of files to export
     */
    private int fileMaxSize;

    public TLRequestAccountInitTakeoutSession() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public boolean isContacts() {
        return this.isFlagSet(FLAG_CONTACTS);
    }

    public boolean isMessageUsers() {
        return this.isFlagSet(FLAG_MESSAGE_USERS);
    }

    public boolean isMessageChats() {
        return this.isFlagSet(FLAG_MESSAGE_CHATS);
    }

    public boolean isMessageMegagroups() {
        return this.isFlagSet(FLAG_MESSAGE_MEGAGROUPS);
    }

    public boolean isMessageChannels() {
        return this.isFlagSet(FLAG_MESSAGE_CHANNELS);
    }

    public boolean isFiles() {
        return this.isFlagSet(FLAG_FILES);
    }

    public int getFileMaxSize() {
        return fileMaxSize;
    }

    public void setFileMaxSize(int fileMaxSize) {
        this.fileMaxSize = fileMaxSize;
        this.setFlag(FLAG_FILES, this.fileMaxSize != 0);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if (this.isFiles()) {
            StreamingUtils.writeInt(this.fileMaxSize, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.isFiles()) {
            this.fileMaxSize = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public TLAccountTakeout deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAccountTakeout) {
            return (TLAccountTakeout) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.peer.notify.settings.TLAbsPeerNotifySettings, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.initTakeoutSession#f05b4804";
    }

}