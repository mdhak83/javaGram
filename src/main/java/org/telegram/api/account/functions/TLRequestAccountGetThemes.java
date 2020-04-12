package org.telegram.api.account.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.account.base.TLAbsAccountThemes;

/**
 * Get installed themes
 * account.getThemes#285946f8 format:string hash:int = account.Themes;
 */
public class TLRequestAccountGetThemes extends TLMethod<TLAbsAccountThemes> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x285946f8;

    /**
     * Theme format, a string that identifies the theming engines supported by the client
     */
    private String format;
    
    /**
     * @see <a href="https://core.telegram.org/api/offsets#hash-generation">Hash for pagination, for more info click here</a>
     */
    private int hash;
    
    public TLRequestAccountGetThemes() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.format, stream);
        StreamingUtils.writeInt(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.format = StreamingUtils.readTLString(stream);
        this.hash = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsAccountThemes deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsAccountThemes) {
            return (TLAbsAccountThemes) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.peer.notify.settings.TLAbsPeerNotifySettings, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.getThemes#285946f8";
    }

}