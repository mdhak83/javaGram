package org.javagram.api.contacts.base.toppeers;

/**
 * Top peers disabled
 * contacts.topPeersDisabled#b52c939d = contacts.TopPeers;
 */
public class TLContactsTopPeersDisabled extends TLAbsContactsTopPeers {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb52c939d;

    public TLContactsTopPeersDisabled() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "contacts.topPeersDisabled#b52c939d";
    }
    
}