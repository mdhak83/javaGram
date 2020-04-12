package org.javagram.api.input.media;

import org.javagram.api.game.base.input.TLAbsInputGame;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input media photo.
 */
public class TLInputMediaGame extends TLAbsInputMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd33f43f3;

    private TLAbsInputGame id;

    public TLInputMediaGame() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputGame getId() {
        return id;
    }

    public void setId(TLAbsInputGame id) {
        this.id = id;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        id = StreamingUtils.readTLObject(stream, context, TLAbsInputGame.class);
    }

    @Override
    public String toString() {
        return "inputMediaGame#d33f43f3";
    }

}