package org.javagram.api.messages.base.input.filter;

/**
 * Filter for messages containing documents.
 * inputMessagesFilterDocument#9eddf188 = MessagesFilter;
 */
public class TLMessagesFilterDocument extends TLAbsMessagesFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9eddf188;

    public TLMessagesFilterDocument() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputMessagesFilterDocument#9eddf188";
    }
    
}