package org.telegram.api.recentmeurl.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.sticker.base.stickersetcovered.TLAbsStickerSetCovered;

/**
 * Recent t.me stickerset installation URL
 * recentMeUrlStickerSet#bc0a57dc url:string set:StickerSetCovered = RecentMeUrl;
 */
public class TLRecentMeUrlStickerSet extends TLAbsRecentMeUrl {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbc0a57dc;

    /**
     * URL
     */
    private String url;
    
    /**
     * Stickerset
     */
    private TLAbsStickerSetCovered set;

    public TLRecentMeUrlStickerSet() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public TLAbsStickerSetCovered getSet() {
        return set;
    }

    public void setSet(TLAbsStickerSetCovered set) {
        this.set = set;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.url, stream);
        StreamingUtils.writeTLObject(this.set, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.url = StreamingUtils.readTLString(stream);
        this.set = StreamingUtils.readTLObject(stream, context, TLAbsStickerSetCovered.class);
    }

    @Override
    public String toString() {
        return "recentMeUrlStickerSet#bc0a57dc";
    }

}