package org.telegram.api.bot.base.inlineresult;

import org.telegram.api.bot.base.inlinemessage.TLAbsBotInlineMessage;
import org.telegram.api.document.base.TLAbsDocument;
import org.telegram.api.photo.base.TLAbsPhoto;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLBotInlineMediaResult extends TLAbsBotInlineResult {
    public static final int CLASS_ID = 0x17db940b;

    private static final int FLAG_PHOTO       = 0x00000001; // 0
    private static final int FLAG_DOCUMENT    = 0x00000002; // 1
    private static final int FLAG_TITLE       = 0x00000004; // 2
    private static final int FLAG_DESCRIPTION = 0x00000008; // 3

    private String id;
    private String type;
    private TLAbsPhoto photo;
    private TLAbsDocument document;
    private String title;
    private String description;
    private TLAbsBotInlineMessage sendMessage;

    public TLBotInlineMediaResult() {
        super();
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public TLAbsPhoto getPhoto() {
        return photo;
    }

    public TLAbsBotInlineMessage getSendMessage() {
        return sendMessage;
    }

    public TLAbsDocument getDocument() {
        return document;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.id, stream);
        StreamingUtils.writeTLString(this.type, stream);
        if ((flags & FLAG_PHOTO) != 0) {
            StreamingUtils.writeTLObject(this.photo, stream);
        }
        if ((flags & FLAG_DOCUMENT) != 0) {
            StreamingUtils.writeTLObject(this.document, stream);
        }
        if ((flags & FLAG_TITLE) != 0) {
            StreamingUtils.writeTLString(this.title, stream);
        }
        if ((flags & FLAG_DESCRIPTION) != 0) {
            StreamingUtils.writeTLString(this.description, stream);
        }
        StreamingUtils.writeTLObject(this.sendMessage, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readTLString(stream);
        this.type = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_PHOTO) != 0) {
            this.photo = StreamingUtils.readTLObject(stream, context, TLAbsPhoto.class);
        }
        if ((flags & FLAG_DOCUMENT) != 0) {
            this.document = StreamingUtils.readTLObject(stream, context, TLAbsDocument.class);
        }
        if ((flags & FLAG_TITLE) != 0) {
            this.title = StreamingUtils.readTLString(stream);
        }
        if ((flags & FLAG_DESCRIPTION) != 0) {
            this.description = StreamingUtils.readTLString(stream);
        }
        this.sendMessage = StreamingUtils.readTLObject(stream, context, TLAbsBotInlineMessage.class);
    }

    @Override
    public String toString() {
        return "botInlineMediaResult#17db940b";
    }

}
