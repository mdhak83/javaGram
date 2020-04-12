package org.javagram.api.messages.base.dhconfig;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL dh config.
 */
public class TLDhConfig extends TLAbsDhConfig {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2c221edd;

    public TLDhConfig() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.g, stream);
        StreamingUtils.writeTLBytes(this.p, stream);
        StreamingUtils.writeInt(this.version, stream);
        StreamingUtils.writeTLBytes(this.random, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.g = StreamingUtils.readInt(stream);
        this.p = StreamingUtils.readTLBytes(stream, context);
        this.version = StreamingUtils.readInt(stream);
        this.random = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "messages.dhConfig#2c221edd";
    }

}