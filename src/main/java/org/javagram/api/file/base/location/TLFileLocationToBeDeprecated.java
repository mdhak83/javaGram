package org.javagram.api.file.base.location;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Indicates the location of a photo, will be deprecated soon
 * fileLocationToBeDeprecated#bc7fc6cd volume_id:long local_id:int = FileLocation;
 */
public class TLFileLocationToBeDeprecated extends TLAbsFileLocation {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbc7fc6cd;

    /**
     * Volume ID
     */
    private long volumeId;
    
    /**
     * Local ID
     */
    private int localId;

    public TLFileLocationToBeDeprecated() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getVolumeId() {
        return this.volumeId;
    }

    public void setVolumeId(long volumeId) {
        this.volumeId = volumeId;
    }

    public int getLocalId() {
        return this.localId;
    }

    public void setLocalId(int localId) {
        this.localId = localId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.volumeId, stream);
        StreamingUtils.writeInt(this.localId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.volumeId = StreamingUtils.readLong(stream);
        this.localId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "fileLocationToBeDeprecated#bc7fc6cd";
    }
    
    }