package org.javagram.api.page.base.block;

import org.javagram.api.richtext.base.TLAbsRichText;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.page.base.TLPageCaption;

/**
 * An embedded post
 */
public class TLPageBlockEmbedPost extends TLAbsPageBlock {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf259a80b;

    private String url;
    private long webpageId;
    private long authorPhotoId;
    private String author;
    private int date;
    private TLVector<TLAbsPageBlock> blocks;

    /**
     * Caption
     */
    private TLPageCaption caption;

    public TLPageBlockEmbedPost() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getUrl() {
        return url;
    }

    public long getWebpageId() {
        return webpageId;
    }

    public long getAuthorPhotoId() {
        return authorPhotoId;
    }

    public String getAuthor() {
        return author;
    }

    public int getDate() {
        return date;
    }

    public TLVector<TLAbsPageBlock> getBlocks() {
        return blocks;
    }

    public TLPageCaption getCaption() {
        return caption;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWebpageId(long webpageId) {
        this.webpageId = webpageId;
    }

    public void setAuthorPhotoId(long authorPhotoId) {
        this.authorPhotoId = authorPhotoId;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setBlocks(TLVector<TLAbsPageBlock> blocks) {
        this.blocks = blocks;
    }

    public void setCaption(TLPageCaption caption) {
        this.caption = caption;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.url, stream);
        StreamingUtils.writeLong(this.webpageId, stream);
        StreamingUtils.writeLong(this.authorPhotoId, stream);
        StreamingUtils.writeTLString(this.author, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLVector(this.blocks, stream);
        StreamingUtils.writeTLObject(this.caption, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.url = StreamingUtils.readTLString(stream);
        this.webpageId = StreamingUtils.readLong(stream);
        this.authorPhotoId = StreamingUtils.readLong(stream);
        this.author = StreamingUtils.readTLString(stream);
        this.date = StreamingUtils.readInt(stream);
        this.blocks = StreamingUtils.readTLVector(stream, context, TLAbsPageBlock.class);
        this.caption = StreamingUtils.readTLObject(stream, context, TLPageCaption.class);
    }

    @Override
    public String toString() {
        return "pageBlockEmbedPost#f259a80b";
    }
    
}
