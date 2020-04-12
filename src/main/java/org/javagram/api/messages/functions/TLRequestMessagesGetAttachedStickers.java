package org.javagram.api.messages.functions;

import org.javagram.api.sticker.base.input.media.TLAbsInputStickeredMedia;
import org.javagram.api.sticker.base.stickersetcovered.TLAbsStickerSetCovered;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get chats.
 */
public class TLRequestMessagesGetAttachedStickers extends TLMethod<TLVector<TLAbsStickerSetCovered>> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xcc5b67cc;

    private TLAbsInputStickeredMedia media;

    public TLRequestMessagesGetAttachedStickers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLVector<TLAbsStickerSetCovered> deserializeResponse(InputStream stream, TLContext context) throws IOException {
        return StreamingUtils.readTLVector(stream, context, TLAbsStickerSetCovered.class);
    }

    public TLAbsInputStickeredMedia getMedia() {
        return media;
    }

    public void setMedia(TLAbsInputStickeredMedia media) {
        this.media = media;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(media, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        media = StreamingUtils.readTLObject(stream, context, TLAbsInputStickeredMedia.class);
    }

    @Override
    public String toString() {
        return "messages.getAttachedStickers#cc5b67cc";
    }

}