package org.telegram.client.handlers.interfaces;

import java.util.List;
import org.telegram.api.chat.base.TLAbsChat;

public interface IChatsHandler {
    void onChats(List<TLAbsChat> chats);
    void onChats(List<TLAbsChat> chats, boolean updateAccessHash);
}
