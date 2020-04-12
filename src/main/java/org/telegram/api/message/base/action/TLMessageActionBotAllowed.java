package org.telegram.api.message.base.action;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The domain name of the website on which the user has logged in. @see <a href="https://core.telegram.org/widgets/login">More about Telegram Login »</a>
 * messageActionBotAllowed#abe9affe domain:string = MessageAction;
 */
public class TLMessageActionBotAllowed extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xabe9affe;

    /**
     * The domain name of the website on which the user has logged in
     */
    private String domain;

    public TLMessageActionBotAllowed() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.domain, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.domain = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "messageActionBotAllowed#abe9affe";
    }

}