package org.telegram.client.handlers.interfaces;

import java.util.List;
import org.telegram.api.message.base.TLAbsMessage;

public interface IMessagesHandler {
    void onMessages(List<TLAbsMessage> message);
}
