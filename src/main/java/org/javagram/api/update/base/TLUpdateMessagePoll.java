package org.javagram.api.update.base;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.poll.base.TLPoll;
import org.javagram.api.poll.base.TLPollResults;
import org.javagram.utils.StreamingUtils;

/**
 * The results of a poll have changed
 * updateMessagePoll#aca1657b flags:# poll_id:long poll:flags.0?Poll results:PollResults = Update;
 */
public class TLUpdateMessagePoll extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xaca1657b;

    private static final int FLAG_POLL  = 0x00000001; // 0 : If the server knows the client hasn't cached this poll yet, the poll itself

    /**
     * Poll ID
     */
    private long pollId;
    
    /**
     * If the server knows the client hasn't cached this poll yet, the poll itself
     */
    private TLPoll poll;

    /**
     * New poll results
     */
    private TLPollResults results;

    public TLUpdateMessagePoll() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getPollId() {
        return pollId;
    }

    public void setPollId(long pollId) {
        this.pollId = pollId;
    }

    public boolean hasPoll() {
        return this.isFlagSet(FLAG_POLL);
    }
    
    public TLPoll getPoll() {
        return poll;
    }

    public void setPoll(TLPoll poll) {
        this.poll = poll;
        this.setFlag(FLAG_POLL, poll != null);
    }

    public TLPollResults getResults() {
        return results;
    }

    public void setResults(TLPollResults results) {
        this.results = results;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(this.pollId, stream);
        if (this.hasPoll()) {
            StreamingUtils.writeTLObject(this.poll, stream);
        }
        StreamingUtils.writeTLObject(this.results, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.pollId = StreamingUtils.readLong(stream);
        if (this.hasPoll()) {
            this.poll = StreamingUtils.readTLObject(stream, context, TLPoll.class);
        }
        this.results = StreamingUtils.readTLObject(stream, context, TLPollResults.class);
    }

    @Override
    public String toString() {
        return "updateMessagePoll#aca1657b";
    }

}