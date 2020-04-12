package org.javagram.api.keyboard.base.button;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Button to force a user to switch to inline mode Pressing the button will prompt the user to select one of their chats, open that chat and insert the bot‘s username and the specified inline query in the input field.
 * keyboardButtonSwitchInline#568a748 flags:# same_peer:flags.0?true text:string query:string = KeyboardButton;
 */
public class TLKeyboardButtonRequestSwitchInline extends TLAbsKeyboardButton {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x568a748;

    private static final int FLAG_SAME_PEER   = 0x00000001; // 0 : If set, pressing the button will insert the bot‘s username and the specified inline <code>query</code> in the current chat's input field.

    /**
     * The inline query to use
     */
    private String query;

    public TLKeyboardButtonRequestSwitchInline() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getQuery() {
        return query;
    }

    public boolean isSamePeer() {
        return this.isFlagSet(FLAG_SAME_PEER);
    }

    public void setSamePeer(boolean value) {
        this.setFlag(FLAG_SAME_PEER, value);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.text, stream);
        StreamingUtils.writeTLString(this.query, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.text = StreamingUtils.readTLString(stream);
        this.query = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "keyboardButtonSwitchInline#568a748";
    }

}