package org.javagram.api.user.base.status;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL user status online.
 */
public class TLUserStatusOnline extends TLAbsUserStatus {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xedb93949;

    private int expires;

    public TLUserStatusOnline() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets expires.
     *
     * @return the expires
     */
    public int getExpires() {
        return this.expires;
    }

    /**
     * Sets expires.
     *
     * @param expires the expires
     */
    public void setExpires(int expires) {
        this.expires = expires;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.expires, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.expires = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "userStatusOnline#edb93949";
    }

}