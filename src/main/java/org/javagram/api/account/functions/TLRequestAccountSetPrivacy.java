package org.javagram.api.account.functions;

import org.javagram.api.account.base.TLAccountPrivacyRules;
import org.javagram.api.privacy.base.input.inputprivacykey.TLAbsInputPrivacyKey;
import org.javagram.api.privacy.base.input.inputprivacyrule.TLAbsInputPrivacyRule;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Change privacy settings of current account
 * account.setPrivacy#c9f81ce8 key:InputPrivacyKey rules:Vector&lt;InputPrivacyRule&gt; = account.PrivacyRules;
 */
public class TLRequestAccountSetPrivacy extends TLMethod<TLAccountPrivacyRules> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc9f81ce8;

    /**
     * Peers to which the privacy rules apply
     */
    private TLAbsInputPrivacyKey key;
    
    /**
     * New privacy rules
     */
    private TLVector<TLAbsInputPrivacyRule> rules;

    public TLRequestAccountSetPrivacy() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputPrivacyKey getKey() {
        return this.key;
    }

    public void setKey(TLAbsInputPrivacyKey key) {
        this.key = key;
    }

    public TLVector<TLAbsInputPrivacyRule> getRules() {
        return this.rules;
    }

    public void setRules(TLVector<TLAbsInputPrivacyRule> rules) {
        this.rules = rules;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.key, stream);
        StreamingUtils.writeTLVector(this.rules, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.key = StreamingUtils.readTLObject(stream, context, TLAbsInputPrivacyKey.class);
        this.rules = StreamingUtils.readTLVector(stream, context);
    }

    @Override
    public TLAccountPrivacyRules deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAccountPrivacyRules) {
            return (TLAccountPrivacyRules) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.account.TLAccountPrivacyRules, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.setPrivacy#c9f81ce8";
    }

}