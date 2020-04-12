package org.javagram.api.message.base.input;

/**
 * Pinned message
 * inputMessagePinned#86872538 = InputMessage;
 */
public class TLInputMessagePinned extends TLAbsInputMessage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x86872538;

    public TLInputMessagePinned() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputMessagePinned#86872538";
    }

}