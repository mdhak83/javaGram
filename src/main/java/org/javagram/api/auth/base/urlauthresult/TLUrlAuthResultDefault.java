package org.javagram.api.auth.base.urlauthresult;

/**
 * Default authorization request, for more info @see <a href="https://core.telegram.org/api/url-authorization">click here »</a>
 * urlAuthResultDefault#a9d6db1f = UrlAuthResult;
 */
public class TLUrlAuthResultDefault extends TLAbsUrlAuthResult {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa9d6db1f;

    public TLUrlAuthResultDefault() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "urlAuthResultDefault#a9d6db1f";
    }

}