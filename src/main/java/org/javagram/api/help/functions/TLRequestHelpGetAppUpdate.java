package org.javagram.api.help.functions;

import org.javagram.api.help.base.TLAbsHelpAppUpdate;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;

/**
 * Returns information on update availability for the current application.
 * help.getAppUpdate#522d5a7d source:string = help.AppUpdate;
 */
public class TLRequestHelpGetAppUpdate extends TLMethod<TLAbsHelpAppUpdate> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x522d5a7d;
    
    /**
     * Source
     */
    private String source;

    public TLRequestHelpGetAppUpdate() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public TLAbsHelpAppUpdate deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsHelpAppUpdate) {
            return (TLAbsHelpAppUpdate) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.help.TLAbsAppUpdate, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "help.getAppUpdate#522d5a7d";
    }

}