package org.javagram.api.sticker.base.input.media;

import org.javagram.api.photo.base.input.TLAbsInputPhoto;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 *  * @since 26/SEP/2016
 */
public class TLInputStickeredMediaPhoto extends TLAbsInputStickeredMedia {
    public static final int CLASS_ID = 0x4a992157;

    private TLAbsInputPhoto id;

    public TLAbsInputPhoto getId() {
        return id;
    }

    public void setId(TLAbsInputPhoto id) {
        this.id = id;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        id = StreamingUtils.readTLObject(stream, context, TLAbsInputPhoto.class);
    }

    @Override
    public String toString() {
        return "inputStickeredMediaPhoto#4a992157";
    }

}
