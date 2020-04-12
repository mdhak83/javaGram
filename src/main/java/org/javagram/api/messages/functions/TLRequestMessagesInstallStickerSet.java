package org.javagram.api.messages.functions;

import org.javagram.api.sticker.base.input.set.TLAbsInputStickerSet;
import org.javagram.api.messages.base.stickers.setintallresult.TLAbsMessagesStickerSetInstallResult;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Install a stickerset
 * messages.installStickerSet#c78fe460 stickerset:InputStickerSet archived:Bool = messages.StickerSetInstallResult;
 */
public class TLRequestMessagesInstallStickerSet extends TLMethod<TLAbsMessagesStickerSetInstallResult> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc78fe460;

    /**
     * Stickerset to install
     */
    private TLAbsInputStickerSet stickerSet;
    
    /**
     * Whether to archive stickerset
     */
    private boolean archived;

    public TLRequestMessagesInstallStickerSet() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputStickerSet getStickerSet() {
        return stickerSet;
    }

    public void setStickerSet(TLAbsInputStickerSet stickerSet) {
        this.stickerSet = stickerSet;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.stickerSet, stream);
        StreamingUtils.writeTLBool(this.archived, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.stickerSet = StreamingUtils.readTLObject(stream, context, TLAbsInputStickerSet.class);
        this.archived = StreamingUtils.readTLBool(stream);
    }

    @Override
    public TLAbsMessagesStickerSetInstallResult deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsMessagesStickerSetInstallResult) {
            return (TLAbsMessagesStickerSetInstallResult) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsMessagesStickerSetInstallResult.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.installStickerSet#c78fe460";
    }

}