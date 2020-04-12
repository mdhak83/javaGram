package org.telegram.api.page.base.block;

import org.telegram.api.richtext.base.TLAbsRichText;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLVector;

/**
 * A collapsible details block
 * pageBlockDetails#76768bed flags:# open:flags.0?true blocks:Vector&lt;PageBlock&gt; title:RichText = PageBlock;
 */
public class TLPageBlockDetails extends TLAbsPageBlock {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x76768bed;

    private static final int FLAG_OPEN  = 0x00000001; // 0

    /**
     * Block contents
     */
    private TLVector<TLAbsPageBlock> blocks;
    
    /**
     * Always visible heading for the block
     */
    private TLAbsRichText title;

    public TLPageBlockDetails() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isOpen() {
        return this.isFlagSet(FLAG_OPEN);
    }
    
    public void setOpen(boolean value) {
        this.setFlag(FLAG_OPEN, value);
    }

    public TLVector<TLAbsPageBlock> getBlocks() {
        return blocks;
    }

    public void setBlocks(TLVector<TLAbsPageBlock> blocks) {
        this.blocks = blocks;
    }

    public TLAbsRichText getTitle() {
        return title;
    }

    public void setTitle(TLAbsRichText title) {
        this.title = title;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLVector(this.blocks, stream);
        StreamingUtils.writeTLObject(this.title, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.blocks = StreamingUtils.readTLVector(stream, context, TLAbsPageBlock.class);
        this.title = StreamingUtils.readTLObject(stream, context, TLAbsRichText.class);
    }

    @Override
    public String toString() {
        return "pageBlockDetails#76768bed";
    }

}