package org.javagram.api.privacy.base.privacyrule;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;

/**
 * Allow all participants of certain chats
 * privacyValueAllowChatParticipants#18be796b chats:Vector&lt;int&gt; = PrivacyRule;
 */
public class TLPrivacyValueAllowChatParticipants extends TLAbsPrivacyRule {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x18be796b;

    /**
     * Allowed chats
     */
    private TLVector<Integer> chats = new TLVector<>();

    public TLPrivacyValueAllowChatParticipants() {
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
        return "privacyValueAllowChatParticipants#18be796b";
    }

}