package org.javagram.client.kernel;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import org.javagram.api.channel.base.input.TLInputChannel;
import org.javagram.api.channels.functions.TLRequestChannelsGetFullChannel;
import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.chat.base.TLChannel;
import org.javagram.api.chat.base.TLChannelForbidden;
import org.javagram.api.chat.base.TLChat;
import org.javagram.api.chat.base.TLChatForbidden;
import org.javagram.api.message.base.TLAbsMessage;
import org.javagram.api.message.base.TLMessage;
import org.javagram.api.message.base.TLMessageService;
import org.javagram.api.messages.base.TLMessagesChatFull;
import org.javagram.api.messages.functions.TLRequestMessagesGetFullChat;
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
import org.javagram.api.user.base.TLUserEmpty;
import org.javagram.api.user.base.TLUserFull;
import org.javagram.api.user.base.input.TLInputUser;
import org.javagram.api.users.functions.TLRequestUsersGetFullUser;
import org.javagram.utils.BotLogger;
import org.javagram.MyTLAppConfiguration;

public abstract class AbstractDatabaseManager {
    
    private static final String LOGTAG = "[AbstractUpdatesHandler]";
    protected static final Map<Integer, TLAbsChat> CHATS = new LinkedHashMap<>();
    protected static final Map<Integer, TLMessagesChatFull> FULL_CHATS = new LinkedHashMap<>();
    protected static final Map<Integer, TLAbsUser> USERS = new LinkedHashMap<>();
    protected static final Map<Integer, TLUserFull> FULL_USERS = new LinkedHashMap<>();

    private static int MaxTemporalUsers = 4000;

    private MyTLAppConfiguration config;
    
