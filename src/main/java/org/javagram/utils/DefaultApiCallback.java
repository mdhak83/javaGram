package org.javagram.utils;

import org.javagram.MyTLAppConfiguration;
import org.javagram.client.kernel.TelegramApi;
import org.javagram.api.updates.base.TLAbsUpdates;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 11.11.13
 * Time: 7:42
 */
public class DefaultApiCallback {
    
    private static final String LOGTAG = "[KernelCommunicationService]";
    private MyTLAppConfiguration config;

    public DefaultApiCallback() { }
    
    public void build(MyTLAppConfiguration config) {
        this.config = config;
    }
    
    /**
     * On auth cancelled.
     *
     * @param api the api
     */
    public void onAuthCancelled(TelegramApi api) {
        this.config.getApiState().resetAuth();
        BotLogger.severe(LOGTAG, "Auth cancelled");
    }

    /**
     * On updates invalidated.
     *
     * @param api the api
     */
    public void onUpdatesInvalidated(TelegramApi api) {
        if (this.config.getApiState().isAuthenticated()) {
            NotificationsService.getInstance().postNotification(NotificationsService.updatesInvalidated, api);
        }
    }

    /**
     * On update.
     *
     * @param updates the updates
     */
    public void onUpdate(TLAbsUpdates updates) {
        this.config.getKernelCommunicationService().handleUpdates(updates);
    }

}
