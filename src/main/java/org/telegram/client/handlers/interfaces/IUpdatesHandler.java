package org.telegram.client.handlers.interfaces;

import org.telegram.client.structure.UpdateWrapper;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.telegram.api.chat.base.TLAbsChat;
import org.telegram.api.message.base.TLAbsMessage;
import org.telegram.api.update.base.TLAbsUpdate;
import org.telegram.api.updates.base.TLUpdatesState;
import org.telegram.api.updates.base.difference.TLAbsDifference;
import org.telegram.api.user.base.TLAbsUser;

public interface IUpdatesHandler {

    void getDifferences();
    void onTLAbsDifference(@NotNull TLAbsDifference absDifference);
    void onTLChannelDifferences(List<TLAbsUser> users, List<TLAbsMessage> messages, List<TLAbsUpdate> newUpdates, List<TLAbsChat> chats);
    void updateStateModification(TLUpdatesState state);
    boolean checkSeq(int seq, int seqStart, int date);
    void processUpdate(UpdateWrapper updateWrapper);
    void onTLUpdatesTooLong();

}