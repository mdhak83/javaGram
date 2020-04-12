package org.javagram.api.messages.base.stickers.found;

/**
 * No further results were found
 * messages.foundStickerSetsNotModified#d54b65d = messages.FoundStickerSets;
 */
public class TLMessagesFoundStickerSetsNotModified extends TLAbsMessagesFoundStickerSets {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd54b65d;

    public TLMessagesFoundStickerSetsNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messages.foundStickerSetsNotModified#d54b65d";
    }

}