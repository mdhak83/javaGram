package org.telegram.api.messages.base.stickers.setintallresult;

import org.telegram.api.sticker.base.stickersetcovered.TLAbsStickerSetCovered;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 *  @since 08/AUG/2016
 */
public class TLMessagesStickerSetInstallResultArchive extends TLAbsMessagesStickerSetInstallResult {
    public static final int CLASS_ID = 0x35e410a8;

    private TLVector<TLAbsStickerSetCovered> sets;

    public TLMessagesStickerSetInstallResultArchive() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsStickerSetCovered> getSets() {
        return sets;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(sets, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        sets = StreamingUtils.readTLVector(stream, context, TLAbsStickerSetCovered.class);
    }

    @Override
    public String toString() {
        return "messages.stickerSetInstallResultArchive#35e410a8";
    }

}
