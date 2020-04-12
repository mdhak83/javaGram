package org.javagram.api.account.base;

/**
 * No new themes were installed
 * account.themesNotModified#f41eb622 = account.Themes;
 */
public class TLAccountThemesNotModified extends TLAbsAccountThemes {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf41eb622;
    
    public TLAccountThemesNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "account.themesNotModified#f41eb622";
    }

}