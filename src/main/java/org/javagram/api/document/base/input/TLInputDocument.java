package org.javagram.api.document.base.input;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;

/**
 * inputDocument
 * Defines a video for subsequent interaction.
 * inputDocument#1abfb575 id:long access_hash:long file_reference:bytes = InputDocument;
 */
public class TLInputDocument extends TLAbsInputDocument {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1abfb575;

    /**
     * Document ID
     */
    private long id;

    /**
     * access_hash parameter from the <a href="https://core.telegram.org/constructor/document">document</a> constructor
     */
    private long accessHash;

    /**
     * @see <a href="https://core.telegram.org/api/file_reference">File reference</a>
     */
    private TLBytes fileReference;

    public TLInputDocument() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets access hash.
     *
     * @return the access hash
     */
    public long getAccessHash() {
        return this.accessHash;
    }

    /**
     * Sets access hash.
     *
     * @param accessHash the access hash
     */
    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    public TLBytes getFileReference() {
        return fileReference;
    }

    public void setFileReference(TLBytes fileReference) {
        this.fileReference = fileReference;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeTLBytes(this.fileReference, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.fileReference = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "inputDocument#1abfb575";
    }

}