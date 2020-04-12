package org.javagram.api.message.base.media;

import org.javagram.api.document.base.TLAbsDocument;
import org.javagram.api.photo.base.TLAbsPhoto;
import org.javagram.api._primitives.TLObject;

/**
 * The MessageMedia type
 */
public abstract class TLAbsMessageMedia extends TLObject {

    protected TLAbsMessageMedia() {
        super();
    }
    
    public TLAbsPhoto getAbsPhoto() {
        return null;
    }
    
    public TLAbsDocument getAbsDocument() {
        return null;
    }
    
    public String toLog() {
        return this.getClass().getSimpleName().replace("TLMessageMedia", "");
    }
    
}