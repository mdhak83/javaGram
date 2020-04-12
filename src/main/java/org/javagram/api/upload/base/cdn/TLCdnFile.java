package org.javagram.api.upload.base.cdn;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLCdnFile extends TLAbsCdnFile {
    public static final int CLASS_ID = 0xa99fca4f;

    private TLBytes bytes;

    public TLCdnFile() {
        super();
    }

    public TLBytes getBytes() {
        return bytes;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBytes(bytes, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        bytes = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "upload.cdnFile#a99fca4f";
    }

}
