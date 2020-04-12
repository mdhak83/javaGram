package org.javagram.api.contacts.base.toppeers;

/**
 * Top peer info hasn't changed
 * contacts.topPeersNotModified#de266ef5 = contacts.TopPeers;
 */
public class TLContactsTopPeersNotModified extends TLAbsContactsTopPeers {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xde266ef5;

    public TLContactsTopPeersNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "contacts.topPeersNotModified#de266ef5";
    }

}