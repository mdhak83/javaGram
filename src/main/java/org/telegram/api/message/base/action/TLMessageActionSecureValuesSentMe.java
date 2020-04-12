package org.telegram.api.message.base.action;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.secure.base.TLSecureValue;
import org.telegram.api.secure.base.credentials.TLSecureCredentialsEncrypted;
import org.telegram.api._primitives.TLVector;

/**
 * Secure @see <a href="https://core.telegram.org/passport">telegram passport</a> values were received
 * messageActionSecureValuesSentMe#1b287353 values:Vector&lt;SecureValue&gt; credentials:SecureCredentialsEncrypted = MessageAction;
 */
public class TLMessageActionSecureValuesSentMe extends TLAbsMessageAction {

    /**
     * 
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1b287353;

    /**
     * Vector with information about documents and other Telegram Passport elements that were shared with the bot
     */
    private TLVector<TLSecureValue> values;
    
    /**
     * Encrypted credentials required to decrypt the data
     */
    private TLSecureCredentialsEncrypted credentials;

    public TLMessageActionSecureValuesSentMe() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLSecureValue> getValues() {
        return values;
    }

    public void setValues(TLVector<TLSecureValue> values) {
        this.values = values;
    }

    public TLSecureCredentialsEncrypted getCredentials() {
        return credentials;
    }

    public void setCredentials(TLSecureCredentialsEncrypted credentials) {
        this.credentials = credentials;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.values, stream);
        StreamingUtils.writeTLObject(this.credentials, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.values = StreamingUtils.readTLVector(stream, context, TLSecureValue.class);
        this.credentials = StreamingUtils.readTLObject(stream, context, TLSecureCredentialsEncrypted.class);
    }

    @Override
    public String toString() {
        return "messageActionSecureValuesSentMe#1b287353";
    }

}