package org.telegram.api.update.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLContext;

/**
 * The list of favorited stickers was changed, the client should call @see <a href="https://core.telegram.org/method/messages.getFavedStickers">messages.getFavedStickers</a> to refetch the new list
 * updateFavedStickers#e511996d = Update;
 */
public class TLUpdateFavedSticker extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe511996d;

    public TLUpdateFavedSticker() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
    }

    @Override
    public String toString() {
        return "updateFavedStickers#e511996d";
    }

}