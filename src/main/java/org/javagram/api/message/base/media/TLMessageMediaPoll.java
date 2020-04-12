package org.javagram.api.message.base.media;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.poll.base.TLPoll;
import org.javagram.api.poll.base.TLPollResults;

/**
 * messageMediaPoll
 * Poll
 * messageMediaPoll#4bd6e798 poll:Poll results:PollResults = MessageMedia;
 */
public class TLMessageMediaPoll extends TLAbsMessageMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4bd6e798;

    /**
     * The poll
     */
    private TLPoll poll;
    
    /**
     * The results of the poll
     */
    private TLPollResults results;

    public TLMessageMediaPoll() {
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

    public TLPollResults getResults() {
        return results;
    }

    public void setResults(TLPollResults results) {
        this.results = results;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.poll, stream);
        StreamingUtils.writeTLObject(this.results, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.poll = StreamingUtils.readTLObject(stream, context, TLPoll.class);
        this.results = StreamingUtils.readTLObject(stream, context, TLPollResults.class);
    }

    @Override
    public String toString() {
        return "messageMediaPoll#4bd6e798";
    }

}