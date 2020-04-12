package org.telegram.api.phone.base;

import org.telegram.api.phone.base.call.TLAbsPhoneCall;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A VoIP phone call
 * phone.phoneCall#ec82e140 phone_call:PhoneCall users:Vector&lt;User&gt; = phone.PhoneCall;
 */
public class TLPhonePhoneCall extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xec82e140;

    /**
     * The VoIP phone call
     */
    private TLAbsPhoneCall phoneCall;
    
    /**
     * VoIP phone call participants
     */
    private TLVector<TLAbsUser> users;

    public TLPhonePhoneCall() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsPhoneCall getPhoneCall() {
        return phoneCall;
    }

    public void setPhoneCall(TLAbsPhoneCall phoneCall) {
        this.phoneCall = phoneCall;
    }

    public TLVector<TLAbsUser> getUsers() {
        return users;
    }

    public void setUsers(TLVector<TLAbsUser> users) {
        this.users = users;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.phoneCall, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.phoneCall = StreamingUtils.readTLObject(stream, context, TLAbsPhoneCall.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "phone.phoneCall#ec82e140";
    }

}