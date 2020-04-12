package org.javagram.api.password.base;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;
import org.javagram.utils.StreamingUtils;

/**
 * passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow
 * This key derivation algorithm defines that @see <a href="https://core.telegram.org/api/srp">SRP 2FA</a> login must be used
 * passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow#3a912d4a salt1:bytes salt2:bytes g:int p:bytes = PasswordKdfAlgo;
 */
public class TLPasswordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow extends TLAbsPasswordKdfAlgo {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3a912d4a;

    /**
     * One of two salts used by the derivation function (@see <a href="https://core.telegram.org/api/srp">SRP 2FA login</a>)
     */
    private TLBytes salt1;

    /**
     * One of two salts used by the derivation function (@see <a href="https://core.telegram.org/api/srp">SRP 2FA login</a>)
     */
    private TLBytes salt2;
    
    /**
     * 	Base (@see <a href="https://core.telegram.org/api/srp">SRP 2FA login</a>)
     */
    private int g;

    /**
     * 	2048-bit modulus (@see <a href="https://core.telegram.org/api/srp">SRP 2FA login</a>)
     */
    private TLBytes p;

    public TLPasswordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLBytes getSalt1() {
        return salt1;
    }

    public void setSalt1(TLBytes salt1) {
        this.salt1 = salt1;
    }

    public TLBytes getSalt2() {
        return salt2;
    }

    public void setSalt2(TLBytes salt2) {
        this.salt2 = salt2;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public TLBytes getP() {
        return p;
    }

    public void setP(TLBytes p) {
        this.p = p;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBytes(this.salt1, stream);
        StreamingUtils.writeTLBytes(this.salt2, stream);
        StreamingUtils.writeInt(this.g, stream);
        StreamingUtils.writeTLBytes(this.p, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.salt1 = StreamingUtils.readTLBytes(stream, context);
        this.salt2 = StreamingUtils.readTLBytes(stream, context);
        this.g = StreamingUtils.readInt(stream);
        this.p = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow#3a912d4a";
    }

}