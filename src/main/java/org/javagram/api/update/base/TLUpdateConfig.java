package org.javagram.api.update.base;

/**
 * The type TL update channel new message
 */
public class TLUpdateConfig extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa229dd06;

    public TLUpdateConfig() {
        super();
    }
    

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "updateConfig#a229dd06";
    }

}