package org.telegram.api.account.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLObject;

/**
 * The sent email code
 * account.sentEmailCode#811f854f email_pattern:string length:int = account.SentEmailCode;
 */
public class TLAccountSentEmailCode extends TLObject {

    /**
     * The constant CLASS_ID of this class.
     */
    public static final int CLASS_ID = 0x811f854f;

    /**
     * The email (to which the code was sent) must match this @see <a href="https://core.telegram.org/api/pattern">pattern</a>
     */
    private String emailPattern;
    
    /**
     * The length of the verification code
     */
    private int length;

    public TLAccountSentEmailCode() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getEmailPattern() {
        return emailPattern;
    }

    public void setEmailPattern(String emailPattern) {
        this.emailPattern = emailPattern;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.emailPattern, stream);
        StreamingUtils.writeInt(this.length, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.emailPattern = StreamingUtils.readTLString(stream);
        this.length = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "account.sentEmailCode#811f854f";
    }

}