package org.javagram.api.page.base.block;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.page.base.TLPageCaption;

/**
 * Collage of media
 */
public class TLPageBlockCollage extends TLAbsPageBlock {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x65a0fa4d;

    /**
     * Media elements
     */
    private TLVector<TLAbsPageBlock> items;
    
    /**
     * Caption
     */
    private TLPageCaption caption;

    public TLPageBlockCollage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsPageBlock> getItems() {
        return items;
    }

    public TLPageCaption getCaption() {
        return caption;
    }

     public void setItems(TLVector<TLAbsPageBlock> items) {
        this.items = items;
    }

    public void setCaption(TLPageCaption caption) {
        this.caption = caption;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.items, stream);
        StreamingUtils.writeTLObject(this.caption, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.items = StreamingUtils.readTLVector(stream, context, TLAbsPageBlock.class);
        this.caption = StreamingUtils.readTLObject(stream, context, TLPageCaption.class);
    }

    @Override
    public String toString() {
        return "pageBlockCollage#65a0fa4d";
    }

}