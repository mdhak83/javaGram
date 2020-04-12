package org.telegram.api.page.base.block;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.page.base.list.TLAbsPageListOrderedItem;
import org.telegram.api._primitives.TLVector;

/**
 * Ordered list of IV blocks
 * pageBlockOrderedList#9a8ae1e1 items:Vector&lt;PageListOrderedItem&gt; = PageBlock;
 */
public class TLPageBlockOrderedList extends TLAbsPageBlock {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9a8ae1e1;

    /**
     * List items
     */
    private TLVector<TLAbsPageListOrderedItem> items;

    public TLPageBlockOrderedList() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsPageListOrderedItem> getItems() {
        return items;
    }

    public void setItems(TLVector<TLAbsPageListOrderedItem> items) {
        this.items = items;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.items, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.items = StreamingUtils.readTLVector(stream, context, TLAbsPageListOrderedItem.class);
    }

    @Override
    public String toString() {
        return "pageBlockOrderedList#9a8ae1e1";
    }

}