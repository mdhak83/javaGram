package org.javagram.client.handlers;

import java.util.List;
import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.MyTLAppConfiguration;
import org.javagram.client.handlers.interfaces.IChatsHandler;

public abstract class AbstractChatsHandler implements IChatsHandler {

    private MyTLAppConfiguration config;
    
    public AbstractChatsHandler() {
    }
    
    public void build(MyTLAppConfiguration config) {
        this.config = config;
    }
    
    @Override
    public final void onChats(List<TLAbsChat> chats) {
        this.onChats(chats, false);
    }
    
    @Override
    public final void onChats(List<TLAbsChat> chats, boolean updateAccessHash) {
        chats.stream().filter((chat) -> (chat != null)).forEachOrdered((chat) -> {
            this.config.getDatabaseManager().processChat(chat, updateAccessHash);
        });
        this.onChatsCustom(chats);
    }
    
    protected abstract void onChatsCustom(List<TLAbsChat> chats);
    protected abstract void onChatCustom(TLAbsChat chat);

}