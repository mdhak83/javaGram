package org.telegram.api.auth.base.urlauthresult;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.user.base.TLAbsUser;

/**
 * Details about the authorization request, for more info @see <a href="https://core.telegram.org/api/url-authorization">click here »</a>
 * urlAuthResultRequest#92d33a0e flags:# request_write_access:flags.0?true bot:User domain:string = UrlAuthResult;
 */
public class TLUrlAuthResultRequest extends TLAbsUrlAuthResult {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x92d33a0e;

    private static final int FLAG_REQUEST_WRITE_ACCESS  = 0x00000001; // 0 : Whether the bot would like to send messages to the user.

    /**
     * Username of a bot, which will be used for user authorization.
     * If not specified, the current bot's username will be assumed.
     * The url's domain must be the same as the domain linked with the bot.
     * See @see <a href="https://core.telegram.org/widgets/login#linking-your-domain-to-the-bot">Linking your domain to the bot </a> for more details.
     */
    private TLAbsUser bot;

    /**
     * The domain name of the website on which the user will log in.
     */
    private String domain;
    
    public TLUrlAuthResultRequest() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isRequestWriteAccess() {
        return this.isFlagSet(FLAG_REQUEST_WRITE_ACCESS);
    }
    
    public void setRequestWriteAccess(boolean value) {
        this.setFlag(FLAG_REQUEST_WRITE_ACCESS, value);
    }
    
    public void setBot(TLAbsUser bot) {
        this.bot = bot;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.bot, stream);
        StreamingUtils.writeTLString(this.domain, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.bot = StreamingUtils.readTLObject(stream, context, TLAbsUser.class);
        this.domain = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "urlAuthResultRequest#92d33a0e";
    }

}