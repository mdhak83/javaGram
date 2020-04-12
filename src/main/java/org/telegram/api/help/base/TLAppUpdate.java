package org.telegram.api.help.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.document.base.TLAbsDocument;
import org.telegram.api.message.base.entity.TLAbsMessageEntity;
import org.telegram.api._primitives.TLVector;

/**
 * An update is available for the application.
 */
public class TLAppUpdate extends TLAbsHelpAppUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1da7158f;

    private static final int FLAG_CAN_NOT_SKIP  = 0x00000001; // 0 : Unskippable, the new info must be shown to the user (with a popup or something else)
    private static final int FLAG_DOCUMENT      = 0x00000002; // 1 : Attached document
    private static final int FLAG_URL           = 0x00000004; // 2 : Application download URL

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;
    
    /**
     * Update ID
     */
    private int id;
    
    /**
     * New version name
     */
    private String version;
    
    /**
     * Text description of the update
     */
    private String text;
    
    /**
     * @see <a href="https://core.telegram.org/api/entities">Message entities for styled text</a>
     */
    private TLVector<TLAbsMessageEntity> entities;
    
    /**
     * Attached document
     */
    private TLAbsDocument document;
    
    /**
     * Application download URL
     */
    private String url;

    public TLAppUpdate() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TLVector<TLAbsMessageEntity> getEntities() {
        return entities;
    }

    public void setEntities(TLVector<TLAbsMessageEntity> entities) {
        this.entities = entities;
    }

    public TLAbsDocument getDocument() {
        return document;
    }

    public void setDocument(TLAbsDocument document) {
        this.document = document;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeTLString(this.version, stream);
        StreamingUtils.writeTLString(this.text, stream);
        StreamingUtils.writeTLVector(this.entities, stream);
        if ((this.flags & FLAG_DOCUMENT) != 0) {
            StreamingUtils.writeTLObject(this.document, stream);
        }
        if ((this.flags & FLAG_URL) != 0) {
            StreamingUtils.writeTLString(this.url, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        this.version = StreamingUtils.readTLString(stream);
        this.text = StreamingUtils.readTLString(stream);
        this.entities =  StreamingUtils.readTLVector(stream, context, TLAbsMessageEntity.class);
        if ((this.flags & FLAG_DOCUMENT) != 0) {
            this.document = StreamingUtils.readTLObject(stream, context, TLAbsDocument.class);
        }
        if ((this.flags & FLAG_URL) != 0) {
            this.url = StreamingUtils.readTLString(stream);
        }
    }

    @Override
    public String toString() {
        return "help.appUpdate#1da7158f";
    }

}