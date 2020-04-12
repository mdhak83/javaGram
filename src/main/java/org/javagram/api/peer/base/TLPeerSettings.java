package org.javagram.api.peer.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Peer settings
 * peerSettings#818426cd flags:# report_spam:flags.0?true add_contact:flags.1?true block_contact:flags.2?true share_contact:flags.3?true need_contacts_exception:flags.4?true report_geo:flags.5?true = PeerSettings;
 */
public class TLPeerSettings extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x818426cd;

    private static final int FLAG_REPORT_SPAM               = 0x00000001; // 0 : Whether we can still report the user for spam
    private static final int FLAG_ADD_CONTACT               = 0x00000002; // 1 : Whether we can add the user as contact
    private static final int FLAG_BLOCK_CONTACT             = 0x00000004; // 2 : Whether we can block the user
    private static final int FLAG_SHARE_CONTACT             = 0x00000008; // 3 : Whether we can share the user's contact
    private static final int FLAG_NEED_CONTACTS_EXCEPTION   = 0x00000010; // 4 : Whether a special exception for contacts is needed
    private static final int FLAG_REPORT_GEO                = 0x00000020; // 5 : Whether we can report a geogroup is irrelevant for this location

    public TLPeerSettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public boolean isReportSpam() {
        return this.isFlagSet(FLAG_REPORT_SPAM);
    }

    public void setReportSpam(boolean value) {
        this.setFlag(FLAG_REPORT_SPAM, value);
    }

    public boolean isAddContact() {
        return this.isFlagSet(FLAG_ADD_CONTACT);
    }

    public void setAddContact(boolean value) {
        this.setFlag(FLAG_ADD_CONTACT, value);
    }

    public boolean isBlockContact() {
        return this.isFlagSet(FLAG_BLOCK_CONTACT);
    }

    public void setBlockContact(boolean value) {
        this.setFlag(FLAG_BLOCK_CONTACT, value);
    }

    public boolean isShareContact() {
        return this.isFlagSet(FLAG_SHARE_CONTACT);
    }

    public void setShareContact(boolean value) {
        this.setFlag(FLAG_SHARE_CONTACT, value);
    }

    public boolean isContactsExceptionNeeded() {
        return this.isFlagSet(FLAG_NEED_CONTACTS_EXCEPTION);
    }

    public void setContactsExceptionNeeded(boolean value) {
        this.setFlag(FLAG_NEED_CONTACTS_EXCEPTION, value);
    }

    public boolean isReportGeo() {
        return this.isFlagSet(FLAG_REPORT_GEO);
    }

    public void setReportGeo(boolean value) {
        this.setFlag(FLAG_REPORT_GEO, value);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "peerSettings#818426cd";
    }

}