    private final Object usersLock = new Object();
    private final Object chatsLock = new Object();

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
        synchronized(this.usersLock) {
            if (USERS.size() > MaxTemporalUsers) {
                int countRemove = USERS.size() / 5;
                Set<Integer> remove = new HashSet<>();
                for (int i : USERS.keySet()) {
                    if (countRemove <= 0) {
                        break;
                    }
                    remove.add(i);
                    countRemove--;
                }
                remove.forEach((i) -> {
                    USERS.remove(i);
                    FULL_USERS.remove(i);
                });
            }
        }
    }
    
    public void processUser(TLAbsUser user, boolean _updateAccessHash) {
        synchronized(this.usersLock) {
            if (user instanceof TLUser) {
                final TLUser tlUser = (TLUser) user;
                TLAbsUser current = USERS.get(user.getId());
                boolean update = true;
                if (!tlUser.isDeleted()) {
                    if (current == null || current instanceof TLUserEmpty) {
                        USERS.put(tlUser.getId(), tlUser);
                        current = tlUser;
                        update = false;
                    }
                    current.setAccessHash(tlUser.getAccessHash());
                    if (!update) {
                        this.addUser(current);
                    } else {
                        this.updateUser(current);
                    }
                    if (!current.isBot() && current.getAccessHash() != null && !FULL_USERS.containsKey(current.getId())) {
                        TLRequestUsersGetFullUser getFullUser = new TLRequestUsersGetFullUser();
                        TLInputUser iUser = new TLInputUser();
                        iUser.setUserId(current.getId());
                        iUser.setAccessHash(current.getAccessHash());
                        getFullUser.setId(iUser);
                        TLUserFull userFull = null;
                        boolean ok = false;
                        try {
                            userFull = this.config.getApi().doRpcCall(getFullUser);
                            ok = true;
                        } catch (Exception ex) {
                            ok = false;
                        }
                        FULL_USERS.put(current.getId(), userFull);
                        //++DEBUG
                        if (!ok) {
                            TLVector<String> reasons = tlUser.getRestrictionReason();
                            int k = 0;
                        } else {
                            int k = 0;
                        }
                        //--DEBUG
                    }
                } else {
                    if (current != null) {
                        this.deleteUser(current);
                        USERS.remove(current.getId(), current);
                    }
                }
            }
        }
        this._removeExcessTemporalUsers();
    }
    
    public void processChat(TLAbsChat chat, boolean updateAccessHash) {
        synchronized(this.chatsLock) {
            TLAbsChat current = CHATS.get(chat.getId());
            boolean update = (current != null);
            if (current == null) {
                CHATS.put(chat.getId(), chat);
                current = chat;
            }
            if (updateAccessHash || current.getAccessHash() == null || current.getAccessHash() == 0L) {
                current.setAccessHash(chat.getAccessHash());
            }
            if (current.getAccessHash() != null && !FULL_CHATS.containsKey(current.getId())) {
                boolean ok = false;
                if (current.getAccessHash() != null && (current instanceof TLChannel || current instanceof TLChannelForbidden)) {
                    TLRequestChannelsGetFullChannel getFullChannel = new TLRequestChannelsGetFullChannel();
                    TLInputChannel ic = new TLInputChannel();
                    ic.setChannelId(current.getId());
                    ic.setAccessHash(current.getAccessHash());
                    getFullChannel.setChannel(ic);
                    TLMessagesChatFull channelFull = null;
                    try {
                        channelFull = this.config.getApi().doRpcCall(getFullChannel);
                        ok = true;
                    } catch (Exception ex) {
                        ok = false;
                    }
                    FULL_CHATS.put(current.getId(), channelFull);
                } else if (current instanceof TLChat || current instanceof TLChatForbidden) {
                    TLRequestMessagesGetFullChat getFullChat = new TLRequestMessagesGetFullChat();
                    getFullChat.setChatId(current.getId());
                    TLMessagesChatFull chatFull = null;
                    try {
                        chatFull = this.config.getApi().doRpcCall(getFullChat);
                        ok = true;
                    } catch (Exception ex) {
                        ok = false;
                    }
                    FULL_CHATS.put(current.getId(), chatFull);
                }
                //++DEBUG
                if (!ok) {
                    int k = 0;
                } else {
                    int k = 0;
                }
                //--DEBUG
            }
            if (!update) {
                this.addChat(current);
            } else {
                this.updateChat(current);
            }
        }
    }
    
    public boolean isUserFromMessageMissing(TLAbsMessage message, boolean checkChatId) {
        boolean isMissing = true;

        if (message instanceof TLMessage) {
            final TLMessage tlMessage = (TLMessage) message;
            boolean isFromMissing = true;
            if (tlMessage.hasFromId()) {
                isFromMissing = isUserMissing(tlMessage.getFromId());
            }

            boolean isToMissing = true;
            if (tlMessage.getToId() instanceof TLPeerUser) {
                isToMissing = isUserMissing(((TLPeerUser) tlMessage.getToId()).getUserId());
            } else if (checkChatId) {
                if (tlMessage.getToId() instanceof TLPeerChannel) {
                    isToMissing = isChatMissing(((TLPeerChannel) tlMessage.getToId()).getChannelId());
                } else if (tlMessage.getToId() instanceof TLPeerChat) {
                    isToMissing = isChatMissing(((TLPeerChat) tlMessage.getToId()).getChatId());
                }
            }

            boolean isForwardedMissing = true;
            if (tlMessage.hasFwdFrom()) {
                isForwardedMissing = isUserMissing(tlMessage.getFwdFrom().getFromId());
            }

            isMissing = isFromMissing && isToMissing && isForwardedMissing;
        } else if (message instanceof TLMessageService ){
            final TLMessageService tlMessageService = (TLMessageService) message;

            boolean isFromMissing = true;
            if (tlMessageService.hasFromId()) {
                isFromMissing = isUserMissing(tlMessageService.getFromId());
            }

            boolean isToMissing = true;
            if (tlMessageService.getToId() instanceof TLPeerUser) {
                isToMissing = isUserMissing(((TLPeerUser) tlMessageService.getToId()).getUserId());
            } else if (checkChatId) {
                if (tlMessageService.getToId() instanceof TLPeerChannel) {
                    isToMissing = isChatMissing(((TLPeerChannel) tlMessageService.getToId()).getChannelId());
                } else if (tlMessageService.getToId() instanceof TLPeerChat) {
                    isToMissing = isChatMissing(((TLPeerChat) tlMessageService.getToId()).getChatId());
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
        synchronized(this.chatsLock) {
            return CHATS.get(chatId) == null;
        }
    }

    public boolean isUserMissing(int userId) {
        synchronized(this.usersLock) {
            return USERS.get(userId) == null;
        }
    }

    public boolean isPeerMissing(TLAbsPeer peer) {
        synchronized(this.usersLock) {
            boolean isMissing = true;
            if (peer instanceof TLPeerUser) {
                isMissing = USERS.get(((TLPeerUser) peer).getUserId()) == null;
            } else if (peer instanceof TLPeerChat) {
                isMissing = USERS.get(((TLPeerChat) peer).getChatId()) == null;
            } else if (peer instanceof TLPeerChannel) {
                isMissing = USERS.get(((TLPeerChannel) peer).getChannelId()) == null;
            } else {
                BotLogger.warning(LOGTAG, "!! Peer " + (peer != null ? peer.toString() : "null"));
            }
            return isMissing;
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
        synchronized(this.usersLock) {
            return (USERS.get(updateShortMessage.getUserId()) == null) ||
                    (updateShortMessage.isForwarded() && (USERS.get(updateShortMessage.getFwdFrom().getFromId()) == null));
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
    public TLAbsUser getUserById(int uid) {
        synchronized(this.usersLock) {
            return USERS.get(uid);
        }
    }
    
    public TLUserFull getFullUserById(int uid) {
        synchronized(this.usersLock) {
            return FULL_USERS.get(uid);
        }
    }
    
    public TLMessagesChatFull getFullChatById(int cid) {
        synchronized(this.chatsLock) {
            return FULL_CHATS.get(cid);
        }
    }
    
    public TLAbsChat getChatById(int cid) {
        synchronized(this.chatsLock) {
            return CHATS.get(cid);
        }
    }
    
}