package org.telegram.api.file.base.input.location.web;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Location of a remote HTTP(s) file
 * inputWebFileLocation#c239d686 url:string access_hash:long = InputWebFileLocation;
 */
public class TLInputWebFileLocation extends TLAbsInputWebFileLocation {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc239d686;

    /**
     * HTTP URL of file
     */
    private String url;
    
    /**
     * Access hash
     */
    private long accessHash;

    public TLInputWebFileLocation() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.url, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.url = StreamingUtils.readTLString(stream);
        this.accessHash = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "inputWebFileLocation#c239d686";
    }

}