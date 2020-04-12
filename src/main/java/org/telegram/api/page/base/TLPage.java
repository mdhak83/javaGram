package org.telegram.api.page.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.document.base.TLAbsDocument;
import org.telegram.api.page.base.block.TLAbsPageBlock;
import org.telegram.api.photo.base.TLAbsPhoto;
import org.telegram.api._primitives.TLVector;

/**
 * @see <a href="https://instantview.telegram.org/">Instant view page</a>
 * page#ae891bec flags:# part:flags.0?true rtl:flags.1?true v2:flags.2?true url:string blocks:Vector&lt;PageBlock&gt; photos:Vector&lt;Photo&gt; documents:Vector&lt;Document&gt; = Page;
 */
public class TLPage extends TLAbsPage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xae891bec;

    private static final int FLAG_PART  = 0x00000001; // 0
    private static final int FLAG_RTL   = 0x00000002; // 1 
    private static final int FLAG_V2    = 0x00000004; // 2

    /**
     * URL of article
     */
    private String url;
    
    /**
     * Page elements (like with HTML elements, only as TL constructors)
     */
    private TLVector<TLAbsPageBlock> blocks;
    
    /**
     * Photos in page
     */
    private TLVector<TLAbsPhoto> photos;
    
    /**
     * Media in page
     */
    private TLVector<TLAbsDocument> documents;
    
    public TLPage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public boolean isPart() {
        return this.isFlagSet(FLAG_PART);
    }
    
    public void setPart(boolean value) {
        this.setFlag(FLAG_PART, value);
    }

    public boolean isRtl() {
        return this.isFlagSet(FLAG_RTL);
    }
    
    public void setRtl(boolean value) {
        this.setFlag(FLAG_RTL, value);
    }

    public boolean isV2() {
        return this.isFlagSet(FLAG_V2);
    }
    
    public void setV2(boolean value) {
        this.setFlag(FLAG_V2, value);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TLVector<TLAbsPageBlock> getBlocks() {
        return blocks;
    }

    public void setBlocks(TLVector<TLAbsPageBlock> blocks) {
        this.blocks = blocks;
    }

    public TLVector<TLAbsPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(TLVector<TLAbsPhoto> photos) {
        this.photos = photos;
    }

    public TLVector<TLAbsDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(TLVector<TLAbsDocument> documents) {
        this.documents = documents;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.url, stream);
        StreamingUtils.writeTLVector(this.blocks, stream);
        StreamingUtils.writeTLVector(this.photos, stream);
        StreamingUtils.writeTLVector(this.documents, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.url = StreamingUtils.readTLString(stream);
        this.blocks = StreamingUtils.readTLVector(stream, context, TLAbsPageBlock.class);
        this.photos = StreamingUtils.readTLVector(stream, context, TLAbsPhoto.class);
        this.documents = StreamingUtils.readTLVector(stream, context, TLAbsDocument.class);
    }

    @Override
    public String toString() {
        return "page#ae891bec";
    }

}