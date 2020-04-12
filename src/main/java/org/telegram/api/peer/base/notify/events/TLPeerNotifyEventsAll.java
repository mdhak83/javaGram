package org.telegram.api.peer.base.notify.events;

/**
 * The type TL peer notify events all.
 */
public class TLPeerNotifyEventsAll extends TLAbsPeerNotifyEvents {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6d1ded88;

    public TLPeerNotifyEventsAll() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "peerNotifyEventsAll#6d1ded88";
    }

}