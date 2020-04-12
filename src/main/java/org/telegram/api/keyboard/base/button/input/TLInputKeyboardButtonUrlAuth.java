package org.telegram.api.keyboard.base.button.input;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.keyboard.base.button.TLAbsKeyboardButton;
import org.telegram.api.user.base.input.TLAbsInputUser;

/**
 * Button to request a user to authorize via URL using @see <a href="https://telegram.org/blog/privacy-discussions-web-bots#meet-seamless-web-bots">Seamless Telegram Login</a>.
 * inputKeyboardButtonUrlAuth#d02e7fd4 flags:# request_write_access:flags.0?true text:string fwd_text:flags.1?string url:string bot:InputUser = KeyboardButton;
 */
public class TLInputKeyboardButtonUrlAuth extends TLAbsKeyboardButton {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd02e7fd4;

    private static final int FLAG_REQUEST_WRITE_ACCESS  = 0x00000001; // 0 : Set this flag to request the permission for your bot to send messages to the user.
    private static final int FLAG_FWD_TEXT              = 0x00000002; // 1 : New text of the button in forwarded messages.

    /**
     * New text of the button in forwarded messages.
     */
    private String fwdText;
    
    /**
     * An HTTP URL to be opened with user authorization data added to the query string when the button is pressed.
     * If the user refuses to provide authorization data, the original URL without information about the user will be opened.
     * The data added is the same as described in @see <a href="https://core.telegram.org/widgets/login#receiving-authorization-data">Receiving authorization data</a>.
     * <strong>NOTE:</strong> Services must <strong>always</strong> check the hash of the received data to verify the authentication and the integrity of the data as described in @see <a href="https://core.telegram.org/widgets/login#checking-authorization">Checking authorization</a>.
     */
    private String url;
    
    /**
     * Username of a bot, which will be used for user authorization.
     * See @see <a href="https://core.telegram.org/widgets/login#setting-up-a-bot">Setting up a bot</a> for more details.
     * If not specified, the current bot's username will be assumed. The url's domain must be the same as the domain linked with the bot. See @see <a href="https://core.telegram.org/widgets/login#linking-your-domain-to-the-bot">Linking your domain to the bot</a> for more details.
     */
    private TLAbsInputUser bot;

    public TLInputKeyboardButtonUrlAuth() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isRequestWriteAccess() {
        return this.isFlagSet(FLAG_REQUEST_WRITE_ACCESS);
    }
    
    public void setRequestWriteAccess(boolean value) {
        this.setFlag(FLAG_REQUEST_WRITE_ACCESS, value);
    }
    
    public boolean hasFwdText() {
        return this.isFlagSet(FLAG_FWD_TEXT);
    }
    
    public String getFwdText() {
        return fwdText;
    }

    public void setFwdText(String fwdText) {
        this.fwdText = fwdText;
        this.setFlag(FLAG_FWD_TEXT, this.fwdText != null && !this.fwdText.trim().isEmpty());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TLAbsInputUser getBot() {
        return bot;
    }

    public void setBot(TLAbsInputUser bot) {
        this.bot = bot;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.text, stream);
        if (this.hasFwdText()) {
            StreamingUtils.writeTLString(this.fwdText, stream);
        }
        StreamingUtils.writeTLString(this.url, stream);
        StreamingUtils.writeTLObject(this.bot, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.text = StreamingUtils.readTLString(stream);
        if (this.hasFwdText()) {
            this.fwdText = StreamingUtils.readTLString(stream);
        }
        this.url = StreamingUtils.readTLString(stream);
        this.bot = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
    }

    @Override
    public String toString() {
        return "inputKeyboardButtonUrlAuth#d02e7fd4";
    }

}