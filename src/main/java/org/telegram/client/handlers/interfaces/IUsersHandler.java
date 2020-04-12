package org.telegram.client.handlers.interfaces;

import java.util.List;
import org.telegram.api.user.base.TLAbsUser;

public interface IUsersHandler {
    void onUsers(List<TLAbsUser> users);
    void onUsers(List<TLAbsUser> users, boolean updateAccessHash);
}
