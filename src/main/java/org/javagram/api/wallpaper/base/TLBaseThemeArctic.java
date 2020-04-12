package org.javagram.api.wallpaper.base;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Webpage attributes
 */
public class TLBaseThemeArctic extends TLAbsBaseTheme {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5b11125a;

    public TLBaseThemeArctic() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
    }

    @Override
    public String toString() {
        return "baseThemeArctic#5b11125a";
    }

}