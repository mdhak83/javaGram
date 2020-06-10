package org.javagram.client.kernel;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.message.base.TLAbsMessage;
import org.javagram.api.message.base.TLMessage;
import org.javagram.api.message.base.TLMessageService;
import org.javagram.api.notify.base.peer.TLAbsNotifyPeer;
import org.javagram.api.notify.base.peer.TLNotifyPeer;
import org.javagram.api.peer.base.TLAbsPeer;
import org.javagram.api.peer.base.TLFolderPeer;
import org.javagram.api.peer.base.TLPeerChannel;
import org.javagram.api.peer.base.TLPeerChat;
import org.javagram.api.peer.base.TLPeerLocated;
import org.javagram.api.peer.base.TLPeerUser;
import org.javagram.api.updates.base.TLUpdateShortMessage;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.api.user.base.TLUser;
import org.javagram.utils.BotLogger;
import org.javagram.MyTLAppConfiguration;
import org.javagram.api._primitives.TLIntVector;
import org.javagram.api.channel.base.input.TLAbsInputChannel;
import org.javagram.api.channel.base.input.TLInputChannel;
import org.javagram.api.channels.functions.TLRequestChannelsGetChannels;
import org.javagram.api.chat.base.TLChatEmpty;
import org.javagram.api.messages.base.chats.TLAbsMessagesChats;
import org.javagram.api.messages.functions.TLRequestMessagesGetChats;
import org.javagram.api.user.base.input.TLAbsInputUser;
import org.javagram.api.user.base.input.TLInputUser;
import org.javagram.api.user.base.input.TLInputUserSelf;
import org.javagram.api.users.functions.TLRequestUsersGetUsers;

public abstract class AbstractDatabaseManager {
    
    private static final String LOGTAG = "[AbstractDatabaseManager]";
    private static int MaxTemporalUsers = 4000;

    private final Map<Integer, TLAbsChat> chats = new LinkedHashMap<>();
    private final Map<Integer, TLUser> users = new LinkedHashMap<>();

    protected MyTLAppConfiguration config;

    public abstract boolean addUser(TLAbsUser user);
    public abstract boolean updateUser(TLAbsUser user);
    public abstract boolean deleteUser(TLAbsUser user);
    public abstract boolean addChat(TLAbsChat chat);
    public abstract boolean updateChat(TLAbsChat chat);
    public abstract Map<Integer, int[]> getDifferencesData();
    public abstract boolean updateDifferencesData(int botId, int pts, int date, int seq);
    public abstract void free() throws Throwable;
    
    protected AbstractDatabaseManager() {
    }
    
    public void build(MyTLAppConfiguration config) {
        this.config = config;
    }

    private void _removeExcessTemporalUsers() {
        synchronized(this.users) {
            if (this.users.size() > MaxTemporalUsers) {
                int countRemove = this.users.size() / 5;
                Set<Integer> remove = new HashSet<>();
                for (int i : this.users.keySet()) {
                    if (countRemove <= 0) {
                        break;
                    }
                    remove.add(i);
                    countRemove--;
                }
                remove.forEach((i) -> {
                    TLUser u = this.users.get(i);
                    this.users.remove(i);
                });
            }
        }
    }
    
    public void processUser(TLAbsUser user) {
        if (user != null && user instanceof TLUser) {
            final TLUser tlUser = (TLUser) user;
            TLUser current = this.getUserById(user.getId());
            boolean update = true;
            if (!tlUser.isDeleted()) {
                if (current == null || (current.isMin() && !tlUser.isMin())) {
                    synchronized(this.users) {
                        this.users.put(tlUser.getId(), tlUser);
                    }
                    update = (current != null && current.isMin() && !tlUser.isMin());
                    current = tlUser;
                }
                if (!update) {
                    this.addUser(current);
                } else {
                    this.updateUser(current);
                }
            } else {
                if (current != null) {
                    this.deleteUser(current);
                    this.users.remove(current.getId(), current);
                }
            }
            this._removeExcessTemporalUsers();
        }
    }
    
    public void processChat(TLAbsChat chat) {
        if (chat != null && !(chat instanceof TLChatEmpty)) {
            synchronized(this.chats) {
                TLAbsChat current = this.chats.get(chat.getId());
                boolean update = true;
                if (current == null || (current.isMin() && !chat.isMin())) {
                    this.chats.put(chat.getId(), chat);
                    current = chat;
                    update = false;
                }
                if (!update) {
                    this.addChat(current);
                } else {
                    this.updateChat(current);
                }
            }
        }
    }
    
