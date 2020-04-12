package org.telegram.api.photo.base.size;

import org.telegram.api.file.base.location.TLAbsFileLocation;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLBytes;

/**
 * Description of an image and its content.
 * photoCachedSize#e9a734fa type:string location:FileLocation w:int h:int bytes:bytes = PhotoSize;
 */
public class TLPhotoCachedSize extends TLAbsPhotoSize {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe9a734fa;

    /**
     * File location
     */
    private TLAbsFileLocation location;
    
    /**
     * Image width
     */
    private int w;
    
    /**
     * Image height
     */
    private int h;
    
    /**
     * Binary data, file content
     */
    private TLBytes bytes;

    public TLPhotoCachedSize() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsFileLocation getLocation() {
        return this.location;
    }

    public void setLocation(TLAbsFileLocation location) {
        this.location = location;
    }

    public int getW() {
        return this.w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return this.h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public TLBytes getBytes() {
        return this.bytes;
    }

    public void setBytes(TLBytes bytes) {
        this.bytes = bytes;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.type, stream);
        StreamingUtils.writeTLObject(this.location, stream);
        StreamingUtils.writeInt(this.w, stream);
        StreamingUtils.writeInt(this.h, stream);
        StreamingUtils.writeTLBytes(this.bytes, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.type = StreamingUtils.readTLString(stream);
        this.location = StreamingUtils.readTLObject(stream, context, TLAbsFileLocation.class);
        this.w = StreamingUtils.readInt(stream);
        this.h = StreamingUtils.readInt(stream);
        this.bytes = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "photoCachedSize#e9a734fa";
    }

}