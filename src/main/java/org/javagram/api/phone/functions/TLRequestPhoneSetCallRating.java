package org.javagram.api.phone.functions;

import org.javagram.api.phone.base.call.input.TLInputPhoneCall;
import org.javagram.api.updates.base.TLAbsUpdates;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Rate a call
 * phone.setCallRating#59ead627 flags:# user_initiative:flags.0?true peer:InputPhoneCall rating:int comment:string = Updates;
 */
public class TLRequestPhoneSetCallRating extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x59ead627;

    private static final int FLAG_USER_INITIATIVE   = 0x00000001; // 0 : Whether the user decided on their own initiative to rate the call

    /**
     * The call to rate
     */
    private TLInputPhoneCall peer;

    /**
     * Rating in <code>1-5</code> stars
     */
    private int rating;

    /**
     * An additional comment
     */
    private String comment;

    public TLRequestPhoneSetCallRating() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isUserInitiative() {
        return this.isFlagSet(FLAG_USER_INITIATIVE);
    }

    public void setUserInitiative(boolean value) {
        this.setFlag(FLAG_USER_INITIATIVE, value);
    }

    public TLInputPhoneCall getPeer() {
        return peer;
    }

    public void setPeer(TLInputPhoneCall peer) {
        this.peer = peer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(peer, stream);
        StreamingUtils.writeInt(rating, stream);
        StreamingUtils.writeTLString(comment, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        peer = StreamingUtils.readTLObject(stream, context, TLInputPhoneCall.class);
        rating = StreamingUtils.readInt(stream);
        comment = StreamingUtils.readTLString(stream);
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getCanonicalName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    @Override
    public String toString() {
        return "phone.setCallRating#59ead627";
    }

}