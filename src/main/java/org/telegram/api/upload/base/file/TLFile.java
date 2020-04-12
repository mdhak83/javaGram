package org.telegram.api.upload.base.file;

import org.telegram.api.storage.base.file.TLAbsFileType;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL file.
 */
public class TLFile extends TLAbsFile {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x96a18d5;

    private TLAbsFileType type;
    private int mtime;
    private TLBytes bytes;

    public TLFile() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public TLAbsFileType getType() {
        return this.type;
    }

    /**
     * Sets type.
     *
     * @param value the value
     */
    public void setType(TLAbsFileType value) {
        this.type = value;
    }

    /**
     * Gets mtime.
     *
     * @return the mtime
     */
    public int getMtime() {
        return this.mtime;
    }

    /**
     * Sets mtime.
     *
     * @param value the value
     */
    public void setMtime(int value) {
        this.mtime = value;
    }

    /**
     * Gets bytes.
     *
     * @return the bytes
     */
    public TLBytes getBytes() {
        return this.bytes;
    }

    /**
     * Sets bytes.
     *
     * @param value the value
     */
    public void setBytes(TLBytes value) {
        this.bytes = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.type, stream);
        StreamingUtils.writeInt(this.mtime, stream);
        StreamingUtils.writeTLBytes(this.bytes, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.type = StreamingUtils.readTLObject(stream, context, TLAbsFileType.class);
        this.mtime = StreamingUtils.readInt(stream);
        this.bytes = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "upload.file#96a18d5";
    }

}