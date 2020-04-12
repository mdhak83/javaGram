package org.telegram.api.document.base.attribute;

import org.telegram.api._primitives.TLObject;

/**
 * DocumentAttribute
 * Various possible attributes of a document (used to define if it's a sticker, a GIF, a video, a mask sticker, an image, an audio, and so on)
 */
public abstract class TLAbsDocumentAttribute extends TLObject {

    protected TLAbsDocumentAttribute() {
        super();
    }

}