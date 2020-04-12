package org.telegram.api.message.base.action;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Someone scored in a game
 * messageActionGameScore#92a72876 game_id:long score:int = MessageAction;
 */
public class TLMessageActionGameScore extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x92a72876;

    /**
     * Game ID
     */
    private long gameId;
    
    /**
     * Score
     */    
    private int score;

    public TLMessageActionGameScore() {
        super();
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.gameId, stream);
        StreamingUtils.writeInt(this.score, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.gameId = StreamingUtils.readLong(stream);
        this.score = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messageActionGameScore#92a72876";
    }

}