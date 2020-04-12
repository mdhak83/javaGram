package org.javagram.api.wallpaper.base.input;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Wallpaper
 * inputWallPaper#e630b979 id:long access_hash:long = InputWallPaper;
 */
public class TLInputWallPaper extends TLAbsInputWallPaper {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe630b979;

    /**
     * Wallpaper ID
     */
    private long id;
    
    /**
     * Access hash
     */
    private long accessHash;
    
    public TLInputWallPaper() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.accessHash = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "inputWallPaper#e630b979";
    }

}