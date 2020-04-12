package org.javagram.api.updates.base;

import org.javagram.api.message.base.entity.TLAbsMessageEntity;
import org.javagram.api.message.base.media.TLAbsMessageMedia;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.client.handlers.AbstractUpdatesHandler;

/**
 * The type TL update short sent message.
 */
public class TLUpdateShortSentMessage extends TLAbsUpdates {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x11f1331c;

    private static final int FLAG_UNUSED_0           = 0x00000001; // 0
    private static final int FLAG_OUT                = 0x00000002; // 1
    private static final int FLAG_UNUSED2            = 0x00000004; // 2
    private static final int FLAG_UNUSED3            = 0x00000008; // 3
    private static final int FLAG_UNUSED4            = 0x00000010; // 4
    private static final int FLAG_UNUSED5            = 0x00000020; // 5
    private static final int FLAG_UNUSED6            = 0x00000040; // 6
    private static final int FLAG_ENTITIES           = 0x00000080; // 7
    private static final int FLAG_UNUSED8            = 0x00000100; // 8
    private static final int FLAG_MEDIA              = 0x00000200; // 9

    private int id;
    private int pts;
    private int ptsCount;
    private int date;
    private TLAbsMessageMedia media;
    private TLVector<TLAbsMessageEntity> entities;

    public TLUpdateShortSentMessage() {
        super();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPts() {
        return this.pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public int getDate() {
        return this.date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getPtsCount() {
        return this.ptsCount;
    }

    public void setPtsCount(int ptsCount) {
        this.ptsCount = ptsCount;
    }

    public TLVector<TLAbsMessageEntity> getEntities() {
        return entities;
    }

    public void setEntities(TLVector<TLAbsMessageEntity> entities) {
        this.entities = entities;
    }

    public TLAbsMessageMedia getMedia() {
        return media;
    }

    public void setMedia(TLAbsMessageMedia media) {
        this.media = media;
    }

    public boolean hasMedia() {
        return this.isFlagSet(FLAG_MEDIA);
    }

    public boolean isSent() {
        return this.isFlagSet(FLAG_OUT);
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
        StreamingUtils.writeInt(this.date, stream);
        if ((this.flags & FLAG_MEDIA) != 0) {
            StreamingUtils.writeTLObject(this.media, stream);
        }
        if ((this.flags & FLAG_ENTITIES) != 0) {
            StreamingUtils.writeTLVector(this.entities, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_MEDIA) != 0) {
            this.media = StreamingUtils.readTLObject(stream, context, TLAbsMessageMedia.class);
        }
        if ((this.flags & FLAG_ENTITIES) != 0) {
            this.entities = StreamingUtils.readTLVector(stream, context, TLAbsMessageEntity.class);
        }
    }

    @Override
    public String toString() {
        return "updateShortSentMessage#11f1331c";
    }

    public String toLog() {
        String ret;
        ret = "Message#" + this.id + " - Media : " + (this.media != null ? this.media.toLog() : "---");
        return ret;
    }

}