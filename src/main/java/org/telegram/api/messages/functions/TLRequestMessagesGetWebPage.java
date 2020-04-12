package org.telegram.api.messages.functions;

import org.telegram.api.webpage.base.TLAbsWebPage;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLRequestMessagesGetWebPage extends TLMethod<TLAbsWebPage> {
    public static final int CLASS_ID = 0x32ca8f91;

    private String url;
    private int hash;

    public TLRequestMessagesGetWebPage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAbsWebPage deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if (res instanceof TLAbsWebPage)
            return (TLAbsWebPage) res;
        throw new IOException("Incorrect response type. Expected " + TLAbsWebPage.class.getCanonicalName()
                + ", got: " + res.getClass().getCanonicalName());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.url, stream);
        StreamingUtils.writeInt(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.url = StreamingUtils.readTLString(stream);
        this.hash = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messages.getWebPage#32ca8f91";
    }

}