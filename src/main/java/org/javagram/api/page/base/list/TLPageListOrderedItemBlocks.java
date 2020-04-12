package org.javagram.api.page.base.list;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.page.base.block.TLAbsPageBlock;
import org.javagram.api._primitives.TLVector;

/**
 * Ordered list of IV blocks
 * pageListOrderedItemBlocks#98dd8936 num:string blocks:Vector&lt;PageBlock&gt; = PageListOrderedItem;
 */
public class TLPageListOrderedItemBlocks extends TLAbsPageListOrderedItem {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x98dd8936;

    /**
     * Number of element within ordered list
     */
    private String num;
    
    /**
     * Item contents
     */
    private TLVector<TLAbsPageBlock> blocks;

    public TLPageListOrderedItemBlocks() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public TLVector<TLAbsPageBlock> getBlocks() {
        return blocks;
    }

    public void setBlocks(TLVector<TLAbsPageBlock> blocks) {
        this.blocks = blocks;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.num, stream);
        StreamingUtils.writeTLVector(this.blocks, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.num = StreamingUtils.readTLString(stream);
        this.blocks = StreamingUtils.readTLVector(stream, context, TLAbsPageBlock.class);
    }

    @Override
    public String toString() {
        return "pageListOrderedItemBlocks#98dd8936";
    }

}