    public void retrieveSelf() {
        try {
            TLRequestUsersGetUsers req = new TLRequestUsersGetUsers();
            TLInputUserSelf self = new TLInputUserSelf(); 
            TLVector<TLAbsInputUser> id = new TLVector<>();
            id.add(self);
            req.setId(id);
            TLVector<TLAbsUser> _users = this.config.getApi().doRpcCall(req);
            if (!_users.isEmpty() && _users.size() == 1 && _users.get(0) instanceof TLUser) {
                TLUser selfUser = (TLUser) _users.get(0);
                this.processUser(selfUser);
            }
        } catch (Exception ex) {
            BotLogger.error(LOGTAG, "[AbstractDatabaseManager::retrieveSelf] Impossible to retrieve self user.");
        }
    }
    
    public boolean isUserFromMessageMissing(TLAbsMessage message, boolean checkChatId) {
        boolean isMissing = true;

        if (message instanceof TLMessage) {
            final TLMessage tlMessage = (TLMessage) message;
            boolean isFromMissing = true;
            if (tlMessage.hasFromId()) {
                isFromMissing = this.isUserMissing(tlMessage.getFromId());
            }

            boolean isToMissing = true;
            if (tlMessage.getToId() instanceof TLPeerUser) {
                isToMissing = this.isUserMissing(((TLPeerUser) tlMessage.getToId()).getUserId());
            } else if (checkChatId) {
                if (tlMessage.getToId() instanceof TLPeerChannel) {
                    isToMissing = this.isChatMissing(((TLPeerChannel) tlMessage.getToId()).getChannelId());
                } else if (tlMessage.getToId() instanceof TLPeerChat) {
                    isToMissing = this.isChatMissing(((TLPeerChat) tlMessage.getToId()).getChatId());
                }
            }

            boolean isForwardedMissing = true;
            if (tlMessage.hasFwdFrom()) {
                isForwardedMissing = this.isUserMissing(tlMessage.getFwdFrom().getFromId());
            }

            isMissing = isFromMissing && isToMissing && isForwardedMissing;
        } else if (message instanceof TLMessageService ){
            final TLMessageService tlMessageService = (TLMessageService) message;

            boolean isFromMissing = true;
            if (tlMessageService.hasFromId()) {
                isFromMissing = this.isUserMissing(tlMessageService.getFromId());
            }

            boolean isToMissing = true;
            if (tlMessageService.getToId() instanceof TLPeerUser) {
                isToMissing = this.isUserMissing(((TLPeerUser) tlMessageService.getToId()).getUserId());
            } else if (checkChatId) {
                if (tlMessageService.getToId() instanceof TLPeerChannel) {
                    isToMissing = this.isChatMissing(((TLPeerChannel) tlMessageService.getToId()).getChannelId());
                } else if (tlMessageService.getToId() instanceof TLPeerChat) {
                    isToMissing = this.isChatMissing(((TLPeerChat) tlMessageService.getToId()).getChatId());
                }
            }

            isMissing = isFromMissing && isToMissing;
        }

        return isMissing;
    }

    public boolean isUserFromMessageMissing(TLAbsMessage message) {
        return this.isUserFromMessageMissing(message, false);
    }

    public boolean isChatMissing(int chatId) {
        synchronized(this.chats) {
            return this.chats.get(chatId) == null;
        }
    }

    public boolean isUserMissing(int userId) {
        synchronized(this.users) {
            return this.getUserById(userId) == null;
        }
    }

    public boolean isPeerMissing(TLAbsPeer peer) {
        synchronized(this.users) {
            try {
                boolean isMissing = true;
                if (peer instanceof TLPeerUser) {
                    isMissing = this.getUserById(((TLPeerUser) peer).getUserId()) == null;
                } else if (peer instanceof TLPeerChat) {
                    isMissing = this.getChatById(((TLPeerChat) peer).getChatId(), false) == null;
                } else if (peer instanceof TLPeerChannel) {
                    isMissing = this.getChatById(((TLPeerChannel) peer).getChannelId(), true) == null;
                } else {
                    BotLogger.warning(LOGTAG, "!! Peer " + (peer != null ? peer.toString() : "null"));
                }
                return isMissing;
            } catch (Exception ex) {
                return true;
            }
        }
    }

    public boolean isNotifyPeerMissing(TLAbsNotifyPeer notifyPeer) {
        boolean isMissing = false;
        if (notifyPeer instanceof TLNotifyPeer) {
            isMissing = isPeerMissing(((TLNotifyPeer) notifyPeer).getPeer());
        }
        return isMissing;
    }
    
