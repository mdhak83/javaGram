package org.javagram.api.messages.functions;

import org.javagram.api.bot.base.input.TLInputBotInlineMessageId;
import org.javagram.api.user.base.input.TLAbsInputUser;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages accept encryption.
 */
public class TLRequestMessagesSetInlineGameScore extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x15ad9f64;

    private static final int FLAG_EDITMESSAGE    = 0x00000001; // 0
    private static final int FLAG_FORCE          = 0x00000002; // 1

    private TLInputBotInlineMessageId id;
    private TLAbsInputUser userId;
    private int score;

    public TLRequestMessagesSetInlineGameScore() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if (res instanceof TLBool)
            return (TLBool) res;
        throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void enableEditMessage(boolean enabled) {
        this.setFlag(FLAG_EDITMESSAGE, enabled);
    }

    public void enableForce(boolean enabled) {
        this.setFlag(FLAG_FORCE, enabled);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.id, stream);
        StreamingUtils.writeTLObject(this.userId, stream);
        StreamingUtils.writeInt(this.score, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readTLObject(stream, context, TLInputBotInlineMessageId.class);
        this.userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
        this.score = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messages.setInlineGameScore#15ad9f64";
    }

}