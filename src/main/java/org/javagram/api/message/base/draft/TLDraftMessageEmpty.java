package org.javagram.api.message.base.draft;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;

/**
 * draftMessageEmpty
 * Empty draft
 * draftMessageEmpty#1b0c841a flags:# date:flags.0?int = DraftMessage;
 */
public class TLDraftMessageEmpty extends TLAbsDraftMessage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1b0c841a;

    private static final int FLAG_DATE  = 0x00000001; // 0 : When was the draft last updated
    
    /**
     * When was the draft last updated
     */
    private int date;
    
    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean hasDate() {
        return this.isFlagSet(FLAG_DATE);
    }
    
    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
        this.setFlag(FLAG_DATE, true);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if (this.hasDate()) {
            StreamingUtils.writeInt(this.date, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.hasDate()) {
            this.date = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "draftMessageEmpty#1b0c841a";
    }

}