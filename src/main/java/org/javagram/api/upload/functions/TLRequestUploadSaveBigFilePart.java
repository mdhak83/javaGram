package org.javagram.api.upload.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request upload save big file part.
 */
public class TLRequestUploadSaveBigFilePart extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xde7b673d;

    private long fileId;
    private int filePart;
    private int fileTotalParts;
    private TLBytes bytes;

    public TLRequestUploadSaveBigFilePart() {
        super();
    }

    public TLRequestUploadSaveBigFilePart(long fileId, int filePart, int fileTotalParts, TLBytes bytes) {
        super();
        this.setFileId(fileId);
        this.setFilePart(filePart);
        this.setFileTotalParts(fileTotalParts);
        this.setBytes(bytes);
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if (res instanceof TLBool)
            return (TLBool) res;
        throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets file id.
     *
     * @return the file id
     */
    public long getFileId() {
        return this.fileId;
    }

    /**
     * Sets file id.
     *
     * @param value the value
     */
    public final void setFileId(long value) {
        this.fileId = value;
    }

    /**
     * Gets file part.
     *
     * @return the file part
     */
    public int getFilePart() {
        return this.filePart;
    }

    /**
     * Sets file part.
     *
     * @param value the value
     */
    public final void setFilePart(int value) {
        this.filePart = value;
    }

    /**
     * Gets file total parts.
     *
     * @return the file total parts
     */
    public int getFileTotalParts() {
        return this.fileTotalParts;
    }

    /**
     * Sets file total parts.
     *
     * @param value the value
     */
    public final void setFileTotalParts(int value) {
        this.fileTotalParts = value;
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
    public final void setBytes(TLBytes value) {
        this.bytes = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.fileId, stream);
        StreamingUtils.writeInt(this.filePart, stream);
        StreamingUtils.writeInt(this.fileTotalParts, stream);
        StreamingUtils.writeTLBytes(this.bytes, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.fileId = StreamingUtils.readLong(stream);
        this.filePart = StreamingUtils.readInt(stream);
        this.fileTotalParts = StreamingUtils.readInt(stream);
        this.bytes = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "upload.saveBigFilePart#de7b673d";
    }

}