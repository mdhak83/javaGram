package org.javagram.api.auth.base.urlauthresult;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Details about an accepted authorization request, for more info @see <a href="https://core.telegram.org/api/url-authorization">click here »</a>
 * urlAuthResultAccepted#8f8c0e4e url:string = UrlAuthResult;
 */
public class TLUrlAuthResultAccepted extends TLAbsUrlAuthResult {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8f8c0e4e;

    /**
     * The URL name of the website on which the user has logged in.
     */
    private String url;
    
    public TLUrlAuthResultAccepted() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        return "urlAuthResultAccepted#8f8c0e4e";
    }

}