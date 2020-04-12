package org.javagram.api.contacts.functions;

import org.javagram.api.contacts.base.toppeers.TLAbsContactsTopPeers;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Get most used peers
 * contacts.getTopPeers#d4982db5 flags:# correspondents:flags.0?true bots_pm:flags.1?true bots_inline:flags.2?true phone_calls:flags.3?true forward_users:flags.4?true forward_chats:flags.5?true groups:flags.10?true channels:flags.15?true offset:int limit:int hash:int = contacts.TopPeers;
 */
public class TLRequestContactsGetTopPeers extends TLMethod<TLAbsContactsTopPeers> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd4982db5;

    private static final int FLAG_CORRESPONDENTS    = 0x00000001; // 0
    private static final int FLAG_BOTS_PM           = 0x00000002; // 1
    private static final int FLAG_BOTS_INLINE       = 0x00000004; // 2
    private static final int FLAG_PHONE_CALLS       = 0x00000008; // 3
    private static final int FLAG_FORWARD_USERS     = 0x00000010; // 4
    private static final int FLAG_FORWARD_CHATS     = 0x00000020; // 5
    private static final int FLAG_GROUPS            = 0x00000400; // 10
    private static final int FLAG_CHANNELS          = 0x00008000; // 15

    /**
     * Offset for @see <a href="https://core.telegram.org/api/offsets">pagination</a>
     */
    private int offset;
    
    /**
     * Maximum number of results to return, @see <a href="https://core.telegram.org/api/offsets">see pagination</a>
     */
    private int limit;
    
    /**
     * @see <a href="https://core.telegram.org/api/offsets#hash-generation">Hash for pagination, for more info click here</a>
     */
    private int hash;

    public TLRequestContactsGetTopPeers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean hasCorrespondents() {
        return this.isFlagSet(FLAG_CORRESPONDENTS);
    }

    public void enableCorrespondents(boolean value) {
        this.setFlag(FLAG_CORRESPONDENTS, value);
    }

    public boolean hasBotsPM() {
        return this.isFlagSet(FLAG_BOTS_PM);
    }

    public void enableBotsPM(boolean value) {
        this.setFlag(FLAG_BOTS_PM, value);
    }

    public boolean hasBotsInline() {
        return this.isFlagSet(FLAG_BOTS_INLINE);
    }

    public void enableBotsInline(boolean value) {
        this.setFlag(FLAG_BOTS_INLINE, value);
    }

    public boolean hasPhoneCalls() {
        return this.isFlagSet(FLAG_PHONE_CALLS);
    }

    public void enablePhoneCalls(boolean value) {
        this.setFlag(FLAG_PHONE_CALLS, value);
    }

    public boolean hasForwardUsers() {
        return this.isFlagSet(FLAG_FORWARD_USERS);
    }

    public void enableForwardUsers(boolean value) {
        this.setFlag(FLAG_FORWARD_USERS, value);
    }

    public boolean hasForwardChats() {
        return this.isFlagSet(FLAG_FORWARD_CHATS);
    }

    public void enableForwardChats(boolean value) {
        this.setFlag(FLAG_FORWARD_CHATS, value);
    }

    public boolean hasGroups() {
        return this.isFlagSet(FLAG_GROUPS);
    }

    public void enableGroups(boolean value) {
        this.setFlag(FLAG_GROUPS, value);
    }

    public boolean hasChannels() {
        return this.isFlagSet(FLAG_CHANNELS);
    }

    public void enableChannels(boolean value) {
        this.setFlag(FLAG_CHANNELS, value);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.offset, stream);
        StreamingUtils.writeInt(this.limit, stream);
        StreamingUtils.writeInt(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.offset = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
        this.hash = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsContactsTopPeers deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsContactsTopPeers) {
            return (TLAbsContactsTopPeers) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsContactsTopPeers.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "contacts.getTopPeers#d4982db5";
    }

}