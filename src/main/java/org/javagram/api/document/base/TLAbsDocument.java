package org.javagram.api.document.base;

import org.javagram.api._primitives.TLObject;

public abstract class TLAbsDocument extends TLObject {

    /**
     * Document ID or <code>0</code>
     */
    protected long id;

    public TLAbsDocument() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}