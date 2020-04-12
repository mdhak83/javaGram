package org.javagram.api;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Settings used by telegram servers for sending the confirm code.
 * Example implementations: @see <a href="https://github.com/DrKLO/Telegram/blob/master/TMessagesProj/src/main/java/org/telegram/ui/LoginActivity.java">telegram for android</a>, @see <a href="https://github.com/tdlib/td/tree/master/td/telegram/SendCodeHelper.cpp">tdlib</a>.
 * codeSettings#debebe83 flags:# allow_flashcall:flags.0?true current_number:flags.1?true allow_app_hash:flags.4?true = CodeSettings;
 */
public class TLCodeSettings extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xdebebe83;

    private static final int FLAG_ALLOW_FLASHCALL   = 0x00000001; // 0 : Whether to allow phone verification via phone calls.
    private static final int FLAG_CURRENT_NUMBER    = 0x00000002; // 1 : Pass true if the phone number is used on the current device. Ignored if allow_flashcall is not set.
    private static final int FLAG_ALLOW_APP_HASH    = 0x00000010; // 4 : If a token that will be included in eventually sent SMSs is required: required in newer versions of android, to use the android SMS receiver APIs
    
    public TLCodeSettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isFlashcallAllowed() {
        return this.isFlagSet(FLAG_ALLOW_FLASHCALL);
    }
    
    public void setFlashCallAllowed(boolean value) {
        this.setFlag(FLAG_ALLOW_FLASHCALL, value);
    }
    
    public boolean isCurrentNumber() {
        return this.isFlagSet(FLAG_CURRENT_NUMBER);
    }
    
    public void setCurrentNumber(boolean value) {
        this.setFlag(FLAG_CURRENT_NUMBER, value);
    }
    
    public boolean isAppHashAllowed() {
        return this.isFlagSet(FLAG_ALLOW_APP_HASH);
    }
    
    public void setAppHashAllowed(boolean value) {
        this.setFlag(FLAG_ALLOW_APP_HASH, value);
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
        return "codeSettings#debebe83";
    }

}