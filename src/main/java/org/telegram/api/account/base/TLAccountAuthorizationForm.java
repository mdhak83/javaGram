package org.telegram.api.account.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.secure.base.TLAbsSecureRequiredType;
import org.telegram.api.secure.base.TLSecureValue;
import org.telegram.api.secure.base.valueerror.TLAbsSecureValueError;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;

/**
 * @see <a href="https://core.telegram.org/passport">Telegram Passport</a> authorization form
 * account.authorizationForm#ad2e1cd8 flags:# required_types:Vector&lt;SecureRequiredType&gt; values:Vector&lt;SecureValue&gt; errors:Vector&lt;SecureValueError&gt; users:Vector&lt;User&gt; privacy_policy_url:flags.0?string = account.AuthorizationForm;
 */
public class TLAccountAuthorizationForm extends TLObject {

    /**
     * The constant CLASS_ID of this class.
     */
    public static final int CLASS_ID = 0xad2e1cd8;

    private static final int FLAG_PRIVACY_POLICY_URL    = 0x00000001; // 0 : URL of the service's privacy policy
    
    /**
     * Required @see <a href="https://core.telegram.org/passport">Telegram Passport</a> documents
     */
    private TLVector<TLAbsSecureRequiredType> requiredTypes;
    
    /**
     * Already submitted @see <a href="https://core.telegram.org/passport">Telegram Passport</a> documents
     */
    private TLVector<TLSecureValue> values;
    
    /**
     * @see <a href="https://core.telegram.org/passport">Telegram Passport</a> errors
     */
    private TLVector<TLAbsSecureValueError> errors;
    
    /**
     * Info about the bot to which the form will be submitted
     */
    private TLVector<TLAbsUser> users;
    
    /**
     * URL of the service's privacy policy
     */
    private String privacyPolicyUrl;
    
    public TLAccountAuthorizationForm() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsSecureRequiredType> getRequiredTypes() {
        return requiredTypes;
    }

    public void setRequiredTypes(TLVector<TLAbsSecureRequiredType> requiredTypes) {
        this.requiredTypes = requiredTypes;
    }

    public TLVector<TLSecureValue> getValues() {
        return values;
    }

    public void setValues(TLVector<TLSecureValue> values) {
        this.values = values;
    }

    public TLVector<TLAbsSecureValueError> getErrors() {
        return errors;
    }

    public void setErrors(TLVector<TLAbsSecureValueError> errors) {
        this.errors = errors;
    }

    public TLVector<TLAbsUser> getUsers() {
        return users;
    }

    public void setUsers(TLVector<TLAbsUser> users) {
        this.users = users;
    }

    public boolean hasPrivacyPolicyUrl() {
        return this.isFlagSet(FLAG_PRIVACY_POLICY_URL);
    }

    public String getPrivacyPolicyUrl() {
        return privacyPolicyUrl;
    }

    public void setPrivacyPolicyUrl(String privacyPolicyUrl) {
        this.privacyPolicyUrl = privacyPolicyUrl;
        this.setFlag(FLAG_PRIVACY_POLICY_URL, this.privacyPolicyUrl != null && !this.privacyPolicyUrl.trim().isEmpty());
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLVector(this.requiredTypes, stream);
        StreamingUtils.writeTLVector(this.values, stream);
        StreamingUtils.writeTLVector(this.errors, stream);
        StreamingUtils.writeTLVector(this.users, stream);
        if (this.hasPrivacyPolicyUrl()) {
            StreamingUtils.writeTLString(this.privacyPolicyUrl, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.requiredTypes = StreamingUtils.readTLVector(stream, context, TLAbsSecureRequiredType.class);
        this.values = StreamingUtils.readTLVector(stream, context, TLSecureValue.class);
        this.errors = StreamingUtils.readTLVector(stream, context, TLAbsSecureValueError.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
        if (this.hasPrivacyPolicyUrl()) {
            this.privacyPolicyUrl = StreamingUtils.readTLString(stream);
        }
    }

    @Override
    public String toString() {
        return "account.authorizationForm#ad2e1cd8";
    }

}