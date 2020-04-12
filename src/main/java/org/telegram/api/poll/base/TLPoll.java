package org.telegram.api.poll.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;

public class TLPoll extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd5529d06;

    private static final int FLAG_CLOSED            = 0x00000001; // 0
    private static final int FLAG_PUBLIC_VOTERS     = 0x00000002; // 1
    private static final int FLAG_MULTIPLE_CHOICE   = 0x00000004; // 2
    private static final int FLAG_QUIZ              = 0x00000008; // 3

    private long id;
    private int flags;
    private String question;
    private TLVector<TLPollAnswer> answers;

    public TLPoll() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public TLVector<TLPollAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(TLVector<TLPollAnswer> answers) {
        this.answers = answers;
    }
    
    public boolean isClosed() {
        return this.isFlagSet(FLAG_CLOSED);
    }

    public boolean isPublicVoters() {
        return this.isFlagSet(FLAG_PUBLIC_VOTERS);
    }

    public boolean isMultipleChoice() {
        return this.isFlagSet(FLAG_MULTIPLE_CHOICE);
    }

    public boolean isQuiz() {
        return this.isFlagSet(FLAG_QUIZ);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.question, stream);
        StreamingUtils.writeTLVector(this.answers, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.flags = StreamingUtils.readInt(stream);
        this.question = StreamingUtils.readTLString(stream);
        this.answers = StreamingUtils.readTLVector(stream, context, TLPollAnswer.class);
    }

    @Override
    public String toString() {
        return "poll#d5529d06";
    }

}