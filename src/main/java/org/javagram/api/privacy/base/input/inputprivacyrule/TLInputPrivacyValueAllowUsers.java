package org.javagram.api.privacy.base.input.inputprivacyrule;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.user.base.input.TLAbsInputUser;

/**
 * Allow only certain users
 * inputPrivacyValueAllowUsers#131cc67f users:Vector&lt;InputUser&gt; = InputPrivacyRule;
 */
public class TLInputPrivacyValueAllowUsers extends TLAbsInputPrivacyRule {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x131cc67f;

    /**
     * Allowed users
     */
    private TLVector<TLAbsInputUser> users = new TLVector<>();

    public TLInputPrivacyValueAllowUsers() {
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
        return "inputPrivacyValueAllowUsers#131cc67f";
    }

}