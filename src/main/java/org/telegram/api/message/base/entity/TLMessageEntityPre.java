package org.telegram.api.message.base.entity;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Message entity representing a preformatted <code>codeblock</code>, allowing the user to specify a programming language for the codeblock.
 * messageEntityPre#73924be0 offset:int length:int language:string = MessageEntity;
 */
public class TLMessageEntityPre extends TLAbsMessageEntity {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x73924be0;

    /**
     * Offset of message entity within message (in UTF-8 codepoints)
     */
    private int offset;
    
    /**
     * Length of message entity within message (in UTF-8 codepoints)
     */
    private int length;

    /**
     * Programming language of the code
     */
    private String language;

    public TLMessageEntityPre() {
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.offset, stream);
        StreamingUtils.writeInt(this.length, stream);
        StreamingUtils.writeTLString(this.language, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.offset = StreamingUtils.readInt(stream);
        this.length = StreamingUtils.readInt(stream);
        this.language = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "message.entity.MessageEntityPre#73924be0";
    }

}