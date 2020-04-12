package org.javagram.api.messages.base.input.filter;

/**
 * Return only messages containing contacts
 * inputMessagesFilterContacts#e062db83 = MessagesFilter;
 */
public class TLMessagesFilterContacts extends TLAbsMessagesFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe062db83;

    public TLMessagesFilterContacts() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputMessagesFilterContacts#e062db83";
    }

}