package org.telegram.api.bot.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 *  * @since 07/JUL/2015
 */
public class TLBotInfo extends TLObject {
    public static final int CLASS_ID = 0x98e81d3a;

    private int userId;
    private String description;
    private TLVector<TLBotCommand> commands = new TLVector<>();

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TLVector<TLBotCommand> getCommands() {
        return this.commands;
    }

    public void setCommands(TLVector<TLBotCommand> commands) {
        this.commands = commands;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeTLVector(this.commands, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.userId = StreamingUtils.readInt(stream);
        this.description = StreamingUtils.readTLString(stream);
        this.commands = StreamingUtils.readTLVector(stream, context, TLBotCommand.class);
    }

    @Override
    public String toString() {
        return "bot.BotInfo#98e81d3a";
    }

}
