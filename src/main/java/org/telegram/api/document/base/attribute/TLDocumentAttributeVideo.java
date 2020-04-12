package org.telegram.api.document.base.attribute;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * documentAttributeVideo
 * Defines a video
 * documentAttributeVideo#ef02ce6 flags:# round_message:flags.0?true supports_streaming:flags.1?true duration:int w:int h:int = DocumentAttribute;
 */
public class TLDocumentAttributeVideo extends TLAbsDocumentAttribute {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xef02ce6;

    private static final int FLAG_ROUND_MESSAGE         = 0x00000001; // 0 : Whether this is a round video
    private static final int FLAG_SUPPORTS_STREAMING    = 0x00000002; // 1 : Whether the video supports streaming

    /**
     * Duration in seconds
     */
    private int duration;

    /**
     * Video width
     */
    private int w;

    /**
     * Video height
     */
    private int h;

    public TLDocumentAttributeVideo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isRoundMessage() {
        return this.isFlagSet(FLAG_ROUND_MESSAGE);
    }

    public void setRoundMessage(boolean value) {
        this.setFlag(FLAG_ROUND_MESSAGE, value);
    }

    public boolean isSupportsStreaming() {
        return this.isFlagSet(FLAG_SUPPORTS_STREAMING);
    }

    public void setSupportsStreaming(boolean value) {
        this.setFlag(FLAG_SUPPORTS_STREAMING, value);
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getW() {
        return this.w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return this.h;
    }

    public void setH(int h) {
        this.h = h;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.duration, stream);
        StreamingUtils.writeInt(this.w, stream);
        StreamingUtils.writeInt(this.h, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.duration = StreamingUtils.readInt(stream);
        this.w = StreamingUtils.readInt(stream);
        this.h = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "documentAttributeVideo#ef02ce6";
    }

}