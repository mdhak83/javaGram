package org.javagram.api.account.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.theme.base.TLTheme;
import org.javagram.api.theme.base.input.TLAbsInputTheme;

/**
 * Get theme information
 * account.getTheme#8d9d742b format:string theme:InputTheme document_id:long = Theme;
 */
public class TLRequestAccountGetTheme extends TLMethod<TLTheme> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8d9d742b;

    /**
     * Theme format, a string that identifies the theming engines supported by the client
     */
    private String format;
    
    /**
     * Theme
     */
    private TLAbsInputTheme theme;
    
    /**
     * Document ID
     */
    private long documentId;

    public TLRequestAccountGetTheme() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public TLAbsInputTheme getTheme() {
        return theme;
    }

    public void setTheme(TLAbsInputTheme theme) {
        this.theme = theme;
    }

    public long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(long documentId) {
        this.documentId = documentId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.format, stream);
        StreamingUtils.writeTLObject(this.theme, stream);
        StreamingUtils.writeLong(this.documentId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.format = StreamingUtils.readTLString(stream);
        this.theme = StreamingUtils.readTLObject(stream, context, TLAbsInputTheme.class);
        this.documentId = StreamingUtils.readLong(stream);
    }

    @Override
    public TLTheme deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLTheme) {
            return (TLTheme) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.peer.notify.settings.TLAbsPeerNotifySettings, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.getTheme#8d9d742b";
    }

}