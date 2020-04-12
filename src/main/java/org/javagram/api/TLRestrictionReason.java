package org.javagram.api;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Restriction reason.
 * Contains the reason why access to a certain object must be restricted.
 * Clients are supposed to deny access to the channel if the <code>platform</code> field is equal to <code>all</code> or to the current platform (<code>ios</code>, <code>android</code>, <code>wp</code>, etc.).
 * Platforms can be concatenated (<code>ios-android</code>, <code>ios-wp</code>), unknown platforms are to be ignored.
 * The <code>text</code> is the error message that should be shown to the user.
 * restrictionReason#d072acb4 platform:string reason:string text:string = RestrictionReason;
 */
public class TLRestrictionReason extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd072acb4;
    
    /**
     * Platform identifier (ios, android, wp, all, etc.), can be concatenated with a dash as separator (<code>android-ios</code>, <code>ios-wp</code>, etc)
     */
    private String platform;
    
    /**
     * Restriction reason (<code>porno</code>, <code>terms</code>, etc.)
     */
    private String reason;
    
    /**
     * Error message to be shown to the user
     */
    private String text;

    public TLRestrictionReason() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.platform, stream);
        StreamingUtils.writeTLString(this.reason, stream);
        StreamingUtils.writeTLString(this.text, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.platform = StreamingUtils.readTLString(stream);
        this.reason = StreamingUtils.readTLString(stream);
        this.text = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "restrictionReason#d072acb4";
    }

}