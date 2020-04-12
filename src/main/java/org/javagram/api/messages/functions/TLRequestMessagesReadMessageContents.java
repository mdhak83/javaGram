package org.javagram.api.messages.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.messages.base.TLAffectedMessages;
import org.javagram.api.updates.base.TLAbsUpdates;
import org.javagram.api._primitives.TLIntVector;
import org.javagram.api._primitives.TLObject;

/**
 * Notifies the sender about the recipient having listened a voice message or watched a video.
 * messages.readMessageContents#36a73f77 id:Vector&lt;int&gt; = messages.AffectedMessages;
 */
public class TLRequestMessagesReadMessageContents extends TLMethod<TLAffectedMessages> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x36a73f77;

    /**
     * Message ID list
     */
    private TLIntVector id;

    public TLRequestMessagesReadMessageContents() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLIntVector getId() {
        return id;
    }

    public void setId(TLIntVector id) {
        this.id = id;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readTLIntVector(stream, context);
    }

    @Override
    public TLAffectedMessages deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAffectedMessages) {
            return (TLAffectedMessages) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.readMessageContents#36a73f77";
    }

}