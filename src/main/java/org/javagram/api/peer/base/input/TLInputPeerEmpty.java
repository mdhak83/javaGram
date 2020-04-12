package org.javagram.api.peer.base.input;

/**
 * An empty constructor, no user or chat is defined.
 * inputPeerEmpty#7f3b18ea = InputPeer;
 */
public class TLInputPeerEmpty extends TLAbsInputPeer {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7f3b18ea;

    public TLInputPeerEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputPeerEmpty#7f3b18ea";
    }

}