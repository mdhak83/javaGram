package org.telegram.api.user.base.status;

import org.telegram.api._primitives.TLObject;

/**
 * The UserStatus type.
 */
public abstract class TLAbsUserStatus extends TLObject {

    protected TLAbsUserStatus() {
        super();
    }
    
    public String toLog() {
        return this.getClass().getSimpleName().substring(2);
    }

}