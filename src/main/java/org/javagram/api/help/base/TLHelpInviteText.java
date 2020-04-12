package org.javagram.api.help.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Text of a text message with an invitation to install application.
 * help.inviteText#18cb9f78 message:string = help.InviteText;
 */
public class TLHelpInviteText extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x18cb9f78;

    /**
     * Text of a message
     */
    private String message;

    public TLHelpInviteText() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String value) {
        this.message = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.message, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.message = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "help.inviteText#18cb9f78";
    }

}