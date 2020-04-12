package org.telegram.api.account.base;

import org.telegram.api.secure.base.TLSecureSecretSettings;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.password.base.TLAbsPasswordKdfAlgo;

/**
 * Settings for setting up a new password
 * account.passwordInputSettings#c23727c9 flags:# new_algo:flags.0?PasswordKdfAlgo new_password_hash:flags.0?bytes hint:flags.0?string email:flags.1?string new_secure_settings:flags.2?SecureSecretSettings = account.PasswordInputSettings;
 */
public class TLAccountPasswordInputSettings extends TLObject {

    /**
     * The constant CLASS_ID of this class.
     */
    public static final int CLASS_ID = 0xc23727c9;

    private static final int FLAG_NEW_ALGO              = 0x00000001; // 0
    private static final int FLAG_EMAIL                 = 0x00000002; // 1
    private static final int FLAG_NEW_SECURE_SETTINGS   = 0x00000004; // 2

    /**
     * The @see <a href="https://core.telegram.org/api/srp">SRP algorithm</a> to use
     */
    private TLAbsPasswordKdfAlgo newAlgo;
    
    /**
     * The @see <a href="https://core.telegram.org/api/srp">computed password hash</a>
     */
    private TLBytes newPasswordHash;
    
    /**
     * Text hint for the password
     */
    private String hint;
    
    /**
     * Password recovery email
     */
    private String email;
    
    /**
     * Telegram @see <a href="https://core.telegram.org/passport">passport</a> settings
     */
    private TLSecureSecretSettings newSecureSettings;

    public TLAccountPasswordInputSettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean hasNewAlgo() {
        return this.isFlagSet(FLAG_NEW_ALGO);
    }
    
    public TLAbsPasswordKdfAlgo getNewAlgo() {
        return newAlgo;
    }

    public void setNewAlgo(TLAbsPasswordKdfAlgo newAlgo) {
        this.newAlgo = newAlgo;
        if (this.newAlgo != null) {
            this.flags |= FLAG_NEW_ALGO;
        } else {
            this.flags &= ~FLAG_NEW_ALGO;
        }
    }

    public TLBytes getNewPasswordHash() {
        return newPasswordHash;
    }

    public void setNewPasswordHash(TLBytes newPasswordHash) {
        this.newPasswordHash = newPasswordHash;
        if (this.newPasswordHash != null) {
            this.flags |= FLAG_NEW_ALGO;
        } else {
            this.flags &= ~FLAG_NEW_ALGO;
        }
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
        if (this.hint != null && !this.hint.trim().isEmpty()) {
            this.flags |= FLAG_NEW_ALGO;
        } else {
            this.flags &= ~FLAG_NEW_ALGO;
        }
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

    public boolean hasNewSecureSettings() {
        return this.isFlagSet(FLAG_NEW_SECURE_SETTINGS);
    }
    
    public TLSecureSecretSettings getNewSecureSettings() {
        return newSecureSettings;
    }

    public void setNewSecureSettings(TLSecureSecretSettings newSecureSettings) {
        this.newSecureSettings = newSecureSettings;
        if (this.newSecureSettings != null) {
            this.flags |= FLAG_NEW_SECURE_SETTINGS;
        } else {
            this.flags &= ~FLAG_NEW_SECURE_SETTINGS;
        }
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if (this.hasNewAlgo()) {
            StreamingUtils.writeTLObject(this.newAlgo, stream);
            StreamingUtils.writeTLBytes(this.newPasswordHash, stream);
            StreamingUtils.writeTLString(this.hint, stream);
        }
        if (this.hasEmail()) {
            StreamingUtils.writeTLString(this.email, stream);
        }
        if (this.hasNewSecureSettings()) {
            StreamingUtils.writeTLObject(this.newSecureSettings, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.hasNewAlgo()) {
            this.newAlgo = StreamingUtils.readTLObject(stream, context, TLAbsPasswordKdfAlgo.class);
            this.newPasswordHash = StreamingUtils.readTLBytes(stream, context);
            this.hint = StreamingUtils.readTLString(stream);
        }
        if (this.hasEmail()) {
            this.email = StreamingUtils.readTLString(stream);
        }
        if (this.hasNewSecureSettings()) {
            this.newSecureSettings = StreamingUtils.readTLObject(stream, context, TLSecureSecretSettings.class);
        }
    }

    @Override
    public String toString() {
        return "account.passwordInputSettings#c23727c9";
    }

}