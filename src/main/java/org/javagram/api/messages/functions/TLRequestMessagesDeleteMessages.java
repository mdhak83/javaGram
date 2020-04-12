package org.javagram.api.messages.functions;

import org.javagram.api.messages.base.TLAffectedMessages;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLIntVector;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Deletes messages by their identifiers.
 * messages.deleteMessages#e58e95d2 flags:# revoke:flags.0?true id:Vector&lt;int&gt; = messages.AffectedMessages;
 */
public class TLRequestMessagesDeleteMessages extends TLMethod<TLAffectedMessages> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe58e95d2;

    private static final int FLAG_REVOKE      = 0x00000001; // 0 : Whether to delete messages for all participants of the chat

    /**
     * Message ID list
     */
    private TLIntVector id;

    public TLRequestMessagesDeleteMessages() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isRevoke() {
        return this.isFlagSet(FLAG_REVOKE);
    }

    public void setRevoke(boolean value) {
        this.setFlag(FLAG_REVOKE, value);
    }

    public TLIntVector getId() {
        return this.id;
    }

    public void setId(TLIntVector id) {
        this.id = id;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLVector(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readTLIntVector(stream, context);
    }

    @Override
    public TLAffectedMessages deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAffectedMessages) {
            return (TLAffectedMessages) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.messages.TLAffectedMessages, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.deleteMessages#e58e95d2";
    }

}