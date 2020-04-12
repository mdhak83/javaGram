package org.telegram.utils;

import org.telegram.api.chat.base.TLAbsChat;
import org.telegram.api.peer.base.input.TLAbsInputPeer;
import org.telegram.api.peer.base.input.TLInputPeerChannel;
import org.telegram.api.peer.base.input.TLInputPeerChat;
import org.telegram.api.peer.base.input.TLInputPeerSelf;
import org.telegram.api.peer.base.input.TLInputPeerUser;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.api.user.base.input.TLAbsInputUser;
import org.telegram.api.user.base.input.TLInputUser;
import org.telegram.api.user.base.input.TLInputUserSelf;

public class TLFactory {
    
    public static TLAbsInputUser createTLInputUser(TLAbsUser user) {
        final TLAbsInputUser tlAbsInputUser;
        if (user == null) {
            tlAbsInputUser = new TLInputUserSelf();
        } else {
            final TLInputUser tlInputUser = new TLInputUser();
            tlInputUser.setUserId(user.getId());
            tlInputUser.setAccessHash(user.getAccessHash());
            tlAbsInputUser = tlInputUser;
        }
        return tlAbsInputUser;
    }

    public static TLAbsInputPeer createTLInputPeer(TLAbsUser user, TLAbsChat chat) {
        final TLAbsInputPeer tlInputPeer;
        if (user == null) {
            if (chat == null) {
                tlInputPeer = new TLInputPeerSelf();
            } else {
                if (chat.isChannel()) {
                    final TLInputPeerChannel inputPeerChannel = new TLInputPeerChannel();
                    inputPeerChannel.setChannelId(chat.getId());
                    inputPeerChannel.setAccessHash(chat.getAccessHash());
                    tlInputPeer = inputPeerChannel;
                } else {
                    final TLInputPeerChat tlInputPeerChat = new TLInputPeerChat();
                    tlInputPeerChat.setChatId(chat.getId());
                    tlInputPeer = tlInputPeerChat;
                }
            }
        } else {
            final TLInputPeerUser tlInputPeerUser = new TLInputPeerUser();
            tlInputPeerUser.setUserId(user.getId());
            tlInputPeerUser.setAccessHash(user.getAccessHash());
            tlInputPeer = tlInputPeerUser;
        }

        return tlInputPeer;
    }

}