package org.javagram.api.sticker.base.stickersetcovered;

import org.javagram.api.document.base.TLAbsDocument;
import org.javagram.api.sticker.base.set.TLStickerSet;
import org.javagram.api._primitives.TLObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 *  * @since 07/AUG/2016
 */
public abstract class TLAbsStickerSetCovered extends TLObject {
    protected TLStickerSet set;
    protected TLAbsDocument cover;

    public TLStickerSet getSet() {
        return set;
    }

    public TLAbsDocument getCover() {
        return cover;
    }

}
