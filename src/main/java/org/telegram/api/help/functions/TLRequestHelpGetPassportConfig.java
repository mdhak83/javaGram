package org.telegram.api.help.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.help.base.TLAbsHelpPassportConfig;

/**
 * Get @see <a href="https://core.telegram.org/passport">passport</a> configuration
 * help.getPassportConfig#c661ad08 hash:int = help.PassportConfig;
 */
public class TLRequestHelpGetPassportConfig extends TLMethod<TLAbsHelpPassportConfig> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc661ad08;
    
    /**
     * @see <a href="https://core.telegram.org/api/offsets#hash-generation">Hash for pagination, for more info click here</a>
     */
    private int hash;
    
    public TLRequestHelpGetPassportConfig() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.hash = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsHelpPassportConfig deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsHelpPassportConfig) {
            return (TLAbsHelpPassportConfig) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.help.TLAbsAppUpdate, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "help.getPassportConfig#c661ad08";
    }

}