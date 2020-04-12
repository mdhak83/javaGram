package org.telegram.api.help.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Localized name for telegram support
 * help.supportName#8c05f1c9 name:string = help.SupportName;
 */
public class TLHelpSupportName extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8c05f1c9;

    /**
     * Localized name
     */
    private String name;
    
    public TLHelpSupportName() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.name, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.name = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "help.supportName#8c05f1c9";
    }

}