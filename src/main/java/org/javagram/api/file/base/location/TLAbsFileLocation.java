package org.javagram.api.file.base.location;

import org.javagram.api._primitives.TLObject;

/**
 * The type TL abs file location.
 */
public abstract class TLAbsFileLocation extends TLObject {

    /**
     * Volume ID
     */
    protected long volumeId;

    /**
     * Local ID
     */
    protected int localId;

    protected TLAbsFileLocation() {
        super();
    }
    
    /**
     * Gets volume id.
     *
     * @return the volume id
     */
    public long getVolumeId() {
        return this.volumeId;
    }

    /**
     * Sets volume id.
     *
     * @param volumeId the volume id
     */
    public void setVolumeId(long volumeId) {
        this.volumeId = volumeId;
    }

    /**
     * Gets local id.
     *
     * @return the local id
     */
    public int getLocalId() {
        return this.localId;
    }

    /**
     * Sets local id.
     *
     * @param localId the local id
     */
    public void setLocalId(int localId) {
        this.localId = localId;
    }

}