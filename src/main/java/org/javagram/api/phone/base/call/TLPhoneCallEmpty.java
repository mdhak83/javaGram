package org.javagram.api.phone.base.call;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Empty constructor
 * phoneCallEmpty#5366c915 id:long = PhoneCall;
 */
public class TLPhoneCallEmpty extends TLAbsPhoneCall {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5366c915;

    /**
     * Call ID
     */
    private long id;

    public TLPhoneCallEmpty() {
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
        return "phoneCallEmpty#5366c915";
    }

}