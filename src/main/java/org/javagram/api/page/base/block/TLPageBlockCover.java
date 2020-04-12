package org.javagram.api.page.base.block;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPageBlockCover extends TLAbsPageBlock {
    public static final int CLASS_ID = 0x39f23300;

    private TLAbsPageBlock cover;

    public TLPageBlockCover() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsPageBlock getCover() {
        return cover;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(cover, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        cover = StreamingUtils.readTLObject(stream, context, TLAbsPageBlock.class);
    }

    @Override
    public String toString() {
        return "pageBlockCover#39f23300";
    }

}
