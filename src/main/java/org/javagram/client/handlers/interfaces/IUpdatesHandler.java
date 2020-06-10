package org.javagram.client.handlers.interfaces;

import org.javagram.client.structure.UpdateWrapper;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.message.base.TLAbsMessage;
import org.javagram.api.update.base.TLAbsUpdate;
import org.javagram.api.updates.base.TLUpdatesState;
import org.javagram.api.updates.base.difference.TLAbsDifference;
import org.javagram.api.user.base.TLAbsUser;

public interface IUpdatesHandler {

    void getDifferences();
    void onTLAbsDifference(@NotNull TLAbsDifference absDifference);
    void onTLChannelDifferences(List<TLAbsUser> users, List<TLAbsMessage> messages, List<TLAbsUpdate> newUpdates, List<TLAbsChat> chats);
    void updateCommonUpdateState(TLUpdatesState state);
    boolean checkSeq(int seq, int seqStart, int date);
    void processUpdate(UpdateWrapper updateWrapper);
    void onTLUpdatesTooLong();

}