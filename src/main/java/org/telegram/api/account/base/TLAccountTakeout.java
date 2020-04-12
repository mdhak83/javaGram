package org.telegram.api.account.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLObject;

/**
 * Takeout info
 * account.takeout#4dba4501 id:long = account.Takeout;
 */
public class TLAccountTakeout extends TLObject {

    /**
     * The constant CLASS_ID of this class.
     */
    public static final int CLASS_ID = 0x4dba4501;

    /**
     * Takeout ID
     */
    private long id;
    
    public TLAccountTakeout() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "account.takeout#4dba4501";
    }

}