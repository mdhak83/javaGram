package org.telegram.api.messages.base.input.filter;

/**
 * Return only messages where the current user was mentioned
 * inputMessagesFilterMyMentions#c1f8e69a = MessagesFilter;
 */
public class TLMessagesFilterMyMentions extends TLAbsMessagesFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc1f8e69a;

    public TLMessagesFilterMyMentions() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputMessagesFilterMyMentions#c1f8e69a";
    }

}