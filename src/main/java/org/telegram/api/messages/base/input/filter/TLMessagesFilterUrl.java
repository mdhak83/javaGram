package org.telegram.api.messages.base.input.filter;

/**
 * Return only messages containing URLs
 * inputMessagesFilterUrl#7ef0dd87 = MessagesFilter;
 */
public class TLMessagesFilterUrl extends TLAbsMessagesFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7ef0dd87;

    public TLMessagesFilterUrl() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputMessagesFilterUrl#7ef0dd87";
    }
    
}