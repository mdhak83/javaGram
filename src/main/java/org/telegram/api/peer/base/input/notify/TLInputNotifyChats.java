package org.telegram.api.peer.base.input.notify;

/**
 * The type TL input notify chats.
 */
public class TLInputNotifyChats extends TLAbsInputNotifyPeer {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4a95e84e;

    public TLInputNotifyChats() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputNotifyChats#4a95e84e";
    }

}