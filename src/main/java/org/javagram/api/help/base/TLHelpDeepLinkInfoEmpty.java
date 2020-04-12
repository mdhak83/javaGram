package org.javagram.api.help.base;

/**
 * Deep link info empty
 * help.deepLinkInfoEmpty#66afa166 = help.DeepLinkInfo;
 */
public class TLHelpDeepLinkInfoEmpty extends TLAbsHelpDeepLinkInfo {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x66afa166;

    public TLHelpDeepLinkInfoEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "help.deepLinkInfoEmpty#66afa166";
    }

}