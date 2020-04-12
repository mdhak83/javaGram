package org.telegram.api.poll.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api._primitives.TLObject;

/**
 * pollAnswer
 * A possible answer of a poll
 * pollAnswer#6ca9c2e9 text:string option:bytes = PollAnswer;
 */
public class TLPollAnswer extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6ca9c2e9;

    /**
     * Textual representation of the answer
     */
    private String text;
    
    /**
     * The param that has to be passed to @see <a href="https://core.telegram.org/method/messages.sendVote">messages.sendVote</a>.
     */
    private TLBytes option;

    public TLPollAnswer() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TLBytes getOption() {
        return option;
    }

    public void setOption(TLBytes option) {
        this.option = option;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.text, stream);
        StreamingUtils.writeTLBytes(this.option, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.text = StreamingUtils.readTLString(stream);
        this.option = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "pollAnswer#6ca9c2e9";
    }

}