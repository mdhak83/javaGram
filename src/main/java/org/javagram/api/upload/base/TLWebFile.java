package org.javagram.api.upload.base;

import org.javagram.api.storage.base.file.TLAbsFileType;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLWebFile extends TLObject {
    public static final int CLASS_ID = 0x21e753bc;

    private int size;
    private String mimeType;
    private TLAbsFileType fileType;
    private int mTime;
    private TLBytes bytes;

    public TLWebFile() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getSize() {
        return size;
    }

    public String getMimeType() {
        return mimeType;
    }

    public TLAbsFileType getFileType() {
        return fileType;
    }

    public int getmTime() {
        return mTime;
    }

    public TLBytes getBytes() {
        return bytes;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(size, stream);
        StreamingUtils.writeTLString(mimeType, stream);
        StreamingUtils.writeTLObject(fileType, stream);
        StreamingUtils.writeInt(mTime, stream);
        StreamingUtils.writeTLBytes(bytes, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        size = StreamingUtils.readInt(stream);
        mimeType = StreamingUtils.readTLString(stream);
        fileType = StreamingUtils.readTLObject(stream, context, TLAbsFileType.class);
        mTime = StreamingUtils.readInt(stream);
        bytes = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "upload.webFile#21e753bc";
    }

}
