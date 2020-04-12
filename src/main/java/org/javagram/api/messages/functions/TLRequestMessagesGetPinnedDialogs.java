package org.javagram.api.messages.functions;

import org.javagram.api.messages.base.TLMessagesPeerDialogs;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Get pinned dialogs
 * messages.getPinnedDialogs#d6b94df2 folder_id:int = messages.PeerDialogs;
 */
public class TLRequestMessagesGetPinnedDialogs extends TLMethod<TLMessagesPeerDialogs> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd6b94df2;

    /**
     * Folder ID
     */
    private int folderId;
    
    public TLRequestMessagesGetPinnedDialogs() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.folderId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.folderId = StreamingUtils.readInt(stream);
    }

    @Override
    public TLMessagesPeerDialogs deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLMessagesPeerDialogs) {
            return (TLMessagesPeerDialogs) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLMessagesPeerDialogs.class.getName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    @Override
    public String toString() {
        return "messages.getPinnedDialogs#d6b94df2";
    }

}