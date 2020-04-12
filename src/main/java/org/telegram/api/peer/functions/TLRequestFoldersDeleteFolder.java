package org.telegram.api.peer.functions;

import org.telegram.api.phone.base.TLPhonePhoneCall;
import org.telegram.api._primitives.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.updates.base.TLAbsUpdates;
import org.telegram.utils.StreamingUtils;

/**
 * Delete a folder
 * folders.deleteFolder#1c295881 folder_id:int = Updates;
 */
public class TLRequestFoldersDeleteFolder extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1c295881;

    /**
     * Folder to delete
     */
    private int folderId;

    public TLRequestFoldersDeleteFolder() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
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
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLPhonePhoneCall.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "folders.deleteFolder#1c295881";
    }

}