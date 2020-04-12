package org.telegram.client.handlers;

import org.telegram.client.handlers.interfaces.IUpdatesHandler;
import org.jetbrains.annotations.NotNull;
import org.telegram.utils.BotLogger;
import org.telegram.utils.NotificationsService;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.api.updates.base.TLAbsUpdates;
import org.telegram.api.updates.base.TLUpdateShort;
import org.telegram.api.updates.base.TLUpdateShortChatMessage;
import org.telegram.api.updates.base.TLUpdateShortMessage;
import org.telegram.api.updates.base.TLUpdateShortSentMessage;
import org.telegram.api.updates.base.TLUpdates;
import org.telegram.api.updates.base.TLUpdatesCombined;
import org.telegram.api.updates.base.TLUpdatesState;
import org.telegram.api.updates.base.TLUpdatesTooLong;
import org.telegram.api.updates.functions.TLRequestUpdatesGetState;
import org.telegram.MyTLAppConfiguration;
import org.telegram.utils.RpcException;
import org.telegram.client.kernel.IKernelComm;
import org.telegram.client.structure.UpdateWrapper;

public class DefaultKernelHandler implements NotificationsService.NotificationObserver {

    private static final String LOGTAG = "[KernelHandler]";
    
    private MyTLAppConfiguration config;
    private UpdatesHandlerThread updatesHandlerThread;
    private UpdateHandlerThread updateHandlerThread;

    private boolean running;
    private final AtomicBoolean gettingDifferences = new AtomicBoolean(false);
    private final AtomicBoolean needGetUpdateState = new AtomicBoolean(true);
    protected final ConcurrentLinkedDeque<TLAbsUpdates> updatesQueue = new ConcurrentLinkedDeque<>();

    public DefaultKernelHandler() { }
    
    public void build(MyTLAppConfiguration config) {
        this.config = config;
        NotificationsService.getInstance().addObserver(this, NotificationsService.needGetUpdates);
        this.running = false;
        this.updatesHandlerThread = new UpdatesHandlerThread(this, this.needGetUpdateState, this.gettingDifferences);
        this.updatesHandlerThread.setPriority(9);
        this.updateHandlerThread = new UpdateHandlerThread(this.config.getUpdatesHandler());
        this.updateHandlerThread.setPriority(9);
    }

    public void start() {
        this.updatesQueue.clear();
        this.config.getExecutor().submit(this.updatesHandlerThread);
        this.config.getExecutor().submit(this.updateHandlerThread);
        this.running = true;
    }

    public void stop() {
        this.running = false;
    }

    /**
     * Add a TLAbsUpdates to the updates queue.
     * Load Updates states from database if needed
     * @param updates Updates to add
     */
    public void onUpdate(@NotNull final TLAbsUpdates updates) {
        if (this.running) {
            this.updatesQueue.addLast(updates);
            synchronized (this.updatesQueue) {
                this.updatesQueue.notifyAll();
            }
        }
    }

