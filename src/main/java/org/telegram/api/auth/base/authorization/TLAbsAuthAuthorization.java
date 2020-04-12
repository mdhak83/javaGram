package org.telegram.api.auth.base.authorization;

import org.telegram.api._primitives.TLObject;
import org.telegram.api.user.base.TLAbsUser;

/**
 * auth.Authorization type.
 */
public abstract class TLAbsAuthAuthorization extends TLObject {

    /**
     * Info on authorized user
     */
    protected TLAbsUser user = null;

    protected TLAbsAuthAuthorization() {
        super();
    }
    
    public TLAbsUser getUser() {
        return this.user;
    }



}
