package org.telegram.api.webpage.base;

import org.telegram.api.document.base.TLAbsDocument;
import org.telegram.api.page.base.TLAbsPage;
import org.telegram.api.photo.base.TLAbsPhoto;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.wallpaper.base.TLThemeSettings;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;

/**
 * Webpage attributes
 */
public class TLWebPageAttributeTheme extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x54b56617;

    private static final int FLAG_DOCUMENTS = 0x00000001; // 0
    private static final int FLAG_SETTINGS  = 0x00000002; // 1

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;
    
    /**
     * Theme files
     */
    private TLVector<TLAbsDocument> documents;
    
    /**
     * Theme settings
     */
    private TLThemeSettings settings;

    public TLWebPageAttributeTheme() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if ((this.flags & FLAG_DOCUMENTS) != 0) {
            StreamingUtils.writeTLVector(this.documents, stream);
        }
        if ((this.flags & FLAG_SETTINGS) != 0) {
            StreamingUtils.writeTLObject(this.settings, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_DOCUMENTS) != 0) {
            this.documents = StreamingUtils.readTLVector(stream, context, TLAbsDocument.class);
        }
        if ((this.flags & FLAG_SETTINGS) != 0) {
            this.settings = StreamingUtils.readTLObject(stream, context, TLThemeSettings.class);
        }
    }

    @Override
    public String toString() {
        return "webPageAttributeTheme#54b56617";
    }

}