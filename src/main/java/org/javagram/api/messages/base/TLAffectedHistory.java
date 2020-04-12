package org.javagram.api.messages.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL affected history.
 */
public class TLAffectedHistory extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb45c69d1;

    private int pts;
    private int ptsCount;
    private int offset;

    public TLAffectedHistory() {
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
     * @param value the value
     */
    public void setPts(int value) {
        this.pts = value;
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

    /**
     * Gets offset.
     *
     * @return the offset
     */
    public int getOffset() {
        return this.offset;
    }

    /**
     * Sets offset.
     *
     * @param value the value
     */
    public void setOffset(int value) {
        this.offset = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
        StreamingUtils.writeInt(this.offset, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
        this.offset = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messages.affectedHistory#b45c69d1";
    }

}