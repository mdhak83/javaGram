package org.javagram.api.channels.functions;

import org.javagram.api.channel.base.input.TLAbsInputChannel;
import org.javagram.api.user.base.input.TLAbsInputUser;
import org.javagram.api.updates.base.TLAbsUpdates;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.password.base.input.checkpassword.TLAbsInputCheckPasswordSRP;

/**
 * Transfer channel ownership
 * channels.editCreator#8f38cd1f channel:InputChannel user_id:InputUser password:InputCheckPasswordSRP = Updates;
 */
public class TLRequestChannelsEditCreator extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8f38cd1f;

    /**
     * Channel
     */
    private TLAbsInputChannel channel;

    /**
     * New channel owner
     */
    private TLAbsInputUser userId;

    /**
     * @see <a href="https://core.telegram.org/api/srp">2FA password</a> of account
     */
    private TLAbsInputCheckPasswordSRP password;

    public TLRequestChannelsEditCreator() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputChannel getChannel() {
        return channel;
    }

    public void setChannel(TLAbsInputChannel channel) {
        this.channel = channel;
    }

    public TLAbsInputUser getUserId() {
        return userId;
    }

    public void setUserId(TLAbsInputUser userId) {
        this.userId = userId;
    }

    public TLAbsInputCheckPasswordSRP getPassword() {
        return password;
    }

    public void setPassword(TLAbsInputCheckPasswordSRP password) {
        this.password = password;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.channel, stream);
        StreamingUtils.writeTLObject(this.userId, stream);
        StreamingUtils.writeTLObject(this.password, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channel = StreamingUtils.readTLObject(stream, context,TLAbsInputChannel.class);
        this.userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
        this.password = StreamingUtils.readTLObject(stream, context, TLAbsInputCheckPasswordSRP.class);
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "channels.editCreator#8f38cd1f";
    }

}