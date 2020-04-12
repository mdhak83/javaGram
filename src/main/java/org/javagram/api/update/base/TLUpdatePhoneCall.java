package org.javagram.api.update.base;

import org.javagram.api.phone.base.call.TLAbsPhoneCall;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update chat user typing.
 */
public class TLUpdatePhoneCall extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xab0f6b1e;

    private TLAbsPhoneCall phoneCall;

    public TLUpdatePhoneCall() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsPhoneCall getPhoneCall() {
        return phoneCall;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.phoneCall, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.phoneCall = StreamingUtils.readTLObject(stream, context, TLAbsPhoneCall.class);
    }

    @Override
    public String toString() {
        return "updatePhoneCall#ab0f6b1e";
    }

}