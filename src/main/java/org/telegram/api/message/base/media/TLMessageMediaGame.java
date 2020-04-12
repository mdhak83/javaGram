package org.telegram.api.message.base.media;

import org.telegram.api.game.base.TLGame;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Telegram game
 * messageMediaGame#fdb19008 game:Game = MessageMedia;
 */
public class TLMessageMediaGame extends TLAbsMessageMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfdb19008;

    /**
     * Game
     */
    private TLGame game;

    public TLMessageMediaGame() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLGame getGame() {
        return game;
    }
    
    public void setGame(TLGame game) {
        this.game = game;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(game, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.game = StreamingUtils.readTLObject(stream, context, TLGame.class);
    }

    @Override
    public String toString() {
        return "messageMediaGame#fdb19008";
    }
    
}