package org.telegram.api.foundgif.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
  * @since 13/FEB/2016
 */
public class TLFoundGif extends TLAbsFoundGif {
    public static final int CLASS_ID = 0x162ecc1f;

    private String url;
    private String thumbUrl;
    private String contentUrl;
    private String contentType;
    private int w;
    private int h;

    public TLFoundGif() {
        super();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(url, stream);
        StreamingUtils.writeTLString(thumbUrl, stream);
        StreamingUtils.writeTLString(contentUrl, stream);
        StreamingUtils.writeTLString(contentType, stream);
        StreamingUtils.writeInt(w, stream);
        StreamingUtils.writeInt(h, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.url = StreamingUtils.readTLString(stream);
        this.thumbUrl = StreamingUtils.readTLString(stream);
        this.contentUrl = StreamingUtils.readTLString(stream);
        this.contentType = StreamingUtils.readTLString(stream);
        this.w = StreamingUtils.readInt(stream);
        this.h = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "foundGif#162ecc1f";
    }

}
