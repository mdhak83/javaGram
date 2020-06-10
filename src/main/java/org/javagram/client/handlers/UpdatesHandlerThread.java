package org.javagram.client.handlers;

import java.util.concurrent.atomic.AtomicBoolean;
import org.javagram.api.updates.base.TLAbsUpdates;
import org.javagram.utils.BotLogger;

public class UpdatesHandlerThread extends Thread {
    
    private static final String LOGTAG = "[UpdatesHandlerThread]";
    private final DefaultKernelHandler mainHandler;
    private final AtomicBoolean gettingDifferences;
    private final AtomicBoolean needGetUpdateState;

    private boolean isAlive = true;

    protected UpdatesHandlerThread(DefaultKernelHandler mainHandler, AtomicBoolean needGetUpdateState, AtomicBoolean gettingDifferences) {
        super("UpdatesHandlerThread");
        this.setPriority(9);
        this.mainHandler = mainHandler;
        this.needGetUpdateState = needGetUpdateState;
        this.gettingDifferences = gettingDifferences;
    }

    @Override
    public void interrupt() {
        this.isAlive = false;
        super.interrupt();
    }

    @Override
    public void run() {
        TLAbsUpdates updates;
        while (this.isAlive) {
            try {
                if (this.needGetUpdateState.get() && !this.gettingDifferences.get()) {
                    this.mainHandler.getUpdatesState();
                }
                synchronized(this.mainHandler.updatesQueue) {
                    updates = this.mainHandler.updatesQueue.pollFirst();
                }
                if (updates == null) {
                    synchronized(this.mainHandler.updatesQueue) {
                        try {
                            this.mainHandler.updatesQueue.wait();
                        } catch (InterruptedException e) {
                            BotLogger.error(LOGTAG, e);
                        }
                    }
                } else {
                    this.mainHandler.onTLAbsUpdates(updates);
                }
            } catch (Exception e) {
                BotLogger.error(LOGTAG, e);
            }
        }
        BotLogger.info(LOGTAG, "Quitting UpdatesHandlerThread.");
    }
}
