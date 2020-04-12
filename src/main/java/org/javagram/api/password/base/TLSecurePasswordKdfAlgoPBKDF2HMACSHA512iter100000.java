package org.javagram.api.password.base;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;
import org.javagram.utils.StreamingUtils;

/**
 * securePasswordKdfAlgoPBKDF2HMACSHA512iter100000
 * PBKDF2 with SHA512 and 100000 iterations KDF algo
 * securePasswordKdfAlgoPBKDF2HMACSHA512iter100000#bbf2dda0 salt:bytes = SecurePasswordKdfAlgo;
 */
public class TLSecurePasswordKdfAlgoPBKDF2HMACSHA512iter100000 extends TLAbsSecurePasswordKdfAlgo {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbbf2dda0;

    /**
     * Salt
     */
    private TLBytes salt;

    public TLSecurePasswordKdfAlgoPBKDF2HMACSHA512iter100000() {
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
        return "securePasswordKdfAlgoPBKDF2HMACSHA512iter100000#bbf2dda0";
    }

}