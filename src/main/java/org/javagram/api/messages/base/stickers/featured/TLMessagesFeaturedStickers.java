package org.javagram.api.messages.base.stickers.featured;

import org.javagram.api.sticker.base.stickersetcovered.TLAbsStickerSetCovered;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLLongVector;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 *  * @since 07/AUG/2016
 */
public class TLMessagesFeaturedStickers extends TLAbsMessagesFeaturedStickers {
    public static final int CLASS_ID = 0xf89d88e5;

    private int hash;
    private TLVector<TLAbsStickerSetCovered> sets;
    private TLLongVector unread;

    public TLMessagesFeaturedStickers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getHash() {
        return hash;
    }

    public TLVector<TLAbsStickerSetCovered> getSets() {
        return sets;
    }

    public TLLongVector getUnread() {
        return unread;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(hash, stream);
        StreamingUtils.writeTLVector(sets, stream);
        StreamingUtils.writeTLVector(unread, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        hash = StreamingUtils.readInt(stream);
        sets = StreamingUtils.readTLVector(stream, context, TLAbsStickerSetCovered.class);
        unread = StreamingUtils.readTLLongVector(stream, context);
    }

    @Override
    public String toString() {
        return "messages.featuredStickers#f89d88e5";
    }

}
