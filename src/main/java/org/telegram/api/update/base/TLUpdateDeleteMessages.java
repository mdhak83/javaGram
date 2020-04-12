package org.telegram.api.update.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLIntVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

/**
 * The type TL update delete messages.
 */
public class TLUpdateDeleteMessages extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa20db0e5;

    private TLIntVector messages;
    private int pts;
    private int ptsCount;

    public TLUpdateDeleteMessages() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets messages.
     *
     * @return the messages
     */
    public TLIntVector getMessages() {
        return this.messages;
    }

    /**
     * Sets messages.
     *
     * @param messages the messages
     */
    public void setMessages(TLIntVector messages) {
        this.messages = messages;
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
        StreamingUtils.writeTLVector(this.messages, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.messages = StreamingUtils.readTLIntVector(stream, context);
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updateDeleteMessages#a20db0e5";
    }

    public String toLog() {
        String ret = null;
        if (this.messages != null && !this.messages.isEmpty()) {
            String sIds = "";
            if (this.messages != null) {
                Iterator<Integer> it = this.messages.iterator();
                while (it.hasNext()) {
                    sIds += it.next();
                    if (it.hasNext()) {
                        sIds += ";";
                    }
                }
            }
            ret = "IDs : {" + sIds + "}";
        }
        return ret;
    }

}