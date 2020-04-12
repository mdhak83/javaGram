package org.telegram.api.wallpaper.base.input;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Wallpaper with no file
 * inputWallPaperNoFile#8427bbac = InputWallPaper;
 */
public class TLInputWallPaperNoFile extends TLAbsInputWallPaper {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8427bbac;

    public TLInputWallPaperNoFile() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputWallPaperNoFile#8427bbac";
    }

}