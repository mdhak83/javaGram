package org.javagram.api.messages.base;

import org.javagram.api.sticker.base.stickersetcovered.TLAbsStickerSetCovered;
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
 *  * @since 07/AUG/2016
 */
public class TLMessagesArchivedStickers extends TLObject {
    public static final int CLASS_ID = 0x4fcba9c8;

    private int count;
    private TLVector<TLAbsStickerSetCovered> sets;

    public TLMessagesArchivedStickers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getCount() {
        return count;
    }

    public TLVector<TLAbsStickerSetCovered> getSets() {
        return sets;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(count, stream);
        StreamingUtils.writeTLVector(sets, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        count = StreamingUtils.readInt(stream);
        sets = StreamingUtils.readTLVector(stream, context, TLAbsStickerSetCovered.class);
    }

    @Override
    public String toString() {
        return "messages.archivedStickers#4fcba9c8";
    }

}