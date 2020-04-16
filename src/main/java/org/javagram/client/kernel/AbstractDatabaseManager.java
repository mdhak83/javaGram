package org.javagram.client.kernel;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
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
import org.javagram.api.chat.base.TLChatEmpty;

public abstract class AbstractDatabaseManager {
    
    private static final String LOGTAG = "[AbstractDatabaseManager]";
    protected static final Map<Integer, TLAbsChat> CHATS = new LinkedHashMap<>();
    protected static final Map<Integer, TLUser> USERS = new LinkedHashMap<>();

    private static int MaxTemporalUsers = 4000;

    protected MyTLAppConfiguration config;
    
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
    public abstract void onUserAdded(TLUser regular);
    public abstract void onUserRemoved(TLUser regular);
    public abstract void onChatAdded(TLAbsChat regular);
    
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
                    TLUser u = USERS.get(i);
                    this.onUserRemoved(u);
                    USERS.remove(i);
                });
            }
        }
    }
    
    public void processUser(TLAbsUser user) {
        if (user != null && user instanceof TLUser) {
            final TLUser tlUser = (TLUser) user;
            if (!tlUser.isMin()) {
                synchronized(this.usersLock) {
                    TLUser current = USERS.get(user.getId());
                    boolean update = true;
                    if (!tlUser.isDeleted()) {
                        if (current == null) {
                            USERS.put(tlUser.getId(), tlUser);
                            current = tlUser;
                            update = false;
                        }
                        if (!update) {
                            this.addUser(current);
                            this.onUserAdded(current);
                        } else {
                            this.updateUser(current);
                        }
                    } else {
                        if (current != null) {
                            this.deleteUser(current);
                            USERS.remove(current.getId(), current);
                        }
                    }
                }
                this._removeExcessTemporalUsers();
            }
        }
    }
    
    public void processChat(TLAbsChat chat) {
        if (chat != null && !(chat instanceof TLChatEmpty)) {
            if (!chat.isMin()) {
                synchronized(this.chatsLock) {
                    TLAbsChat current = CHATS.get(chat.getId());
                    boolean update = true;
                    if (current == null) {
                        CHATS.put(chat.getId(), chat);
                        current = chat;
                        update = false;
                    }
                    if (!update) {
                        this.addChat(current);
                        this.onChatAdded(current);
                    } else {
                        this.updateChat(current);
                    }
                }
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
    public TLUser getUserById(int uid) {
        synchronized(this.usersLock) {
            return USERS.get(uid);
        }
    }
    
    public TLAbsChat getChatById(int cid) {
        synchronized(this.chatsLock) {
            return CHATS.get(cid);
        }
    }
    
}