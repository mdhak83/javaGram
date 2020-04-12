package org.telegram.api.messages.base.stickers.setintallresult;

/**
 * @author Ruben Bermudez
 * @version 1.0
 *  @since 08/AUG/2016
 */
public class TLMessagesStickerSetInstallResultSuccess extends TLAbsMessagesStickerSetInstallResult {
    public static final int CLASS_ID = 0x38641628;

    public TLMessagesStickerSetInstallResultSuccess() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messages.stickerSetInstallResultSuccess#38641628";
    }

}
