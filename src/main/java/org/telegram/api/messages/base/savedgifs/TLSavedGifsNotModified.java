package org.telegram.api.messages.base.savedgifs;

import org.telegram.api._primitives.TLObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
  * @since 13/FEB/2016
 */
public class TLSavedGifsNotModified extends TLObject {
    public static final int CLASS_ID = 0xe8025ca2;

    public TLSavedGifsNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "savedgifsnotmodified#e8025ca2";
    }

}
