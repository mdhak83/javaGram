package org.telegram.api.account.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api.password.base.TLAbsPasswordKdfAlgo;
import org.telegram.api.password.base.TLAbsSecurePasswordKdfAlgo;
import org.telegram.api._primitives.TLObject;

/**
 * account.password
 * Configuration for two-factor authorization
 * account.password#ad2641f8 flags:# has_recovery:flags.0?true has_secure_values:flags.1?true has_password:flags.2?true current_algo:flags.2?PasswordKdfAlgo srp_B:flags.2?bytes srp_id:flags.2?long hint:flags.3?string email_unconfirmed_pattern:flags.4?string new_algo:PasswordKdfAlgo new_secure_algo:SecurePasswordKdfAlgo secure_random:bytes = account.Password;
 */
public class TLAccountPassword extends TLObject {

    /**
     * The constant CLASS_ID of this class.
     */
    public static final int CLASS_ID = 0xad2641f8;

    private static final int FLAG_HAS_RECOVERY              = 0x00000001; // 0
    private static final int FLAG_HAS_SECURE_VALUES         = 0x00000002; // 1
    private static final int FLAG_HAS_PASSWORD              = 0x00000004; // 2
    private static final int FLAG_HINT                      = 0x00000008; // 3
    private static final int FLAG_EMAIL_UNCONFIRMED_PATTERN = 0x00000010; // 4

    /**
     * The @see <a href="https://core.telegram.org/api/srp">KDF algorithm for SRP two-factor authentication</a> of the current password
     */
    private TLAbsPasswordKdfAlgo currentAlgo;
    
    /**
     * Srp B param for @see <a href="https://core.telegram.org/api/srp">SRP authorization</a>
     */
    private TLBytes srpB;
    
    /**
     * Srp ID param for @see <a href="https://core.telegram.org/api/srp">SRP authorization</a>
     */
    private long srpId;
    
    /**
     * Text hint for the password
     */
    private String hint;
    
    /**
     * A @see <a href="https://core.telegram.org/api/srp#email-verification">password recovery email</a> with the specified @see <a href="https://core.telegram.org/api/pattern">pattern</a> is still awaiting verification
     */
    private String emailUnconfirmedPattern;
    
    /**
     * The @see <a href="https://core.telegram.org/api/srp">KDF algorithm for SRP two-factor authentication</a> to use when creating new passwords
     */
    private TLAbsPasswordKdfAlgo newAlgo;
    
    /**
     * The KDF algorithm for telegram @see <a href="https://core.telegram.org/passport">passport</a>
     */
    private TLAbsSecurePasswordKdfAlgo newSecureAlgo;
    
    /**
     * Secure random string
     */
    private TLBytes secureRandom;
    
    public TLAccountPassword() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean hasRecovery() {
        return this.isFlagSet(FLAG_HAS_RECOVERY);
    }

    public void setRecovery(boolean value) {
        this.setFlag(FLAG_HAS_RECOVERY, value);
    }

    public boolean hasSecureValues() {
        return this.isFlagSet(FLAG_HAS_SECURE_VALUES);
    }

    public void setSecureValues(boolean value) {
        this.setFlag(FLAG_HAS_SECURE_VALUES, value);
    }

    public boolean hasPassword() {
        return this.isFlagSet(FLAG_HAS_PASSWORD);
    }

    public TLAbsPasswordKdfAlgo getCurrentAlgo() {
        return currentAlgo;
    }

    public void setCurrentAlgo(TLAbsPasswordKdfAlgo currentAlgo) {
        this.currentAlgo = currentAlgo;
        this.setFlag(FLAG_HAS_PASSWORD, this.currentAlgo != null || this.srpB != null || this.srpId != 0L);
    }

    public TLBytes getSrpB() {
        return srpB;
    }

    public void setSrpB(TLBytes srpB) {
        this.srpB = srpB;
        this.setFlag(FLAG_HAS_PASSWORD, this.currentAlgo != null || this.srpB != null || this.srpId != 0L);
    }

    public long getSrpId() {
        return srpId;
    }

    public void setSrpId(long srpId) {
        this.srpId = srpId;
        this.setFlag(FLAG_HAS_PASSWORD, this.currentAlgo != null || this.srpB != null || this.srpId != 0L);
    }

    public boolean hasHint() {
        return this.isFlagSet(FLAG_HINT);
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
        this.setFlag(FLAG_HINT, this.hint != null && !this.hint.trim().isEmpty());
    }

    public boolean hasEmailUnconfirmedPattern() {
        return this.isFlagSet(FLAG_EMAIL_UNCONFIRMED_PATTERN);
    }

    public String getEmailUnconfirmedPattern() {
        return emailUnconfirmedPattern;
    }

    public void setEmailUnconfirmedPattern(String emailUnconfirmedPattern) {
        this.emailUnconfirmedPattern = emailUnconfirmedPattern;
        this.setFlag(FLAG_EMAIL_UNCONFIRMED_PATTERN, this.emailUnconfirmedPattern != null && !this.emailUnconfirmedPattern.trim().isEmpty());
    }

    public TLAbsPasswordKdfAlgo getNewAlgo() {
        return newAlgo;
    }

    public void setNewAlgo(TLAbsPasswordKdfAlgo newAlgo) {
        this.newAlgo = newAlgo;
    }

    public TLAbsSecurePasswordKdfAlgo getNewSecureAlgo() {
        return newSecureAlgo;
    }

    public void setNewSecureAlgo(TLAbsSecurePasswordKdfAlgo newSecureAlgo) {
        this.newSecureAlgo = newSecureAlgo;
    }

    public TLBytes getSecureRandom() {
        return secureRandom;
    }

    public void setSecureRandom(TLBytes secureRandom) {
        this.secureRandom = secureRandom;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if (this.hasPassword()) {
            StreamingUtils.writeTLObject(this.currentAlgo, stream);
            StreamingUtils.writeTLBytes(this.srpB, stream);
            StreamingUtils.writeLong(this.srpId, stream);
        }
        if (this.hasHint()) {
            StreamingUtils.writeTLString(this.hint, stream);
        }
        if (this.hasEmailUnconfirmedPattern()) {
            StreamingUtils.writeTLString(this.emailUnconfirmedPattern, stream);
        }
        StreamingUtils.writeTLObject(this.newAlgo, stream);
        StreamingUtils.writeTLObject(this.newSecureAlgo, stream);
        StreamingUtils.writeTLBytes(this.secureRandom, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.hasPassword()) {
            this.currentAlgo = StreamingUtils.readTLObject(stream, context, TLAbsPasswordKdfAlgo.class);
            this.srpB = StreamingUtils.readTLBytes(stream, context);
            this.srpId = StreamingUtils.readLong(stream);
        }
        if (this.hasHint()) {
            this.hint = StreamingUtils.readTLString(stream);
        }
        if (this.hasEmailUnconfirmedPattern()) {
            this.emailUnconfirmedPattern = StreamingUtils.readTLString(stream);
        }
        this.newAlgo = StreamingUtils.readTLObject(stream, context, TLAbsPasswordKdfAlgo.class);
        this.newSecureAlgo = StreamingUtils.readTLObject(stream, context, TLAbsSecurePasswordKdfAlgo.class);
        this.secureRandom = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "account.password#ad2641f8";
    }

}