package org.javagram.api.message.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Link to a message in a supergroup/channel
 */
public class TLExportedMessageLink extends TLObject {

    public static final int CLASS_ID = 0x5dab1af4;

    private String link;
    private String html;

    public TLExportedMessageLink() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.link, stream);
        StreamingUtils.writeTLString(this.html, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.link = StreamingUtils.readTLString(stream);
        this.html = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "exportedMessageLink#5dab1af4";
    }

}
