package org.telegram.api.help.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.help.base.TLHelpUserInfo;
import org.telegram.api.user.base.input.TLAbsInputUser;

/**
 * Internal use
 * help.getUserInfo#38a08d3 user_id:InputUser = help.UserInfo;
 */
public class TLRequestHelpGetUserInfo extends TLMethod<TLHelpUserInfo> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x38a08d3;
    
    /**
     * User ID
     */
    private TLAbsInputUser userId;

    public TLRequestHelpGetUserInfo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputUser getUserId() {
        return userId;
    }

    public void setUserId(TLAbsInputUser userId) {
        this.userId = userId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.userId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
    }

    @Override
    public TLHelpUserInfo deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLHelpUserInfo) {
            return (TLHelpUserInfo) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.help.TLSupport, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "help.getUserInfo#38a08d3";
    }

}