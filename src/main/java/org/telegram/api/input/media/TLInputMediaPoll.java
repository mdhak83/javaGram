package org.telegram.api.input.media;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.poll.base.TLPoll;

/**
 * A poll
 * inputMediaPoll#6b3765b poll:Poll = InputMedia;
 */
public class TLInputMediaPoll extends TLAbsInputMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6b3765b;

    /**
     * The poll to send
     */
    private TLPoll poll;

    public TLInputMediaPoll() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLPoll getPoll() {
        return poll;
    }

    public void setPoll(TLPoll poll) {
        this.poll = poll;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.poll, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.poll = StreamingUtils.readTLObject(stream, context, TLPoll.class);
    }

    @Override
    public String toString() {
        return "inputMediaPoll#6b3765b";
    }

}