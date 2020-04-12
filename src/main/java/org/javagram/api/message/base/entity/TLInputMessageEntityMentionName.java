package org.javagram.api.message.base.entity;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.user.base.input.TLAbsInputUser;

/**
 * Message entity that can be used to create a <a href="https://t.me/test">user mention</a>: received mentions use the @see <a href="https://core.telegram.org/constructor/messageEntityMentionName">messageEntityMentionName</a> constructor, instead.
 * inputMessageEntityMentionName#208e68c9 offset:int length:int user_id:InputUser = MessageEntity;
 */
public class TLInputMessageEntityMentionName extends TLAbsMessageEntity {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x208e68c9;

    /**
     * Offset of message entity within message (in UTF-8 codepoints)
     */
    private int offset;
    
    /**
     * Length of message entity within message (in UTF-8 codepoints)
     */
    private int length;

    /**
     * Identifier of the user that was mentioned
     */
    private TLAbsInputUser userId;

    public TLInputMessageEntityMentionName() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.offset, stream);
        StreamingUtils.writeInt(this.length, stream);
        StreamingUtils.writeTLObject(this.userId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.offset = StreamingUtils.readInt(stream);
        this.length = StreamingUtils.readInt(stream);
        this.userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
    }

    @Override
    public String toString() {
        return "inputMessageEntityMentionName#208e68c9";
    }

}