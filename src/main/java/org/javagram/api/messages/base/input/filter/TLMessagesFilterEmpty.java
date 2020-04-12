package org.javagram.api.messages.base.input.filter;

/**
 * Filter is absent.
 * inputMessagesFilterEmpty#57e2f66c = MessagesFilter;
 */
public class TLMessagesFilterEmpty extends TLAbsMessagesFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x57e2f66c;

    public TLMessagesFilterEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputMessagesFilterEmpty#57e2f66c";
    }

}