package org.javagram.api.file.base.input.location;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.peer.base.input.TLAbsInputPeer;

/**
 * Location of profile photo of channel/group/supergroup/user
 * inputPeerPhotoFileLocation#27d69997 flags:# big:flags.0?true peer:InputPeer volume_id:long local_id:int = InputFileLocation;
 */
public class TLInputPeerPhotoFileLocation extends TLAbsInputFileLocation {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x27d69997;

    private static final int FLAG_BIG   = 0x00000001; // 0 : Whether to download the high-quality version of the picture

    /**
     * The peer whose profile picture should be downloaded
     */
    private TLAbsInputPeer peer;

    /**
     * Volume ID from @see <a href="https://core.telegram.org/type/FileLocation">FileLocation</a> met in the profile photo container.
     */
    private long volumeId;

    /**
     * Local ID from @see <a href="https://core.telegram.org/type/FileLocation">FileLocation</a> met in the profile photo container.
     */
    private int localId;

    public TLInputPeerPhotoFileLocation() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isBig() {
        return this.isFlagSet(FLAG_BIG);
    }

    public void setBig(boolean value) {
        this.setFlag(FLAG_BIG, value);
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public long getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(long volumeId) {
        this.volumeId = volumeId;
    }

    public int getLocalId() {
        return localId;
    }

    public void setLocalId(int localId) {
        this.localId = localId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeLong(this.volumeId, stream);
        StreamingUtils.writeInt(this.localId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.volumeId = StreamingUtils.readLong(stream);
        this.localId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "inputPeerPhotoFileLocation#27d69997";
    }

}