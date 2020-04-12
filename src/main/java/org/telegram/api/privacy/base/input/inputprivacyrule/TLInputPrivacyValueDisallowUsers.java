package org.telegram.api.privacy.base.input.inputprivacyrule;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.user.base.input.TLAbsInputUser;

/**
 * Disallow only certain users
 * inputPrivacyValueDisallowUsers#90110467 users:Vector&lt;InputUser&gt; = InputPrivacyRule;
 */
public class TLInputPrivacyValueDisallowUsers extends TLAbsInputPrivacyRule {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x90110467;

    /**
     * Users to disallow
     */
    private TLVector<TLAbsInputUser> users = new TLVector<>();

    public TLInputPrivacyValueDisallowUsers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsInputUser> getUsers() {
        return this.users;
    }

    public void setUsers(TLVector<TLAbsInputUser> users) {
        this.users = users;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsInputUser.class);
    }

    @Override
    public String toString() {
        return "inputPrivacyValueDisallowUsers#90110467";
    }

}