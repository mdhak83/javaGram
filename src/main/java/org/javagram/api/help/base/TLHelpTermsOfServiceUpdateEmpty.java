package org.javagram.api.help.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * No changes were made to telegram's terms of service
 * help.termsOfServiceUpdateEmpty#e3309f7f expires:int = help.TermsOfServiceUpdate;
 */
public class TLHelpTermsOfServiceUpdateEmpty extends TLAbsHelpTermsOfServiceUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe3309f7f;

    /**
     * New TOS updates will have to be queried using @see <a href="https://core.telegram.org/method/help.getTermsOfServiceUpdate">help.getTermsOfServiceUpdate</a> in <code>expires</code> seconds
     */
    private int expires;

    public TLHelpTermsOfServiceUpdateEmpty() {
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
        return "help.termsOfServiceUpdateEmpty#e3309f7f";
    }

}