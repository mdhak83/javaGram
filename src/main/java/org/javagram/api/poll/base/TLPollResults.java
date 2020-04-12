package org.javagram.api.poll.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;

/**
 * Results of poll
 * pollResults#5755785a flags:# min:flags.0?true results:flags.1?Vector&lt;PollAnswerVoters&gt; total_voters:flags.2?int = PollResults;
 */
public class TLPollResults extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5755785a;

    private static final int FLAG_MIN           = 0x00000001; // 0 : Similar to min objects, used for poll constructors that are the same for all users so they don't have option chosen by the current user (you can use messages.getPollResults to get the full poll results).
    private static final int FLAG_RESULTS       = 0x00000002; // 1 : Poll results
    private static final int FLAG_TOTAL_VOTERS  = 0x00000004; // 2 : Total number of people that voted in the poll

    /**
     * Poll results
     */
    private TLVector<TLPollAnswerVoters> results;
    
    /**
     * Total number of people that voted in the poll
     */
    private int totalVoters;

    public TLPollResults() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isMin() {
        return this.isFlagSet(FLAG_MIN);
    }
    
    public void setMin(boolean value) {
        this.setFlag(FLAG_MIN, value);
    }
    
    public boolean hasResults() {
        return this.isFlagSet(FLAG_RESULTS);
    }
    
    public TLVector<TLPollAnswerVoters> getResults() {
        return results;
    }

    public void setResults(TLVector<TLPollAnswerVoters> results) {
        this.results = results;
        if (this.results != null && !this.results.isEmpty()) {
            this.flags |= FLAG_RESULTS;
        } else {
            this.flags &= ~FLAG_RESULTS;
        }
    }

    public boolean hasTotalVoters() {
        return this.isFlagSet(FLAG_TOTAL_VOTERS);
    }
    
    public int getTotalVoters() {
        return totalVoters;
    }

    public void setTotalVoters(int totalVoters) {
        this.totalVoters = totalVoters;
        if (this.totalVoters != 0) {
            this.flags |= FLAG_TOTAL_VOTERS;
        } else {
            this.flags &= ~FLAG_TOTAL_VOTERS;
        }
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if (this.hasResults()) {
            StreamingUtils.writeTLVector(this.results, stream);
        }
        if (this.hasTotalVoters()) {
            StreamingUtils.writeInt(this.totalVoters, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.hasResults()) {
            this.results = StreamingUtils.readTLVector(stream, context, TLPollAnswerVoters.class);
        }
        if (this.hasTotalVoters()) {
            this.totalVoters = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "pollResults#5755785a";
    }

}