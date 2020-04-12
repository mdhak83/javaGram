package org.telegram.api.message.base.media;

import org.telegram.api.document.base.TLAbsDocument;
import org.telegram.api.photo.base.TLAbsPhoto;
import org.telegram.api._primitives.TLObject;

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