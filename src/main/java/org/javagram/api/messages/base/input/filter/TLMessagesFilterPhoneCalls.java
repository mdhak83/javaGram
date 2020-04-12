package org.javagram.api.messages.base.input.filter;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Return only phone calls
 * inputMessagesFilterPhoneCalls#80c99768 flags:# missed:flags.0?true = MessagesFilter;
 */
public class TLMessagesFilterPhoneCalls extends TLAbsMessagesFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x80c99768;

    private static final int FLAG_MISSED    = 0x00000001; // 0 : Return only missed phone calls

    public TLMessagesFilterPhoneCalls() {
        super();
    }

    private void setOnlyMissed(boolean value) {
        this.setFlag(FLAG_MISSED, value);
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "inputMessagesFilterPhoneCalls#80c99768";
    }
    
}