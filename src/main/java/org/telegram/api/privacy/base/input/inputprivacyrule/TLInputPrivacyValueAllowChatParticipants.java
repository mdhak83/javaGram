package org.telegram.api.privacy.base.input.inputprivacyrule;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;

/**
 * Allow only participants of certain chats
 * inputPrivacyValueAllowChatParticipants#4c81c1ba chats:Vector&lt;int&gt; = InputPrivacyRule;
 */
public class TLInputPrivacyValueAllowChatParticipants extends TLAbsInputPrivacyRule {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4c81c1ba;

    /**
     * Allowed chat IDs.
     */
    private TLVector<Integer> chats = new TLVector<>();

    public TLInputPrivacyValueAllowChatParticipants() {
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
        return "inputPrivacyValueAllowChatParticipants#4c81c1ba";
    }

}