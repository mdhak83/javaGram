package org.telegram.api.bot.base.input.inlineresult;

import org.telegram.api.bot.base.input.inlinemessage.TLAbsInputBotInlineMessage;
import org.telegram.api.photo.base.input.TLAbsInputPhoto;
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
public class TLInputBotInlineResultPhoto extends TLAbsInputBotInlineResult {
    public static final int CLASS_ID = 0xa8d864a7;

    private String id;
    private String type;
    private TLAbsInputPhoto photo;
    private TLAbsInputBotInlineMessage sendMessage;

    public TLInputBotInlineResultPhoto() {
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

    public TLAbsInputPhoto getPhoto() {
        return photo;
    }

    public TLAbsInputBotInlineMessage getSendMessage() {
        return sendMessage;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.id, stream);
        StreamingUtils.writeTLString(this.type, stream);
        StreamingUtils.writeTLObject(this.photo, stream);
        StreamingUtils.writeTLObject(this.sendMessage, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readTLString(stream);
        this.type = StreamingUtils.readTLString(stream);
        this.photo = StreamingUtils.readTLObject(stream, context,TLAbsInputPhoto.class);
        this.sendMessage = StreamingUtils.readTLObject(stream, context, TLAbsInputBotInlineMessage.class);
    }

    @Override
    public String toString() {
        return "inputBotInlineResultPhoto#a8d864a7";
    }

}
