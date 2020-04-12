package org.telegram.client.handlers;

import java.util.List;
import org.telegram.api.message.base.TLAbsMessage;
import org.telegram.MyTLAppConfiguration;
import org.telegram.client.handlers.interfaces.IMessagesHandler;

public abstract class AbstractMessagesHandler implements IMessagesHandler {

    protected MyTLAppConfiguration config;
    
    public AbstractMessagesHandler() {
    }
    
    public void build(MyTLAppConfiguration config) {
        this.config = config;
    }
    
    @Override
    public final void onMessages(List<TLAbsMessage> messages) {
        this.onTLAbsMessagesCustom(messages);
    }
    
    protected abstract void onTLAbsMessagesCustom(List<TLAbsMessage> messages);
    protected abstract void onTLAbsMessageCustom(TLAbsMessage message);

}