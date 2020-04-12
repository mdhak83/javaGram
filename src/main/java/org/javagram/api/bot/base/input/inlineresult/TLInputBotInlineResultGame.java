package org.javagram.api.bot.base.input.inlineresult;

import org.javagram.api.bot.base.input.inlinemessage.TLAbsInputBotInlineMessage;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * TLInputBotInlineResultGame
 *  @since 13/FEB/2016
 */
public class TLInputBotInlineResultGame extends TLAbsInputBotInlineResult {
    public static final int CLASS_ID = 0x4fa417f2;

    private String id;
    private String shortName;
    private TLAbsInputBotInlineMessage sendMessage;

    public TLInputBotInlineResultGame() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public TLAbsInputBotInlineMessage getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(TLAbsInputBotInlineMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.id, stream);
        StreamingUtils.writeTLString(this.shortName, stream);
        StreamingUtils.writeTLObject(this.sendMessage, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readTLString(stream);
        this.shortName = StreamingUtils.readTLString(stream);
        this.sendMessage = StreamingUtils.readTLObject(stream, context, TLAbsInputBotInlineMessage.class);
    }

    @Override
    public String toString() {
        return "inputBotInlineResultGame#4fa417f2";
    }

}
