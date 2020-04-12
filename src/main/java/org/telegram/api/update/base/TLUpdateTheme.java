package org.telegram.api.update.base;

import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.theme.base.TLTheme;
import org.telegram.utils.StreamingUtils;

/**
 * A cloud theme was updated
 * updateTheme#8216fba3 theme:Theme = Update;
 */
public class TLUpdateTheme extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8216fba3;

    /**
     * Theme
     */
    private TLTheme theme;

    public TLUpdateTheme() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLTheme getTheme() {
        return theme;
    }

    public void setTheme(TLTheme theme) {
        this.theme = theme;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.theme, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.theme = StreamingUtils.readTLObject(stream, context, TLTheme.class);
    }

    @Override
    public String toString() {
        return "updateTheme#8216fba3";
    }

}