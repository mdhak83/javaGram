package org.telegram.api.help.base;

/**
 * No updates are available for the application.
 * help.noAppUpdate#c45a6536 = help.AppUpdate;
 */
public class TLHelpNoAppUpdate extends TLAbsHelpAppUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc45a6536;

    public TLHelpNoAppUpdate() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "help.noAppUpdate#c45a6536";
    }

}