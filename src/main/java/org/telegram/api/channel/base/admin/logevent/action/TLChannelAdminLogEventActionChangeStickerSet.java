package org.telegram.api.channel.base.admin.logevent.action;

import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.sticker.base.input.set.TLAbsInputStickerSet;
import org.telegram.utils.StreamingUtils;

/**
 * The supergroup's stickerset was changed
 * channelAdminLogEventActionChangeStickerSet#b1c3caa7 prev_stickerset:InputStickerSet new_stickerset:InputStickerSet = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionChangeStickerSet extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb1c3caa7;

    /**
     * Previous stickerset
     */
    private TLAbsInputStickerSet prevStickerset;

    /**
     * New stickerset
     */
    private TLAbsInputStickerSet newStickerset;

    public TLChannelAdminLogEventActionChangeStickerSet() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputStickerSet getPrevStickerset() {
        return prevStickerset;
    }

    public void setPrevStickerset(TLAbsInputStickerSet prevStickerset) {
        this.prevStickerset = prevStickerset;
    }

    public TLAbsInputStickerSet getNewStickerset() {
        return newStickerset;
    }

    public void setNewStickerset(TLAbsInputStickerSet newStickerset) {
        this.newStickerset = newStickerset;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.prevStickerset, stream);
        StreamingUtils.writeTLObject(this.newStickerset, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.prevStickerset = StreamingUtils.readTLObject(stream, context, TLAbsInputStickerSet.class);
        this.newStickerset = StreamingUtils.readTLObject(stream, context, TLAbsInputStickerSet.class);
    }

    @Override
    public String toString() {
        return "channelAdminLogEventActionChangeStickerSet";
    }

}