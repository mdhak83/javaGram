package org.javagram.api.help.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.json.base.TLDataJSON;

/**
 * Telegram @see <a href="https://core.telegram.org/passport">passport</a> configuration
 * help.passportConfig#a098d6af hash:int countries_langs:DataJSON = help.PassportConfig;
 */
public class TLHelpPassportConfig extends TLAbsHelpPassportConfig {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa098d6af;

    /**
     * Hash for pagination, for more info click here
     */
    private int hash;
    
    /**
     * Localization
     */
    private TLDataJSON countriesLang;
    
    public TLHelpPassportConfig() {
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

    public TLDataJSON getCountriesLang() {
        return countriesLang;
    }

    public void setCountriesLang(TLDataJSON countriesLang) {
        this.countriesLang = countriesLang;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.hash, stream);
        StreamingUtils.writeTLObject(this.countriesLang, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.hash = StreamingUtils.readInt(stream);
        this.countriesLang = StreamingUtils.readTLObject(stream, context, TLDataJSON.class);
    }

    @Override
    public String toString() {
        return "help.passportConfig#a098d6af";
    }

}