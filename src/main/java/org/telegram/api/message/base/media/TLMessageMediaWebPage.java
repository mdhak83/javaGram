package org.telegram.api.message.base.media;

import org.telegram.api.webpage.base.TLAbsWebPage;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Preview of webpage
 * messageMediaWebPage#a32dd600 webpage:WebPage = MessageMedia;
 */
public class TLMessageMediaWebPage extends TLAbsMessageMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa32dd600;

    private TLAbsWebPage webPage;

    public TLMessageMediaWebPage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsWebPage getWebPage() {
        return this.webPage;
    }

    public void setWebPage(TLAbsWebPage webPage) {
        this.webPage = webPage;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.webPage, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.webPage = StreamingUtils.readTLObject(stream, context, TLAbsWebPage.class);
    }

    @Override
    public String toString() {
        return "messageMediaDocument#a32dd600";
    }

}