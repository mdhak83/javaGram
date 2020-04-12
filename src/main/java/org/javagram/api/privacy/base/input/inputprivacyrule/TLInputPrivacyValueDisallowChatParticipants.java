package org.javagram.api.privacy.base.input.inputprivacyrule;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;

/**
 * Disallow only participants of certain chats
 * inputPrivacyValueDisallowChatParticipants#d82363af chats:Vector&lt;int&gt; = InputPrivacyRule;
 */
public class TLInputPrivacyValueDisallowChatParticipants extends TLAbsInputPrivacyRule {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd82363af;

    /**
     * Disallowed chat IDs
     */
    private TLVector<Integer> chats = new TLVector<>();

    public TLInputPrivacyValueDisallowChatParticipants() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<Integer> getChats() {
        return chats;
    }

    public void setChats(TLVector<Integer> chats) {
        this.chats = chats;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.chats, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.chats = StreamingUtils.readTLIntVector(stream, context);
    }

    @Override
    public String toString() {
        return "inputPrivacyValueDisallowChatParticipants#d82363af";
    }

}