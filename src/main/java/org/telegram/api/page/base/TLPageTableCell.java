package org.telegram.api.page.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.richtext.base.TLAbsRichText;
import org.telegram.api._primitives.TLObject;

/**
 * Table cell
 * pageTableCell#34566b6a flags:# header:flags.0?true align_center:flags.3?true align_right:flags.4?true valign_middle:flags.5?true valign_bottom:flags.6?true text:flags.7?RichText colspan:flags.1?int rowspan:flags.2?int = PageTableCell;
 */
public class TLPageTableCell extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x34566b6a;

    private static final int FLAG_HEADER        = 0x00000001; // 0
    private static final int FLAG_COLSPAN       = 0x00000002; // 1 
    private static final int FLAG_ROWSPAN       = 0x00000004; // 2
    private static final int FLAG_ALIGN_CENTER  = 0x00000008; // 3: 
    private static final int FLAG_ALIGN_RIGHT   = 0x00000010; // 4 
    private static final int FLAG_VALIGN_MIDDLE = 0x00000020; // 5 
    private static final int FLAG_VALIGN_BOTTOM = 0x00000040; // 6 
    private static final int FLAG_TEXT          = 0x00000080; // 7 
    
    /**
     * Content
     */
    private TLAbsRichText text;
    
    /**
     * For how many columns should this cell extend
     */
    private int colspan;
    
    /**
     * For how many rows should this cell extend
     */
    private int rowspan;

    public TLPageTableCell() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public boolean isHeader() {
        return this.isFlagSet(FLAG_HEADER);
    }
    
    public void setHeader(boolean value) {
        this.setFlag(FLAG_HEADER, value);
    }

    public boolean isCenterAligned() {
        return this.isFlagSet(FLAG_ALIGN_CENTER);
    }
    
    public void setCenterAligned(boolean value) {
        this.setFlag(FLAG_ALIGN_CENTER, value);
    }

    public boolean isRightAligned() {
        return this.isFlagSet(FLAG_ALIGN_RIGHT);
    }
    
    public void setRightAligned(boolean value) {
        this.setFlag(FLAG_ALIGN_RIGHT, value);
    }

    public boolean isMiddleVerticalAligned() {
        return this.isFlagSet(FLAG_VALIGN_MIDDLE);
    }
    
    public void setMiddleVerticalAligned(boolean value) {
        this.setFlag(FLAG_VALIGN_MIDDLE, value);
    }

    public boolean isBottomVerticalAligned() {
        return this.isFlagSet(FLAG_VALIGN_BOTTOM);
    }
    
    public void setBottomVerticalAligned(boolean value) {
        this.setFlag(FLAG_VALIGN_BOTTOM, value);
    }

    public boolean hasText() {
        return this.isFlagSet(FLAG_TEXT);
    }
    
    public void setText(TLAbsRichText text) {
        this.text = text;
        if (this.text != null) {
            this.flags |= FLAG_TEXT;
        } else {
            this.flags &= ~FLAG_TEXT;
        }
    }

    public boolean hasColspan() {
        return this.isFlagSet(FLAG_COLSPAN);
    }
    
    public int getColspan() {
        return colspan;
    }
    
    public void setColspan(int colspan) {
        this.colspan = colspan;
        if (this.colspan != 0) {
            this.flags |= FLAG_COLSPAN;
        } else {
            this.flags &= ~FLAG_COLSPAN;
        }
    }

    public boolean hasRowspan() {
        return this.isFlagSet(FLAG_ROWSPAN);
    }
    
    public int getRowspan() {
        return rowspan;
    }
    
    public void setRowspan(int rowspan) {
        this.rowspan = rowspan;
        if (this.rowspan != 0) {
            this.flags |= FLAG_ROWSPAN;
        } else {
            this.flags &= ~FLAG_ROWSPAN;
        }
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if (this.hasText()) {
            StreamingUtils.writeTLObject(this.text, stream);
        }
        if (this.hasColspan()) {
            StreamingUtils.writeInt(this.colspan, stream);
        }
        if (this.hasRowspan()) {
            StreamingUtils.writeInt(this.rowspan, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.hasText()) {
            this.text = StreamingUtils.readTLObject(stream, context, TLAbsRichText.class);
        }
        if (this.hasColspan()) {
            this.colspan = StreamingUtils.readInt(stream);
        }
        if (this.hasRowspan()) {
            this.rowspan = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "pageTableCell#34566b6a";
    }

}