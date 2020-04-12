package org.telegram.api.peer.base.input.notify;

/**
 * All @see <a href="https://core.telegram.org/api/channel">channels</a>
 * inputNotifyBroadcasts#b1db7c7e = InputNotifyPeer;
 */
public class TLInputNotifyBroadcasts extends TLAbsInputNotifyPeer {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb1db7c7e;

    public TLInputNotifyBroadcasts() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputNotifyBroadcasts#b1db7c7e";
    }
    
}