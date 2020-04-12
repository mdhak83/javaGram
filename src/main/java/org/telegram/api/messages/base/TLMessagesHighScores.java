package org.telegram.api.messages.base;

import org.telegram.api.TLHighScore;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 *  * @since 26/SEP/2016
 */
public class TLMessagesHighScores extends TLObject {
    public static final int CLASS_ID = 0x9a3bfd99;

    private TLVector<TLHighScore> scores;
    private TLVector<TLAbsUser> users;

    public TLMessagesHighScores() {
        super();
    }

    public TLVector<TLHighScore> getScores() {
        return scores;
    }

    public TLVector<TLAbsUser> getUsers() {
        return users;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(scores, stream);
        StreamingUtils.writeTLVector(users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        scores = StreamingUtils.readTLVector(stream, context, TLHighScore.class);
        users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "messages.highScores#9a3bfd99";
    }

}
