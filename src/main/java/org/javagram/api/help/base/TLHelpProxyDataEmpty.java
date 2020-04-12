package org.javagram.api.help.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * No proxy was used to connect to tg (or none was provided to @see <a href="https://core.telegram.org/method/initConnection">initConnection</a>, or the used proxy doesn't have a promotion channel associated to it)
 * help.proxyDataEmpty#e09e1fb8 expires:int = help.ProxyData;
 */
public class TLHelpProxyDataEmpty extends TLAbsHelpProxyData {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe09e1fb8;

    /**
     * Expiration date of proxy info, will have to be refetched in <code>expires</code> seconds
     */
    private int expires;

    public TLHelpProxyDataEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.expires, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.expires = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "help.proxyDataEmpty#e09e1fb8";
    }

}