package org.telegram.api.page.base.block;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.page.base.list.TLAbsPageListItem;

/**
 * Unordered list of IV blocks
 */
public class TLPageBlockList extends TLAbsPageBlock {

    public static final int CLASS_ID = 0xe4e88011;

    /**
     * List of blocks in an IV page
     */
    private TLVector<TLAbsPageListItem> items;

    public TLPageBlockList() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsPageListItem> getItems() {
        return items;
    }

    public void setItems(TLVector<TLAbsPageListItem> items) {
        this.items = items;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.items, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.items = StreamingUtils.readTLVector(stream, context, TLAbsPageListItem.class);
    }

    @Override
    public String toString() {
        return "pageBlockList#e4e88011";
    }

}