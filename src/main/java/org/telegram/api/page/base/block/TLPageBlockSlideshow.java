package org.telegram.api.page.base.block;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.page.base.TLPageCaption;

/**
 * Slideshow
 */
public class TLPageBlockSlideshow extends TLAbsPageBlock {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x31f9590;

    /**
     * Slideshow items
     */
    private TLVector<TLAbsPageBlock> items;
    
    /**
     * Caption
     */
    private TLPageCaption caption;

    public TLPageBlockSlideshow() {
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
        return "pageBlockSlideshow#31f9590";
    }

}
