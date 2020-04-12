package org.javagram.api.message.base.action;

/**
 * A contact just signed up to telegram
 * messageActionContactSignUp#f3f25f76 = MessageAction;
 */
public class TLMessageActionContactSignUp extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf3f25f76;

    public TLMessageActionContactSignUp() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messageActionContactSignUp#f3f25f76";
    }

}