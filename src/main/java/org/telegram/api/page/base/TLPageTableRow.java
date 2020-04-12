package org.telegram.api.page.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;

/**
 * Table row
 * pageTableRow#e0c0c5e5 cells:Vector&lt;PageTableCell&gt; = PageTableRow;
 */
public class TLPageTableRow extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe0c0c5e5;

    /**
     * Table cells
     */
    private TLVector<TLPageTableCell> cells;

    public TLPageTableRow() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLPageTableCell> getCells() {
        return cells;
    }

    public void setCells(TLVector<TLPageTableCell> cells) {
        this.cells = cells;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.cells, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.cells = StreamingUtils.readTLVector(stream, context, TLPageTableCell.class);
    }

    @Override
    public String toString() {
        return "pageTableRow#e0c0c5e5";
    }

}