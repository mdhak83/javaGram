package org.telegram.api.peer.base.input.notify;

/**
 * The type TL input notify users.
 */
public class TLInputNotifyUsers extends TLAbsInputNotifyPeer {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x193b4417;

    public TLInputNotifyUsers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputNotifyUsers#193b4417";
    }

}