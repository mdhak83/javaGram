package org.javagram.api.sticker.base.input.set;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Stickerset by short name, from <code>tg://addstickers?set=short_name</code>
 * inputStickerSetShortName#861cc8a0 short_name:string = InputStickerSet;
 */
public class TLInputStickerSetShortName extends TLAbsInputStickerSet {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x861cc8a0;

    /**
     * From <code>tg://addstickers?set=short_name</code>
     */
    private String shortName;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.shortName, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.shortName = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "inputStickerSetShortName#861cc8a0";
    }

}