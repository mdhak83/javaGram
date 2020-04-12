package org.javagram.client.handlers.interfaces;

import java.util.List;
import org.javagram.api.chat.base.TLAbsChat;

public interface IChatsHandler {
    void onChats(List<TLAbsChat> chats);
    void onChats(List<TLAbsChat> chats, boolean updateAccessHash);
}
