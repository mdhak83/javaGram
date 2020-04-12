package org.telegram.api.account.base;

import org.telegram.api.privacy.base.privacyrule.TLAbsPrivacyRule;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.chat.base.TLAbsChat;

/**
 * Privacy rules
 * account.privacyRules#50a04e45 rules:Vector&lt;PrivacyRule&gt; chats:Vector&lt;Chat&gt; users:Vector&lt;User&gt; = account.PrivacyRules;
 */
public class TLAccountPrivacyRules extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x50a04e45;

    /**
     * Privacy rules
     */
    private TLVector<TLAbsPrivacyRule> rules;
    
    /**
     * Chats to which the rules apply
     */
    private TLVector<TLAbsChat> chats;
    
    /**
     * Users to which the rules apply
     */
    private TLVector<TLAbsUser> users;
   
    public TLAccountPrivacyRules() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsPrivacyRule> getRules() {
        return this.rules;
    }

    public void setRules(TLVector<TLAbsPrivacyRule> rules) {
        this.rules = rules;
    }

    public TLVector<TLAbsChat> getChats() {
        return this.chats;
    }

    public void setChats(TLVector<TLAbsChat> chats) {
        this.chats = chats;
    }

    public TLVector<TLAbsUser> getUsers() {
        return this.users;
    }

    public void setUsers(TLVector<TLAbsUser> users) {
        this.users = users;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.rules, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.rules = StreamingUtils.readTLVector(stream, context, TLAbsPrivacyRule.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "account.PrivacyRules#50a04e45";
    }

}