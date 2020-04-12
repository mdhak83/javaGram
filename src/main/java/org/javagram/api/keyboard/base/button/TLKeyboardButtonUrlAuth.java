package org.javagram.api.keyboard.base.button;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Button to request a user to authorize via URL using @see <a href="https://telegram.org/blog/privacy-discussions-web-bots#meet-seamless-web-bots">Seamless Telegram Login</a>.
 * When the user clicks on such a button, @see <a href="https://core.telegram.org/method/messages.requestUrlAuth">messages.requestUrlAuth</a> should be called, providing the <code>button_id</code> and the ID of the container message.
 * The returned @see <a href="https://core.telegram.org/constructor/urlAuthResultRequest">urlAuthResultRequest</a> object will contain more details about the authorization request (<code>request_write_access</code> if the bot would like to send messages to the user along with the username of the bot which will be used for user authorization).
 * Finally, the user can choose to call @see <a href="https://core.telegram.org/method/messages.acceptUrlAuth">messages.acceptUrlAuth</a> to get a @see <a href="https://core.telegram.org/constructor/urlAuthResultAccepted">urlAuthResultAccepted</a> with the URL to open instead of the <code>url</code> of this constructor, or a @see <a href="https://core.telegram.org/constructor/urlAuthResultDefault">urlAuthResultDefault</a>, in which case the <code>url</code> of this constructor must be opened, instead.
 * If the user refuses the authorization request but still wants to open the link, the <code>url</code> of this constructor must be used.
 * keyboardButtonUrlAuth#10b78d29 flags:# text:string fwd_text:flags.0?string url:string button_id:int = KeyboardButton;
 */
public class TLKeyboardButtonUrlAuth extends TLAbsKeyboardButton {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x10b78d29;

    private static final int FLAG_FWD_TEXT  = 0x00000001; // 0 : New text of the button in forwarded messages.

    /**
     * New text of the button in forwarded messages.
     */
    private String fwdText;
    
    /**
     * An HTTP URL to be opened with user authorization data added to the query string when the button is pressed. If the user refuses to provide authorization data, the original URL without information about the user will be opened. The data added is the same as described in @see <a href="https://core.telegram.org/widgets/login#receiving-authorization-data">Receiving authorization data</a>.
     * <strong>NOTE:</strong> Services must <strong>always</strong> check the hash of the received data to verify the authentication and the integrity of the data as described in @see <a href="https://core.telegram.org/widgets/login#checking-authorization">Checking authorization</a>.
     */
    private String url;
    
    /**
     * ID of the button to pass to @see <a href="https://core.telegram.org/method/messages.requestUrlAuth">messages.requestUrlAuth</a>
     */
    private int buttonId;

    public TLKeyboardButtonUrlAuth() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
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

    public int getButtonId() {
        return buttonId;
    }

    public void setButtonId(int buttonId) {
        this.buttonId = buttonId;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.text, stream);
        if (this.hasFwdText()) {
            StreamingUtils.writeTLString(this.fwdText, stream);
        }
        StreamingUtils.writeTLString(this.url, stream);
        StreamingUtils.writeInt(this.buttonId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.text = StreamingUtils.readTLString(stream);
        if (this.hasFwdText()) {
            this.fwdText = StreamingUtils.readTLString(stream);
        }
        this.url = StreamingUtils.readTLString(stream);
        this.buttonId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "keyboardButtonUrlAuth#10b78d29";
    }

}