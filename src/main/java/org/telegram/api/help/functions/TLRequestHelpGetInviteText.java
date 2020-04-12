package org.telegram.api.help.functions;

import org.telegram.api.help.base.TLHelpInviteText;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;

/**
 * Returns text of a text message with an invitation.
 * help.getInviteText#4d392343 = help.InviteText;
 */
public class TLRequestHelpGetInviteText extends TLMethod<TLHelpInviteText> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4d392343;

    public TLRequestHelpGetInviteText() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLHelpInviteText deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) { 
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLHelpInviteText) {
            return (TLHelpInviteText) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.help.TLInviteText, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "help.getInviteText#4d392343";
    }

}