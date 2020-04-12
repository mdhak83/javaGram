package org.javagram.api.foundgif.base;

import org.javagram.api.document.base.TLAbsDocument;
import org.javagram.api.photo.base.TLAbsPhoto;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
  * @since 13/FEB/2016
 */
public class TLFoundGifCached extends TLAbsFoundGif {
    public static final int CLASS_ID = 0x9c750409;

    private String url;
    private TLAbsPhoto photo;
    private TLAbsDocument document;

    public TLFoundGifCached() {
        super();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TLAbsPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(TLAbsPhoto photo) {
        this.photo = photo;
    }

    public TLAbsDocument getDocument() {
        return document;
    }

    public void setDocument(TLAbsDocument document) {
        this.document = document;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(url, stream);
        StreamingUtils.writeTLObject(photo, stream);
        StreamingUtils.writeTLObject(document, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.url = StreamingUtils.readTLString(stream);
        this.photo = StreamingUtils.readTLObject(stream, context, TLAbsPhoto.class);
        this.document = StreamingUtils.readTLObject(stream, context, TLAbsDocument.class);
    }

    @Override
    public String toString() {
        return "foundGifCached#9c750409";
    }

}
