package org.javagram.api.help.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.help.base.TLHelpUserInfo;
import org.javagram.api.message.base.entity.TLAbsMessageEntity;
import org.javagram.api.user.base.input.TLAbsInputUser;
import org.javagram.api._primitives.TLVector;

/**
 * Internal use
 * help.editUserInfo#66b91b70 user_id:InputUser message:string entities:Vector&lt;MessageEntity&gt; = help.UserInfo;
 */
public class TLRequestHelpEditUserInfo extends TLMethod<TLHelpUserInfo> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x66b91b70;
    
    /**
     * User
     */
    private TLAbsInputUser userId;
    
    /**
     * Message
     */
    private String message;
    
    /**
     * @see <a href="https://core.telegram.org/api/entities">Message entities for styled text</a>
     */
    private TLVector<TLAbsMessageEntity> entities;

    public TLRequestHelpEditUserInfo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputUser getUserId() {
        return userId;
    }

    public void setUserId(TLAbsInputUser userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TLVector<TLAbsMessageEntity> getEntities() {
        return entities;
    }

    public void setEntities(TLVector<TLAbsMessageEntity> entities) {
        this.entities = entities;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.userId, stream);
        StreamingUtils.writeTLString(this.message, stream);
        StreamingUtils.writeTLVector(this.entities, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
        this.message = StreamingUtils.readTLString(stream);
        this.entities = StreamingUtils.readTLVector(stream, context, TLAbsMessageEntity.class);
    }

    @Override
    public TLHelpUserInfo deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) { 
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLHelpUserInfo) {
            return (TLHelpUserInfo) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.help.TLSupport, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "help.editUserInfo#66b91b70";
    }

}