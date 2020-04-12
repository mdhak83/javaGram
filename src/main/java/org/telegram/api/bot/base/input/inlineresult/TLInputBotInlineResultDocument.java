package org.telegram.api.bot.base.input.inlineresult;

import org.telegram.api.bot.base.input.inlinemessage.TLAbsInputBotInlineMessage;
import org.telegram.api.document.base.input.TLAbsInputDocument;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
  * @since 13/FEB/2016
 */
public class TLInputBotInlineResultDocument extends TLAbsInputBotInlineResult {
    public static final int CLASS_ID = 0xfff8fdc4;

    private static final int FLAG_UNUSED0         = 0x00000001; // 0
    private static final int FLAG_TITLE           = 0x00000002; // 1
    private static final int FLAG_DESCRIPTION     = 0x00000004; // 2
    private static final int FLAG_UNUSED3         = 0x00000008; // 3
    private static final int FLAG_UNUSED4         = 0x00000010; // 4
    private static final int FLAG_UNUSED5         = 0x00000020; // 5
    private static final int FLAG_UNUSED6         = 0x00000040; // 6
    private static final int FLAG_UNUSED7         = 0x00000080; // 7

    private String id;
    private String type;
    private String title;
    private String description;
    private TLAbsInputDocument document;
    private TLAbsInputBotInlineMessage sendMessage;

    public TLInputBotInlineResultDocument() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TLAbsInputDocument getDocument() {
        return document;
    }

    public TLAbsInputBotInlineMessage getSendMessage() {
        return sendMessage;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.id, stream);
        StreamingUtils.writeTLString(this.type, stream);
        if ((flags & FLAG_TITLE) != 0) {
            StreamingUtils.writeTLString(this.title, stream);
        }
        if ((flags & FLAG_DESCRIPTION) != 0) {
            StreamingUtils.writeTLString(this.description, stream);
        }
        StreamingUtils.writeTLObject(this.document, stream);
        StreamingUtils.writeTLObject(this.sendMessage, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readTLString(stream);
        this.type = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_TITLE) != 0) {
            this.title = StreamingUtils.readTLString(stream);
        }
        if ((flags & FLAG_DESCRIPTION) != 0) {
            this.description = StreamingUtils.readTLString(stream);
        }
        this.document = StreamingUtils.readTLObject(stream, context, TLAbsInputDocument.class);
        this.sendMessage = StreamingUtils.readTLObject(stream, context, TLAbsInputBotInlineMessage.class);
    }

    @Override
    public String toString() {
        return "inputBotInlineResultDocument#fff8fdc4";
    }

}
