package org.javagram.api.update.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.lang.base.TLLangPackDifference;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;

/**
 * Language pack updated
 * updateLangPack#56022f4d difference:LangPackDifference = Update;
 */
public class TLUpdateLangPack extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x56022f4d;

    /**
     * Changed strings
     */
    private TLLangPackDifference difference;

    public TLUpdateLangPack() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLLangPackDifference getDifference() {
        return difference;
    }

    public void setDifference(TLLangPackDifference difference) {
        this.difference = difference;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.difference, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.difference = StreamingUtils.readTLObject(stream, context, TLLangPackDifference.class);
    }

    @Override
    public String toString() {
        return "updateLangPack#56022f4d";
    }

}