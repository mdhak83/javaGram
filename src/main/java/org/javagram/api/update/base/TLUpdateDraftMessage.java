package org.javagram.api.update.base;

import org.javagram.api.message.base.draft.TLAbsDraftMessage;
import org.javagram.api.peer.base.TLAbsPeer;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update channel new message
 */
public class TLUpdateDraftMessage extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xee2bb969;

    private TLAbsPeer peer;
    private TLAbsDraftMessage draft;

    public TLUpdateDraftMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsPeer getPeer() {
        return peer;
    }

    public TLAbsDraftMessage getDraft() {
        return draft;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(peer, stream);
        StreamingUtils.writeTLObject(draft, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsPeer.class);
        this.draft = StreamingUtils.readTLObject(stream, context, TLAbsDraftMessage.class);
    }

    @Override
    public String toString() {
        return "updateDraftMessage#ee2bb969";
    }

}