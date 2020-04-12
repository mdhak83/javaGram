package org.telegram.api.message.base.media;

/**
 * Empty constructor.
 * messageMediaEmpty#3ded6320 = MessageMedia;
 */
public class TLMessageMediaEmpty extends TLAbsMessageMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3ded6320;

    public TLMessageMediaEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messageMediaEmpty#3ded6320";
    }

}