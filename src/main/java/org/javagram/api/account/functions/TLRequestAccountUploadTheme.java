package org.javagram.api.account.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.document.base.TLAbsDocument;
import org.javagram.api.file.base.input.TLAbsInputFile;

/**
 * Upload theme
 * account.uploadTheme#1c3db333 flags:# file:InputFile thumb:flags.0?InputFile file_name:string mime_type:string = Document;
 */
public class TLRequestAccountUploadTheme extends TLMethod<TLAbsDocument> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1c3db333;

    private static final int FLAG_THUMB = 0x00000001; // 0 : Thumbnail

    /**
     * Theme file uploaded as described in @see <a href="https://core.telegram.org/api/files">files »</a>
     */
    private TLAbsInputFile file;
    
    /**
     * Thumbnail
     */
    private TLAbsInputFile thumb;
    
    /**
     * File name
     */
    private String fileName;
    
    /**
     * MIME type, must be <code>application/x-tgtheme-{format}</code>, where <code>format</code> depends on the client
     */
    private String mimeType;
    

    public TLRequestAccountUploadTheme() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public TLAbsInputFile getFile() {
        return file;
    }

    public void setFile(TLAbsInputFile file) {
        this.file = file;
    }

    public boolean hasThumb() {
        return this.isFlagSet(FLAG_THUMB);
    }

    public TLAbsInputFile getThumb() {
        return thumb;
    }

    public void setThumb(TLAbsInputFile thumb) {
        this.thumb = thumb;
        this.setFlag(FLAG_THUMB, this.thumb != null);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.file, stream);
        if (this.hasThumb()) {
            StreamingUtils.writeTLObject(this.thumb, stream);
        }
        StreamingUtils.writeTLString(this.fileName, stream);
        StreamingUtils.writeTLString(this.mimeType, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.file = StreamingUtils.readTLObject(stream, context, TLAbsInputFile.class);
        if (this.hasThumb()) {
            this.thumb = StreamingUtils.readTLObject(stream, context, TLAbsInputFile.class);
        }
        this.fileName = StreamingUtils.readTLString(stream);
        this.mimeType = StreamingUtils.readTLString(stream);
    }

    @Override
    public TLAbsDocument deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsDocument) {
            return (TLAbsDocument) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.peer.notify.settings.TLAbsPeerNotifySettings, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.uploadTheme#1c3db333";
    }

}