    public boolean isAnyPeerMissing(TLVector<? extends TLObject> peers) {
        boolean peerMissing = false;
        for (TLObject o : peers) {
            if (o instanceof TLAbsPeer) {
                TLAbsPeer p = (TLAbsPeer) o;
                peerMissing |= isPeerMissing(p);
            } else if (o instanceof TLPeerLocated) {
                TLPeerLocated p = (TLPeerLocated) o;
                peerMissing |= isPeerMissing(p.getPeer());
            } else if (o instanceof TLFolderPeer) {
                TLFolderPeer p = (TLFolderPeer) o;
                peerMissing |= isPeerMissing(p.getPeer());
            } else {
                throw new IllegalArgumentException("Invalid peer!!");
            }
        }
        return peerMissing;
    }

    /**
     * Check if all user needed by a updateShortMessage are not present in database
     * @param updateShortMessage Update to check
     * @return true if any of them is missing, false otherwise
     */
    public boolean isUserFromShortMessageMissing(TLUpdateShortMessage updateShortMessage) {
        synchronized(this.users) {
            return (this.getUserById(updateShortMessage.getUserId()) == null) ||
                    (updateShortMessage.isForwarded() && (this.getUserById(updateShortMessage.getFwdFrom().getFromId()) == null));
        }
    }

    public static void setMaxTemporalUsers(int aMaxTemporalUsers) {
        if (aMaxTemporalUsers < 100) {
            aMaxTemporalUsers = 100;
        }
        MaxTemporalUsers = aMaxTemporalUsers;
    }
    
    /*
     * 
     */
    public TLUser getUserById(int userId) {
        TLUser ret;
        if (userId == 0) {
            userId = this.config.getApiState().getUserId();
        }
        synchronized(this.users) {
            ret = this.users.get(userId);
        }
        if (ret == null) {
            try {
                TLRequestUsersGetUsers req = new TLRequestUsersGetUsers();
                TLInputUser iu = new TLInputUser(); 
                iu.setUserId(userId);
                iu.setAccessHash(0L);
                TLVector<TLAbsInputUser> id = new TLVector<>();
                id.add(iu);
                req.setId(id);
                TLVector<TLAbsUser> _users = this.config.getApi().doRpcCall(req);
                if (!_users.isEmpty() && _users.size() == 1 && _users.get(0) instanceof TLUser) {
                    TLUser user = (TLUser) _users.get(0);
                    synchronized(this.users) {
                        this.users.put(user.getId(), user);
                    }
                    this.addUser(user);
                    ret = user;
                }
            } catch (Exception ex) {
                BotLogger.error(LOGTAG, "[AbstractDatabaseManager::getUserById] Impossible to retrieve User#" + String.format("%08x", userId));
            }
        }
        return ret;
    }
    
    public TLAbsChat getChatById(int chatId, Boolean isChannel) throws Exception {
        TLAbsChat ret;
        synchronized(this.chats) {
            ret = this.chats.get(chatId);
        }
        if (ret == null) {
            try {
                TLAbsMessagesChats absChatsObject = null;
                if (isChannel == true) {
                    TLRequestChannelsGetChannels req = new TLRequestChannelsGetChannels();
                    TLVector<TLAbsInputChannel> id = new TLVector<>();
                    TLInputChannel ic = new TLInputChannel(); 
                    ic.setChannelId(chatId);
                    ic.setAccessHash(0L);
                    id.add(ic);
                    req.setId(id);
                    absChatsObject = this.config.getApi().doRpcCall(req);
                } else if (isChannel == false) {
                    TLRequestMessagesGetChats req = new TLRequestMessagesGetChats();
                    TLIntVector id = new TLIntVector();
                    id.add(chatId);
                    req.setId(id);
                    absChatsObject = this.config.getApi().doRpcCall(req);
                }
                if (absChatsObject != null && !absChatsObject.getChats().isEmpty() && absChatsObject.getChats().size() == 1 && absChatsObject.getChats().get(0) instanceof TLAbsChat) {
                    TLAbsChat chat = (TLAbsChat) absChatsObject.getChats().get(0);
                    synchronized(this.chats) {
                        this.chats.put(chat.getId(), chat);
                    }
                    this.addChat(chat);
                    ret = chat;
                }
            } catch (Exception ex) {
                BotLogger.error(LOGTAG, "[AbstractDatabaseManager::getChatById] Impossible to retrieve Chat#" + String.format("%08x", chatId));
                throw ex;
            }
        }
        return ret;
    }
    
}