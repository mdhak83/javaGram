package org.javagram.api.page.base.block;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.page.base.TLPageCaption;

/**
 * Audio
 */
public class TLPageBlockAudio extends TLAbsPageBlock {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x804361ea;

    /**
     * Audio ID (to be fetched from the container @see <a href="https://core.telegram.org/constructor/page">page</a> constructor
     */
    private long audioId;
    
    /**
     * Caption
     */
    private TLPageCaption caption;

    public TLPageBlockAudio() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getAudioId() {
        return audioId;
    }

    public void setAudioId(long audioId) {
        this.audioId = audioId;
    }

    public TLPageCaption getCaption() {
        return caption;
    }

    public void setCaption(TLPageCaption caption) {
        this.caption = caption;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.audioId, stream);
        StreamingUtils.writeTLObject(this.caption, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.audioId = StreamingUtils.readLong(stream);
        this.caption = StreamingUtils.readTLObject(stream, context, TLPageCaption.class);
    }

    @Override
    public String toString() {
        return "pageBlockAudio#804361ea";
    }

}