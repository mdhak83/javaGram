package org.javagram.api.message.base.media;

/**
 * Current version of the client does not support this media type.
 * messageMediaUnsupported#9f84f49e = MessageMedia;
 */
public class TLMessageMediaUnsupported extends TLAbsMessageMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9f84f49e;

    public TLMessageMediaUnsupported() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messageMediaUnsupported#9f84f49e";
    }

}