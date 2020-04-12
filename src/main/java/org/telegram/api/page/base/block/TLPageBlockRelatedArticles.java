package org.telegram.api.page.base.block;

import org.telegram.api.richtext.base.TLAbsRichText;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.page.base.TLPageRelatedArticle;
import org.telegram.api._primitives.TLVector;

/**
 * Related articles
 * pageBlockRelatedArticles#16115a96 title:RichText articles:Vector&lt;PageRelatedArticle&gt; = PageBlock;
 */
public class TLPageBlockRelatedArticles extends TLAbsPageBlock {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x16115a96;

    /**
     * Title
     */
    private TLAbsRichText title;

    /**
     * Related articles
     */
    private TLVector<TLPageRelatedArticle> articles;
    
    public TLPageBlockRelatedArticles() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsRichText getTitle() {
        return title;
    }

    public void setTitle(TLAbsRichText title) {
        this.title = title;
    }

    public TLVector<TLPageRelatedArticle> getArticles() {
        return articles;
    }

    public void setArticles(TLVector<TLPageRelatedArticle> articles) {
        this.articles = articles;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.title, stream);
        StreamingUtils.writeTLVector(this.articles, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.title = StreamingUtils.readTLObject(stream, context, TLAbsRichText.class);
        this.articles = StreamingUtils.readTLVector(stream, context, TLPageRelatedArticle.class);
    }

    @Override
    public String toString() {
        return "pageBlockRelatedArticles#16115a96";
    }

}