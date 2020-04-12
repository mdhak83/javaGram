package org.javagram.api;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * URL with chat statistics
 * statsURL#47a971e0 url:string = StatsURL;
 */
public class TLStatsURL extends TLObject {
    
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x47a971e0;

    /**
     * Chat statistics
     */
    private String url;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.url, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.url = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "statsURL#47a971e0";
    }

}