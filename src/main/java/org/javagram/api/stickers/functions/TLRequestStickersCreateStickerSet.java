package org.javagram.api.stickers.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.messages.base.stickers.TLMessagesStickerSet;
import org.javagram.api.sticker.base.input.set.TLInputStickerSetItem;
import org.javagram.api.user.base.input.TLAbsInputUser;

/**
 * Create a stickerset, bots only.
 * stickers.createStickerSet#9bd86e6a flags:# masks:flags.0?true user_id:InputUser title:string short_name:string stickers:Vector&lt;InputStickerSetItem&gt; = messages.StickerSet;
 */
public class TLRequestStickersCreateStickerSet extends TLMethod<TLMessagesStickerSet> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9bd86e6a;

    private static final int FLAG_MASKS = 0x00000001; //  0 : Whether this is a mask stickerset
    
    /**
     * Stickerset owner
     */
    private TLAbsInputUser userId;
    
    /**
     * Stickerset name, <code>1-64</code> chars
     */
    private String title;
    
    /**
     * Sticker set name. Can contain only English letters, digits and underscores. Must end with "by" ( is case insensitive); 1-64 characters
     */
    private String shortName;
    
    /**
     * Stickers
     */
    private TLVector<TLInputStickerSetItem> stickers;

    public TLRequestStickersCreateStickerSet() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isMasks() {
        return this.isFlagSet(FLAG_MASKS);
    }

    public void setMasks(boolean value) {
        this.setFlag(FLAG_MASKS, value);
    }

    public TLAbsInputUser getUserId() {
        return userId;
    }

    public void setUserId(TLAbsInputUser userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public TLVector<TLInputStickerSetItem> getStickers() {
        return stickers;
    }

    public void setStickers(TLVector<TLInputStickerSetItem> stickers) {
        this.stickers = stickers;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.userId, stream);
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeTLString(this.shortName, stream);
        StreamingUtils.writeTLVector(this.stickers, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
        this.title = StreamingUtils.readTLString(stream);
        this.shortName = StreamingUtils.readTLString(stream);
        this.stickers = StreamingUtils.readTLVector(stream, context, TLInputStickerSetItem.class);
    }

    @Override
    public TLMessagesStickerSet deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLMessagesStickerSet) {
            return (TLMessagesStickerSet) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "stickers.createStickerSet#9bd86e6a";
    }

}