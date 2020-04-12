package org.javagram.api.help.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.message.base.entity.TLAbsMessageEntity;
import org.javagram.api._primitives.TLVector;

/**
 * Internal use
 * help.userInfo#1eb3758 message:string entities:Vector&lt;MessageEntity&gt; author:string date:int = help.UserInfo;
 */
public class TLHelpUserInfo extends TLAbsHelpUserInfo {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1eb3758;

    /**
     * 
     */
    private String message;

    /**
     * @see <a href="https://core.telegram.org/api/entities">Message entities for styled text</a>
     */
    private TLVector<TLAbsMessageEntity> entities;
    
    /**
     * Author
     */
    private String author;
    
    /**
     * Date
     */
    private int date;

    public TLHelpUserInfo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.message, stream);
        StreamingUtils.writeTLVector(this.entities, stream);
        StreamingUtils.writeTLString(this.author, stream);
        StreamingUtils.writeInt(this.date, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.message = StreamingUtils.readTLString(stream);
        this.entities = StreamingUtils.readTLVector(stream, context, TLAbsMessageEntity.class);
        this.author = StreamingUtils.readTLString(stream);
        this.date = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "help.userInfo#1eb3758";
    }

}