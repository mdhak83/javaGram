package org.telegram.api.dialog.base;

import org.telegram.api._primitives.TLObject;

/**
 * The Dialog type.
 */
public abstract class TLAbsDialog extends TLObject {

    private static final int FLAG_PINNED    = 0x00000004; // 2

    protected TLAbsDialog() {
        super();
    }

    public boolean isPinned() {
        return this.isFlagSet(FLAG_PINNED);
    }
    
    public void setPinned(boolean value) {
        this.setFlag(FLAG_PINNED, value);
    }

}