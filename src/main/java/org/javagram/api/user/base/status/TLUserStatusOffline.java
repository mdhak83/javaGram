package org.javagram.api.user.base.status;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL user status offline.
 */
public class TLUserStatusOffline extends TLAbsUserStatus {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8c703f;

    private int wasOnline;

    public TLUserStatusOffline() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets was online.
     *
     * @return the was online
     */
    public int getWasOnline() {
        return this.wasOnline;
    }

    /**
     * Sets was online.
     *
     * @param wasOnline the was online
     */
    public void setWasOnline(int wasOnline) {
        this.wasOnline = wasOnline;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.wasOnline, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.wasOnline = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "userStatusOffline#8c703f";
    }

}