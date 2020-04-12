package org.javagram.api.chat.base.input.photo;

import org.javagram.api.file.base.input.TLAbsInputFile;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input chat uploaded photo.
 */
public class TLInputChatUploadedPhoto extends TLAbsInputChatPhoto {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x927c55b4;

    /**
     * The File.
     */
    private TLAbsInputFile file;

    public TLInputChatUploadedPhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets file.
     *
     * @return the file
     */
    public TLAbsInputFile getFile() {
        return this.file;
    }

    /**
     * Sets file.
     *
     * @param file the file
     */
    public void setFile(TLAbsInputFile file) {
        this.file = file;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.file, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.file = StreamingUtils.readTLObject(stream, context, TLAbsInputFile.class);
    }

    @Override
    public String toString() {
        return "inputChatUploadedPhoto#927c55b4";
    }

}