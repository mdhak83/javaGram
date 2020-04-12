package org.telegram.api.account.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.theme.base.input.TLAbsInputTheme;
import org.telegram.api._primitives.TLBool;

/**
 * Install a theme
 * account.installTheme#7ae43737 flags:# dark:flags.0?true format:flags.1?string theme:flags.1?InputTheme = Bool;
 */
public class TLRequestAccountInstallTheme extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7ae43737;

    private static final int FLAG_DARK      = 0x00000001; // 0 : Whether to install the dark version
    private static final int FLAG_THEME     = 0x00000002; // 1 : tHEME ARGUMENTS

    /**
     * Theme format, a string that identifies the theming engines supported by the client
     */
    private String format;
    
    /**
     * Theme to install
     */
    private TLAbsInputTheme theme;

    public TLRequestAccountInstallTheme() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public boolean isDark() {
        return this.isFlagSet(FLAG_DARK);
    }
    
    public void setDark(boolean value) {
        this.setFlag(FLAG_DARK, value);
    }

    public boolean hasTheme() {
        return this.isFlagSet(FLAG_THEME);
    }
    
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
        this.setFlag(FLAG_THEME, format != null & !format.trim().isEmpty());
    }

    public TLAbsInputTheme getTheme() {
        return theme;
    }

    public void setTheme(TLAbsInputTheme theme) {
        this.theme = theme;
        this.setFlag(FLAG_THEME, format != null & !format.trim().isEmpty());
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if (this.hasTheme()) {
            StreamingUtils.writeTLString(this.format, stream);
            StreamingUtils.writeTLObject(this.theme, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.hasTheme()) {
            this.format = StreamingUtils.readTLString(stream);
            this.theme = StreamingUtils.readTLObject(stream, context, TLAbsInputTheme.class);
        }
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.peer.notify.settings.TLAbsPeerNotifySettings, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.installTheme#7ae43737";
    }

}