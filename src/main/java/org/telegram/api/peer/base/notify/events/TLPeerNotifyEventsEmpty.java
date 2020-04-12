package org.telegram.api.peer.base.notify.events;

/**
 * The type TL peer notify events empty.
 */
public class TLPeerNotifyEventsEmpty extends TLAbsPeerNotifyEvents {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xadd53cb3;

    public TLPeerNotifyEventsEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "peerNotifyEventsEmpty#add53cb3";
    }

}