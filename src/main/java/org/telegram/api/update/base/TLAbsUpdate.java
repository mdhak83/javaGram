package org.telegram.api.update.base;

import org.telegram.api._primitives.TLObject;

/**
 * The type TL abs update.
 */
public abstract class TLAbsUpdate extends TLObject {

    protected TLAbsUpdate() {
        super();
    }

    public int getPts() {
        return 0;
    }

    public int getPtsCount() {
        return 0;
    }

}