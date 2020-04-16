package org.javagram.client.handlers.interfaces;

import java.util.List;
import org.javagram.api.user.base.TLAbsUser;

public interface IUsersHandler {
    void onUsers(List<TLAbsUser> users);
}
