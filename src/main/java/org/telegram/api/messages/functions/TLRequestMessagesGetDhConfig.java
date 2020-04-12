package org.telegram.api.messages.functions;

import org.telegram.api.messages.base.dhconfig.TLAbsDhConfig;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Returns configuration parameters for Diffie-Hellman key generation. Can also return a random sequence of bytes of required length.
 * messages.getDhConfig#26cf8950 version:int random_length:int = messages.DhConfig;
 */
public class TLRequestMessagesGetDhConfig extends TLMethod<TLAbsDhConfig> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x26cf8950;

    /**
     * Value of the <strong>version</strong> parameter from @see <a href="https://core.telegram.org/constructor/messages.dhConfig">messages.dhConfig</a>, available at the client
     */
    private int version;
    
    /**
     * Length of the required random sequence
     */
    private int randomLength;

    public TLRequestMessagesGetDhConfig() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int value) {
        this.version = value;
    }

    public int getRandomLength() {
        return this.randomLength;
    }

    public void setRandomLength(int value) {
        this.randomLength = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.version, stream);
        StreamingUtils.writeInt(this.randomLength, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.version = StreamingUtils.readInt(stream);
        this.randomLength = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsDhConfig deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsDhConfig) {
            return (TLAbsDhConfig) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.messages.dhconfig.TLAbsDhConfig, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getDhConfig#26cf8950";
    }

}