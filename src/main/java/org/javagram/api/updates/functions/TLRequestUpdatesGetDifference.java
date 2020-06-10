package org.javagram.api.updates.functions;

import org.javagram.api.updates.base.difference.TLAbsDifference;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * updates.getDifference
 * Get new @see <a href="https://core.telegram.org/api/updates">updates</a>.
 * updates.getDifference#25939651 flags:# pts:int pts_total_limit:flags.0?int date:int qts:int = updates.Difference;
 */
public class TLRequestUpdatesGetDifference extends TLMethod<TLAbsDifference> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x25939651;

    private static final int FLAG_PTS_TOTAL_LIMIT    = 0x00000001; // 0

    /**
     * PTS, see @see <a href="https://core.telegram.org/api/updates">updates</a>.
     */
    private int pts;
    
    /**
     * For fast updating: if provided and <code>pts + pts_total_limit &lt; remote pts</code>, @see <a href="https://core.telegram.org/constructor/updates.differenceTooLong">updates.differenceTooLong</a> will be returned.
     * Simply tells the server to not return the difference if it is bigger than <code>pts_total_limit</code>
     * If the remote pts is too big (> ~4000000), this field will default to 1000000
     */
    private int ptsTotalLimit;

    /**
     * date, see @see <a href="https://core.telegram.org/api/updates">updates</a>.
     */
    private int date;
    
    /**
     * QTS, see @see <a href="https://core.telegram.org/api/updates">updates</a>.
     */
    private int qts;

    public TLRequestUpdatesGetDifference() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public boolean hasPtsTotalLimit() {
        return this.isFlagSet(FLAG_PTS_TOTAL_LIMIT);
    }
    
    public int getPtsTotalLimit() {
        return ptsTotalLimit;
    }

    public void setPtsTotalLimit(int ptsTotalLimit) {
        this.ptsTotalLimit = ptsTotalLimit > 0 ? ptsTotalLimit : 0;
        this.setPtsTotalLimit(ptsTotalLimit > 0);
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getQts() {
        return qts;
    }

    public void setQts(int qts) {
        this.qts = qts;
    }

    private void setPtsTotalLimit(boolean enabled) {
        this.setFlag(FLAG_PTS_TOTAL_LIMIT, enabled);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.pts, stream);
        if (this.hasPtsTotalLimit()) {
            StreamingUtils.writeInt(this.ptsTotalLimit, stream);
        }
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeInt(this.qts, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.pts = StreamingUtils.readInt(stream);
        if (this.hasPtsTotalLimit()) {
            this.ptsTotalLimit = StreamingUtils.readInt(stream);
        }
        this.date = StreamingUtils.readInt(stream);
        this.qts = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsDifference deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsDifference) {
            return (TLAbsDifference) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsDifference.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "updates.getDifference#25939651";
    }

}