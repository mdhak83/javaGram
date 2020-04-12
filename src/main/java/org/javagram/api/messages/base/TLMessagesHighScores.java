package org.javagram.api.messages.base;

import org.javagram.api.TLHighScore;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
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
