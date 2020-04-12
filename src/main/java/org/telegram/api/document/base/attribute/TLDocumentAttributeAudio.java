package org.telegram.api.document.base.attribute;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * documentAttributeAudio
 * Represents an audio file
 * documentAttributeAudio#9852f9c6 flags:# voice:flags.10?true duration:int title:flags.0?string performer:flags.1?string waveform:flags.2?bytes = DocumentAttribute;
 */
public class TLDocumentAttributeAudio extends TLAbsDocumentAttribute {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9852f9c6;

    private static final int FLAG_TITLE         = 0x00000001; //  0 : Name of song
    private static final int FLAG_PERFORMER     = 0x00000002; //  1 : Performer
    private static final int FLAG_WAVEFORM      = 0x00000004; //  2 : Waveform
    private static final int FLAG_VOICE         = 0x00000400; // 10 : Whether this is a voice message

    /**
     * Duration in seconds
     */
    private int duration;

    /**
     * Name of song
     */
    private String title;

    /**
     * Performer
     */
    private String performer;

    /**
     * Waveform
     */
    private TLBytes waveform;

    public TLDocumentAttributeAudio() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isVoice() {
        return this.isFlagSet(FLAG_VOICE);
    }

    public void setVoice(boolean value) {
        this.setFlag(FLAG_VOICE, value);
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean hasTitle() {
        return this.isFlagSet(FLAG_TITLE);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.setFlag(FLAG_TITLE, this.title != null && !this.title.trim().isEmpty());
    }

    public boolean hasPerformer() {
        return this.isFlagSet(FLAG_PERFORMER);
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
        this.setFlag(FLAG_PERFORMER, this.performer != null && !this.performer.trim().isEmpty());
    }

    public boolean hasWaveform() {
        return this.isFlagSet(FLAG_WAVEFORM);
    }

    public TLBytes getWaveform() {
        return waveform;
    }

    public void setWaveform(TLBytes waveform) {
        this.waveform = waveform;
        this.setFlag(FLAG_WAVEFORM, this.waveform != null && this.waveform.getLength() != 0);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.duration, stream);
        if (this.hasTitle()) {
            StreamingUtils.writeTLString(this.title, stream);
        }
        if (this.hasPerformer()) {
            StreamingUtils.writeTLString(this.performer, stream);
        }
        if (this.hasWaveform()) {
            StreamingUtils.writeTLBytes(this.waveform, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.duration = StreamingUtils.readInt(stream);
        if (this.hasTitle()) {
            this.title = StreamingUtils.readTLString(stream);
        }
        if (this.hasPerformer()) {
            this.performer = StreamingUtils.readTLString(stream);
        }
        if (this.hasWaveform()) {
            this.waveform = StreamingUtils.readTLBytes(stream, context);
        }
    }

    @Override
    public String toString() {
        return "documentAttributeAudio#9852f9c6";
    }

}