package org.telegram.api.update.base;

import org.telegram.api.webpage.base.TLAbsWebPage;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update chat user typing.
 */
public class TLUpdateChannelWebPage extends TLAbsUpdate implements TLItfChannelUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x40771900;

    private int channelId;
    private TLAbsWebPage webPage;
    private int pts;
    private int ptsCount;

    public TLUpdateChannelWebPage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public int getChannelId() {
        return channelId;
    }

    public TLAbsWebPage getWebPage() {
        return webPage;
    }

    @Override
    public int getPts() {
        return pts;
    }

    @Override
    public int getPtsCount() {
        return ptsCount;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.channelId, stream);
        StreamingUtils.writeTLObject(this.webPage, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channelId = StreamingUtils.readInt(stream);
        this.webPage = StreamingUtils.readTLObject(stream, context, TLAbsWebPage.class);
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updateChannelWebPage#40771900";
    }

}