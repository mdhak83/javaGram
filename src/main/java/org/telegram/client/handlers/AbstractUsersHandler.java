package org.telegram.client.handlers;

import java.util.List;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.MyTLAppConfiguration;
import org.telegram.client.handlers.interfaces.IUsersHandler;

public abstract class AbstractUsersHandler implements IUsersHandler {
    
    private MyTLAppConfiguration config;
    
    public AbstractUsersHandler() {
    }
    
    public void build(MyTLAppConfiguration config) {
        this.config = config;
    }
    
    @Override
    public final void onUsers(List<TLAbsUser> users) {
        this.onUsers(users, true);
    }

    @Override
    public final void onUsers(List<TLAbsUser> users, boolean updateAccessHash) {
        users.stream().filter((user) -> (user != null)).forEachOrdered((user) -> {
            this.config.getDatabaseManager().processUser(user, updateAccessHash);
        });
        this.onUsersCustom(users);
    }
    
    protected abstract void onUsersCustom(List<TLAbsUser> users);
    protected abstract void onUserCustom(TLAbsUser user);

}