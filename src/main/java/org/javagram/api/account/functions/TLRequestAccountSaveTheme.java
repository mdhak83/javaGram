package org.javagram.api.account.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.theme.base.input.TLAbsInputTheme;

/**
 * Save a theme
 * account.saveTheme#f257106c theme:InputTheme unsave:Bool = Bool;
 */
public class TLRequestAccountSaveTheme extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf257106c;

    /**
     * Theme to save
     */
    private TLAbsInputTheme theme;
    
    /**
     * Unsav
     */
    private boolean unsave;

    public TLRequestAccountSaveTheme() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputTheme getTheme() {
        return theme;
    }

    public void setTheme(TLAbsInputTheme theme) {
        this.theme = theme;
    }

    public boolean isUnsave() {
        return unsave;
    }

    public void setUnsave(boolean unsave) {
        this.unsave = unsave;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.theme, stream);
        StreamingUtils.writeTLBool(this.unsave, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.theme = StreamingUtils.readTLObject(stream, context, TLAbsInputTheme.class);
        this.unsave = StreamingUtils.readTLBool(stream);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.saveTheme#f257106c";
    }

}