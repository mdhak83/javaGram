package org.telegram.api.account.functions;

import org.telegram.api.account.base.TLAccountPrivacyRules;
import org.telegram.api.privacy.base.input.inputprivacykey.TLAbsInputPrivacyKey;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Get privacy settings of current account
 * account.getPrivacy#dadbc950 key:InputPrivacyKey = account.PrivacyRules;
 */
public class TLRequestAccountGetPrivacy extends TLMethod<TLAccountPrivacyRules> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xdadbc950;

    /**
     * Peer category whose privacy settings should be fetched
     */
    private TLAbsInputPrivacyKey key;

    public TLRequestAccountGetPrivacy() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputPrivacyKey getKey() {
        return this.key;
    }

    /**
     * Sets key.
     *
     * @param key the key
     */
    public void setKey(TLAbsInputPrivacyKey key) {
        this.key = key;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.key, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.key = StreamingUtils.readTLObject(stream, context, TLAbsInputPrivacyKey.class);
    }

    @Override
    public TLAccountPrivacyRules deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAccountPrivacyRules)  {
            return (TLAccountPrivacyRules) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.account.TLAccountPrivacyRules, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.getPrivacy#dadbc950";
    }

}