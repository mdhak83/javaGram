package org.telegram.api.notify.base.peer;

/**
 * Channel notification settings
 * notifyBroadcasts#d612e8ef = NotifyPeer;
 */
public class TLNotifyBroadcasts extends TLAbsNotifyPeer {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd612e8ef;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "notifyBroadcasts#d612e8ef";
    }

}