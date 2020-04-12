package org.telegram.api.password.base;

import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLBytes;
import org.telegram.utils.StreamingUtils;

/**
 * securePasswordKdfAlgoSHA512
 * SHA512 KDF algo
 * securePasswordKdfAlgoSHA512#86471d92 salt:bytes = SecurePasswordKdfAlgo;
 */
public class TLSecurePasswordKdfAlgoSHA512 extends TLAbsSecurePasswordKdfAlgo {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x86471d92;

    /**
     * Salt
     */
    private TLBytes salt;

    public TLSecurePasswordKdfAlgoSHA512() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLBytes getSalt() {
        return salt;
    }

    public void setSalt(TLBytes salt) {
        this.salt = salt;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBytes(this.salt, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.salt = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "securePasswordKdfAlgoSHA512#86471d92";
    }

}