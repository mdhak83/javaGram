package org.javagram.api.wallpaper.base;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Webpage attributes
 */
public class TLBaseThemeClassic extends TLAbsBaseTheme {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc3a12462;

    public TLBaseThemeClassic() {
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
        return "baseThemeClassic#c3a12462";
    }

}