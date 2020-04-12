package org.javagram.api.messages.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL affected messages.
 * @author Ruben Bermudez
 * @version 2.0
 * TLAffectedMessages
 * @since 11/APR/2015
 */
public class TLAffectedMessages extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x84d19185;

    private int pts;
    private int ptsCount;

    public TLAffectedMessages() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets pts.
     *
     * @return the pts
     */
    public int getPts() {
        return this.pts;
    }

    /**
     * Sets pts.
     *
     * @param pts the pts
     */
    public void setPts(int pts) {
        this.pts = pts;
    }

    /**
     * Gets pts count.
     *
     * @return the pts count
     */
    public int getPtsCount() {
        return this.ptsCount;
    }

    /**
     * Sets pts count.
     *
     * @param ptsCount the pts count
     */
    public void setPtsCount(int ptsCount) {
        this.ptsCount = ptsCount;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        super.deserializeBody(stream, context);
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messages.affectedmessages#84d19185";
    }

}
