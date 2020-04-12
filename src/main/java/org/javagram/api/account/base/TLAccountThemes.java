package org.javagram.api.account.base;

import org.javagram.api.theme.base.TLTheme;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLVector;

/**
 * Installed themes
 * account.themes#7f676421 hash:int themes:Vector&lt;Theme&gt; = account.Themes;
 */
public class TLAccountThemes extends TLAbsAccountThemes {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7f676421;
    
    /**
     * @see <a href="https://core.telegram.org/api/offsets#hash-generation">Hash for pagination, for more info click here</a>
     */
    private int hash;
    
    /**
     * Themes
     */
    private TLVector<TLTheme> themes;
    
    public TLAccountThemes() {
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

    public TLVector<TLTheme> getThemes() {
        return themes;
    }

    public void setThemes(TLVector<TLTheme> themes) {
        this.themes = themes;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.hash, stream);
        StreamingUtils.writeTLVector(this.themes, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.hash = StreamingUtils.readInt(stream);
        this.themes = StreamingUtils.readTLVector(stream, context, TLTheme.class);
    }

    @Override
    public String toString() {
        return "account.themes#7f676421";
    }

}