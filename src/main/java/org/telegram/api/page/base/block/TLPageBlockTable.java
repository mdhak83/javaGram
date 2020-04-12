package org.telegram.api.page.base.block;

import org.telegram.api.richtext.base.TLAbsRichText;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.page.base.TLPageTableRow;
import org.telegram.api._primitives.TLVector;

/**
 * Table
 * pageBlockTable#bf4dea82 flags:# bordered:flags.0?true striped:flags.1?true title:RichText rows:Vector&lt;PageTableRow&gt; = PageBlock;
 */
public class TLPageBlockTable extends TLAbsPageBlock {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbf4dea82;

    private static final int FLAG_BORDERED  = 0x00000001; // 0 : Does the table have a visible border?
    private static final int FLAG_STRIPED   = 0x00000002; // 1 : Is the table striped?

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;

    /**
     * Title
     */
    private TLAbsRichText title;
    
    /**
     * Table rows
     */
    private TLVector<TLPageTableRow> rows;

    public TLPageBlockTable() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public boolean isBordered() {
        return this.isFlagSet(FLAG_BORDERED);
    }
    
    public void setBordered(boolean value) {
        this.setFlag(FLAG_BORDERED, value);
    }

    public boolean isStriped() {
        return this.isFlagSet(FLAG_STRIPED);
    }
    
    public void setStriped(boolean value) {
        this.setFlag(FLAG_STRIPED, value);
    }

    public TLAbsRichText getTitle() {
        return title;
    }

    public void setTitle(TLAbsRichText title) {
        this.title = title;
    }

    public TLVector<TLPageTableRow> getRows() {
        return rows;
    }

    public void setRows(TLVector<TLPageTableRow> rows) {
        this.rows = rows;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.title, stream);
        StreamingUtils.writeTLVector(this.rows, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.title = StreamingUtils.readTLObject(stream, context, TLAbsRichText.class);
        this.rows = StreamingUtils.readTLVector(stream, context, TLPageTableRow.class);
    }

    @Override
    public String toString() {
        return "pageBlockTable#bf4dea82";
    }

}