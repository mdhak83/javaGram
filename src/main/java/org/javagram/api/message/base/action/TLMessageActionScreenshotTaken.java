package org.javagram.api.message.base.action;

/**
 * A screenshot of the chat was taken
 * messageActionScreenshotTaken#4792929b = MessageAction;
 */
public class TLMessageActionScreenshotTaken extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4792929b;

    public TLMessageActionScreenshotTaken() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messageActionScreenshotTaken#4792929b";
    }

}