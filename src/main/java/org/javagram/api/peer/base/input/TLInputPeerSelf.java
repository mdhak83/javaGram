package org.javagram.api.peer.base.input;

/**
 * Defines the current user.
 * inputPeerSelf#7da07ec9 = InputPeer;
 */
public class TLInputPeerSelf extends TLAbsInputPeer {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7da07ec9;

    public TLInputPeerSelf() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputPeerSelf#7da07ec9";
    }
    
}