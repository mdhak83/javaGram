package org.telegram.api.poll.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api._primitives.TLObject;

/**
 * pollAnswerVoters
 * A poll answer, and how users voted on it
 * pollAnswerVoters#3b6ddad2 flags:# chosen:flags.0?true correct:flags.1?true option:bytes voters:int = PollAnswerVoters;
 */
public class TLPollAnswerVoters extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3b6ddad2;

    private static final int FLAG_CHOSEN    = 0x00000001; // 0 : Whether we have chosen this answer
    private static final int FLAG_CORRECT   = 0x00000002; // 1 : For quizes, whether the option we have chosen is correct

    /**
     * The param that has to be passed to @see <a href="https://core.telegram.org/method/messages.sendVote">messages.sendVote</a>.
     */
    private TLBytes option;
    
    /**
     * How many users voted for this option
     */
    private int voters;

    public TLPollAnswerVoters() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isChosen() {
        return this.isFlagSet(FLAG_CHOSEN);
    }
    
    public void setChosen(boolean value) {
        this.setFlag(FLAG_CHOSEN, value);
    }
    
    public boolean isCorrect() {
        return this.isFlagSet(FLAG_CORRECT);
    }
    
    public void setCorrect(boolean value) {
        this.setFlag(FLAG_CORRECT, value);
    }

    public TLBytes getOption() {
        return option;
    }

    public void setOption(TLBytes option) {
        this.option = option;
    }

    public int getVoters() {
        return voters;
    }

    public void setVoters(int voters) {
        this.voters = voters;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLBytes(this.option, stream);
        StreamingUtils.writeInt(this.voters, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.option = StreamingUtils.readTLBytes(stream, context);
        this.voters = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "pollAnswerVoters#3b6ddad2";
    }

}