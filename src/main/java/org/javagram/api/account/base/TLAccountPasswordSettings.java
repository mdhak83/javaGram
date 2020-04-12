package org.javagram.api.account.base;

import org.javagram.api.secure.base.TLSecureSecretSettings;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Private info associated to the password info (recovery email, telegram @see <a href="https://core.telegram.org/passport">passport</a> info &amp; so on)
 * account.passwordSettings#9a5c33e5 flags:# email:flags.0?string secure_settings:flags.1?SecureSecretSettings = account.PasswordSettings;
 */
public class TLAccountPasswordSettings extends TLObject {

    /**
     * The constant CLASS_ID for this class.
     */
    public static final int CLASS_ID = 0x9a5c33e5;

    private static final int FLAG_EMAIL             = 0x00000001; // 0
    private static final int FLAG_SECURE_SETTINGS   = 0x00000002; // 1

    /**
     * @see <a href="https://core.telegram.org/api/srp#email-verification">2FA Recovery email</a>
     */
    private String email;
    
    /**
     * Telegram @see <a href="https://core.telegram.org/passport">passport</a> settings
     */
    private TLSecureSecretSettings secureSettings;
    
    public TLAccountPasswordSettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean hasEmail() {
        return this.isFlagSet(FLAG_EMAIL);
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        if (this.email != null && !this.email.trim().isEmpty()) {
            this.flags |= FLAG_EMAIL;
        } else {
            this.flags &= ~FLAG_EMAIL;
        }
    }

    public boolean hasSecureSettings() {
        return this.isFlagSet(FLAG_SECURE_SETTINGS);
    }
    
    public TLSecureSecretSettings getSecureSettings() {
        return secureSettings;
    }

    public void setSecureSettings(TLSecureSecretSettings secureSettings) {
        this.secureSettings = secureSettings;
        this.setFlag(FLAG_SECURE_SETTINGS, this.secureSettings != null);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if (this.hasEmail()) {
            StreamingUtils.writeTLString(this.email, stream);
        }
        if (this.hasSecureSettings()) {
            StreamingUtils.writeTLObject(this.secureSettings, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.hasEmail()) {
            this.email = StreamingUtils.readTLString(stream);
        }
        if (this.hasSecureSettings()) {
            this.secureSettings = StreamingUtils.readTLObject(stream, context, TLSecureSecretSettings.class);
        }
    }

    @Override
    public String toString() {
        return "account.passwordSettings#9a5c33e5";
    }

}