    /**
     * Handles TLAbsUpdates
     * @param updates Updates to handle
     */
    protected void onTLAbsUpdates(@NotNull final TLAbsUpdates updates) {
        BotLogger.debug(LOGTAG, "Received:" + updates.toString());
        if (updates instanceof TLUpdateShortMessage) {
            final TLUpdateShortMessage updateShortMessage = (TLUpdateShortMessage) updates;
            final UpdateWrapper wrapper = new UpdateWrapper(updateShortMessage);
            wrapper.setParams(updateShortMessage.getPts(), updateShortMessage.getPtsCount(),
                    updateShortMessage.getDate(), 0, 0);
            updateHandlerThread.addUpdate(wrapper);
        } else if (updates instanceof TLUpdateShortChatMessage) {
            final TLUpdateShortChatMessage updateShortChatMessage = (TLUpdateShortChatMessage) updates;
            final UpdateWrapper wrapper = new UpdateWrapper(updateShortChatMessage);
            wrapper.setParams(updateShortChatMessage.getPts(), updateShortChatMessage.getPtsCount(),
                    updateShortChatMessage.getDate(), 0, 0);
            updateHandlerThread.addUpdate(wrapper);
        } else if (updates instanceof TLUpdateShort) {
            final TLUpdateShort updateShort = (TLUpdateShort) updates;
            final UpdateWrapper wrapper = new UpdateWrapper(updateShort.getUpdate());
            wrapper.setParams(updateShort.getUpdate().getPts(), updateShort.getUpdate().getPtsCount(), updateShort.getDate(), 0, 0);
            this.updateHandlerThread.addUpdate(wrapper);
        } else if (updates instanceof TLUpdates) {
            final TLUpdates tlUpdates = (TLUpdates) updates;
            final boolean disablePtsCheck = tlUpdates.getSeq() != 0;

            boolean correctSeq = true;
            if (disablePtsCheck) {
                correctSeq = this.config.getUpdatesHandler().checkSeq(tlUpdates.getSeq(), 0,tlUpdates.getDate());
            }

            if (correctSeq) {
                this.config.getChatsHandler().onChats(tlUpdates.getChats());
                this.config.getUsersHandler().onUsers(tlUpdates.getUsers());
                tlUpdates.getUpdates().forEach(x -> {
                    final UpdateWrapper wrapper = new UpdateWrapper(x);
                    wrapper.setParams(x.getPts(), x.getPtsCount(), tlUpdates.getDate(), tlUpdates.getSeq(), 0);
                    if (disablePtsCheck) {
                        wrapper.disablePtsCheck();
                    }
                    this.updateHandlerThread.addUpdate(wrapper);
                });
            } else {
                this.config.getUpdatesHandler().getDifferences();
            }
        } else if (updates instanceof TLUpdatesCombined) {
            final TLUpdatesCombined updatesCombined = (TLUpdatesCombined) updates;
            final boolean disablePtsCheck = updatesCombined.getSeq() != 0;

            boolean correctSeq = true;
            if (disablePtsCheck) {
                correctSeq = this.config.getUpdatesHandler().checkSeq(updatesCombined.getSeq(), updatesCombined.getSeqStart(), updatesCombined.getDate());
            }

            if (correctSeq) {
                this.config.getChatsHandler().onChats(updatesCombined.getChats());
                this.config.getUsersHandler().onUsers(updatesCombined.getUsers());
                updatesCombined.getUpdates().forEach(x -> {
                    final UpdateWrapper wrapper = new UpdateWrapper(x);
                    wrapper.setParams(x.getPts(), x.getPtsCount(), updatesCombined.getDate(), updatesCombined.getSeq(), updatesCombined.getSeqStart());
                    if (disablePtsCheck) {
                        wrapper.disablePtsCheck();
                    }
                    this.updateHandlerThread.addUpdate(wrapper);
                });
            } else {
                this.config.getUpdatesHandler().getDifferences();
            }
        } else if (updates instanceof TLUpdateShortSentMessage) {
            final TLUpdateShortSentMessage updateShortSentMessage = (TLUpdateShortSentMessage) updates;
            final UpdateWrapper wrapper = new UpdateWrapper(updateShortSentMessage);
            wrapper.setParams(updateShortSentMessage.getPts(), updateShortSentMessage.getPtsCount(), updateShortSentMessage.getDate(), 0, 0);
            this.updateHandlerThread.addUpdate(wrapper);
        } else if (updates instanceof TLUpdatesTooLong) {
            this.config.getUpdatesHandler().onTLUpdatesTooLong();
        } else {
            BotLogger.debug(LOGTAG, "Unsupported TLAbsUpdates: " + updates.toString());
        }
    }

    public void processUpdates(@NotNull List<UpdateWrapper> updates) {
        this.updateHandlerThread.addUpdates(updates);
    }

    /**
     * Load updats state from server
     */
    protected void getUpdatesState() {
        try {
            final TLUpdatesState state = this.config.getKernelCommunicationService().doRpcCallSync(new TLRequestUpdatesGetState());
            if (state != null) {
                BotLogger.error(LOGTAG, "Received updates state");
                this.config.getUpdatesHandler().updateStateModification(state);
                this.needGetUpdateState.set(false);
            } else {
                BotLogger.error(LOGTAG, "Error getting updates state");
            }
        } catch (ExecutionException | RpcException e) {
            BotLogger.error(LOGTAG, e);
        }
    }

    /**
     * Force getting updates state on next update received
     */
    public void needGetUpdates() {
        this.needGetUpdateState.set(true);
        synchronized (this.updatesQueue) {
            this.updatesQueue.notifyAll();
        }
    }

    public IUpdatesHandler getUpdatesHandler() {
        return this.config.getUpdatesHandler();
    }

    @Override
    public void onNotificationReceived(int notificationId, Object... args) {
        if (notificationId == NotificationsService.needGetUpdates) {
            needGetUpdates();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        NotificationsService.getInstance().removeObserver(this, NotificationsService.needGetUpdates);
        super.finalize();
    }

}