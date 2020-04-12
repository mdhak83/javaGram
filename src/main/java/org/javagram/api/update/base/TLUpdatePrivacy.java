package org.javagram.api.update.base;

import org.javagram.api.privacy.base.privacykey.TLAbsPrivacyKey;
import org.javagram.api.privacy.base.privacyrule.TLAbsPrivacyRule;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update privacy.
 */
public class TLUpdatePrivacy extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xee3b272a;

    private TLAbsPrivacyKey key;
    private TLVector<TLAbsPrivacyRule> rules;

    public TLUpdatePrivacy() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public TLAbsPrivacyKey getKey() {
        return this.key;
    }

    /**
     * Sets key.
     *
     * @param key the key
     */
    public void setKey(TLAbsPrivacyKey key) {
        this.key = key;
    }

    /**
     * Gets rules.
     *
     * @return the rules
     */
    public TLVector<TLAbsPrivacyRule> getRules() {
        return this.rules;
    }

    /**
     * Sets rules.
     *
     * @param rules the rules
     */
    public void setRules(TLVector<TLAbsPrivacyRule> rules) {
        this.rules = rules;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.key, stream);
        StreamingUtils.writeTLVector(this.rules, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.key = StreamingUtils.readTLObject(stream, context, TLAbsPrivacyKey.class);
        this.rules = StreamingUtils.readTLVector(stream, context);
    }

    @Override
    public String toString() {
        return "updatePrivacy#ee3b272a";
    }

}