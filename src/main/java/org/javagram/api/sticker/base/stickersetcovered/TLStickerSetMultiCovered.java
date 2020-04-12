package org.javagram.api.sticker.base.stickersetcovered;

import org.javagram.api.document.base.TLAbsDocument;
import org.javagram.api.sticker.base.set.TLStickerSet;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 *  * @since 07/AUG/2016
 */
public class TLStickerSetMultiCovered extends TLAbsStickerSetCovered {
    public static final int CLASS_ID = 0x3407e51b;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(set, stream);
        StreamingUtils.writeTLObject(cover, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        set = StreamingUtils.readTLObject(stream, context, TLStickerSet.class);
        cover = StreamingUtils.readTLObject(stream, context, TLAbsDocument.class);
    }

    @Override
    public String toString() {
        return "stickerSetMultiCovered#3407e51b";
    }

}
