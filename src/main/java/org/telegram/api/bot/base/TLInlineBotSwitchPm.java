package org.telegram.api.bot.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 *  * @since 09/APR/2016
 */
public class TLInlineBotSwitchPm extends TLObject {
    public static final int CLASS_ID = 0x3c20629f;

    private String text;
    private String startParam;

    public TLInlineBotSwitchPm() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getText() {
        return text;
    }

    public String getStartParam() {
        return startParam;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(text, stream);
        StreamingUtils.writeTLString(startParam, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        text = StreamingUtils.readTLString(stream);
        startParam = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "inlineBotSwitchPM#3c20629f";
    }

}
