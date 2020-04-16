package org.javagram.api.file.base.location;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL file location.
 */
public class TLFileLocation extends TLAbsFileLocation {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x53d69076;

    private int dcId;
    private long secret;

    public TLFileLocation() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets dc id.
     *
     * @return the dc id
     */
    public int getDcId() {
        return this.dcId;
    }

    /**
     * Sets dc id.
     *
     * @param dcId the dc id
     */
    public void setDcId(int dcId) {
        this.dcId = dcId;
    }

    /**
     * Gets secret.
     *
     * @return the secret
     */
    public long getSecret() {
        return this.secret;
    }

    /**
     * Sets secret.
     *
     * @param secret the secret
     */
    public void setSecret(long secret) {
        this.secret = secret;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.dcId, stream);
        StreamingUtils.writeLong(this.volumeId, stream);
        StreamingUtils.writeInt(this.localId, stream);
        StreamingUtils.writeLong(this.secret, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.dcId = StreamingUtils.readInt(stream);
        this.volumeId = StreamingUtils.readLong(stream);
        this.localId = StreamingUtils.readInt(stream);
        this.secret = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "fileLocation#53d69076";
    }

}