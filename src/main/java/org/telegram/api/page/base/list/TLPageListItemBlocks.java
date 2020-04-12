package org.telegram.api.page.base.list;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.page.base.block.TLAbsPageBlock;
import org.telegram.api._primitives.TLVector;

/**
 * Item in block list
 */
public class TLPageListItemBlocks extends TLAbsPageListItem {

    public static final int CLASS_ID = 0x25e073fc;

    /**
     * Blocks
     */
    private TLVector<TLAbsPageBlock> blocks;

    public TLPageListItemBlocks() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsPageBlock> getBlocks() {
        return blocks;
    }

    public void setText(TLVector<TLAbsPageBlock> blocks) {
        this.blocks = blocks;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.blocks, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.blocks = StreamingUtils.readTLVector(stream, context, TLAbsPageBlock.class);
    }

    @Override
    public String toString() {
        return "pageListItemBlocks#25e073fc";
    }

}