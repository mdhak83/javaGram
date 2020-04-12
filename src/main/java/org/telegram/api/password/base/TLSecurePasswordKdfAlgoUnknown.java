package org.telegram.api.password.base;

import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Webpage attributes
 */
public class TLSecurePasswordKdfAlgoUnknown extends TLAbsSecurePasswordKdfAlgo {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4a8537;

    public TLSecurePasswordKdfAlgoUnknown() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
    }

    @Override
    public String toString() {
        return "securePasswordKdfAlgoUnknown#4a8537";
    }

}