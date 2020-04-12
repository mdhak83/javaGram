package org.telegram.api.messages.base.input.entity;

import org.telegram.api.user.base.input.TLAbsInputUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @since 07/AUG/2016
 */
public class TLInputMessageEntityMentionName extends TLAbsInputMessageEntity {
    public static final int CLASS_ID = 0x208e68c9;

    private int offset;
    private int length;
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

    public TLAbsInputUser getUserId() {
        return userId;
    }

    public void setUserId(TLAbsInputUser userId) {
        this.userId = userId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(offset, stream);
        StreamingUtils.writeInt(length, stream);
        StreamingUtils.writeTLObject(userId, stream);
    }

    /*
     offset:int length:int user_id:InputUser
     */

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        offset = StreamingUtils.readInt(stream);
        length = StreamingUtils.readInt(stream);
        userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
    }

    @Override
    public String toString() {
        return "inputMessageEntityMentionName#208e68c9";
    }

}
