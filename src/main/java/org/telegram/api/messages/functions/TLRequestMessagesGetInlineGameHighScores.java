package org.telegram.api.messages.functions;

import org.telegram.api.bot.base.input.TLInputBotInlineMessageId;
import org.telegram.api.user.base.input.TLAbsInputUser;
import org.telegram.api.messages.base.TLMessagesHighScores;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages accept encryption.
 */
public class TLRequestMessagesGetInlineGameHighScores extends TLMethod<TLMessagesHighScores> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf635e1b;

    private TLInputBotInlineMessageId id;
    private TLAbsInputUser userId;

    public TLRequestMessagesGetInlineGameHighScores() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLMessagesHighScores deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if (res instanceof TLMessagesHighScores)
            return (TLMessagesHighScores) res;
        throw new IOException("Incorrect response type. Expected " + TLMessagesHighScores.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    public TLInputBotInlineMessageId getId() {
        return id;
    }

    public void setId(TLInputBotInlineMessageId id) {
        this.id = id;
    }

    public TLAbsInputUser getUserId() {
        return userId;
    }

    public void setUserId(TLAbsInputUser userId) {
        this.userId = userId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(id, stream);
        StreamingUtils.writeTLObject(userId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        id = StreamingUtils.readTLObject(stream, context, TLInputBotInlineMessageId.class);
        userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
    }

    @Override
    public String toString() {
        return "messages.getInlineGameHighScores#f635e1b";
    }

}