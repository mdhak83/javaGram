package org.telegram.api.messages.base.dhconfig;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL dh config not modified.
 */
public class TLDhConfigNotModified extends TLAbsDhConfig {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc0e24635;

    public TLDhConfigNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBytes(this.random, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.random = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "messages.dhConfigNotModified#c0e24635";
    }

}