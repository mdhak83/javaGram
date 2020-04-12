package org.telegram.api.photo.base.size;

import org.telegram.api.file.base.location.TLAbsFileLocation;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Image description.
 * photoSize#77bfb61b type:string location:FileLocation w:int h:int size:int = PhotoSize;
 */
public class TLPhotoSize extends TLAbsPhotoSize {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x77bfb61b;

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
     * File size
     */
    private int size;

    public TLPhotoSize() {
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

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.type, stream);
        StreamingUtils.writeTLObject(this.location, stream);
        StreamingUtils.writeInt(this.w, stream);
        StreamingUtils.writeInt(this.h, stream);
        StreamingUtils.writeInt(this.size, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.type = StreamingUtils.readTLString(stream);
        this.location = StreamingUtils.readTLObject(stream, context, TLAbsFileLocation.class);
        this.w = StreamingUtils.readInt(stream);
        this.h = StreamingUtils.readInt(stream);
        this.size = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "photoSize#77bfb61b";
    }

}