package org.javagram.client.handlers.interfaces;

import java.util.List;
import org.javagram.api.message.base.TLAbsMessage;

public interface IMessagesHandler {
    void onMessages(List<TLAbsMessage> message);
}
