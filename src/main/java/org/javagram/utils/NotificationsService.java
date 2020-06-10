package org.javagram.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class NotificationsService {
    
    private static final String LOGTAG = "[NotificationsService]";
    private static NotificationsService instance;

    private static int notificationsCounter;
    public static final int updatesInvalidated = notificationsCounter++;
    public static final int needGetUpdates = notificationsCounter++;

    private final NotificationsThread thread;
    private final ConcurrentLinkedDeque<Notification> notificationsQueue = new ConcurrentLinkedDeque<>();
    private final ConcurrentHashMap<Integer /*notificationId*/, ConcurrentLinkedDeque<NotificationObserver>> observers = new ConcurrentHashMap<>();

    private NotificationsService() {
        this.thread = new NotificationsThread();
        this.thread.start();
    }

    public static synchronized NotificationsService getInstance() {
        if (instance == null) {
            instance = new NotificationsService();
        }
        return instance;
    }

    public void addObserver(NotificationObserver observer, int notificationId) {
        synchronized(this.observers) {
            if (this.observers.containsKey(notificationId)) {
                if (!this.observers.get(notificationId).contains(observer)) {
                    this.observers.get(notificationId).add(observer);
                }
            } else {
                final ConcurrentLinkedDeque<NotificationObserver> newObservers = new ConcurrentLinkedDeque<>();
                newObservers.add(observer);
                this.observers.put(notificationId, newObservers);
            }
        }
    }

    public void removeObserver(NotificationObserver observer, int notificationId) {
        synchronized(this.observers) {
            if (this.observers.containsKey(notificationId)) {
                this.observers.get(notificationId).remove(observer);
            }
        }
    }

    public void postNotification(int notificationId, Object... args) {
        final Notification notification = new Notification(notificationId, args);
        synchronized(this.notificationsQueue) {
            this.notificationsQueue.addLast(notification);
            this.notificationsQueue.notifyAll();
        }
    }

    private void handleNotification(Notification notification) {
        ConcurrentLinkedDeque<NotificationObserver> notificationObservers = null;
        synchronized(this.observers) {
            if (this.observers.containsKey(notification.notificationId)) {
                notificationObservers = this.observers.get(notification.notificationId);
            }
        }

        if (notificationObservers != null) {
            for (NotificationObserver observer : notificationObservers) {
                observer.onNotificationReceived(notification.notificationId, notification.args);
            }
        }
    }

    public interface NotificationObserver {
        void onNotificationReceived(int notificationId, Object... args);
    }

    private static class Notification {
        
        public int notificationId;
        public Object[] args;

        public Notification(int notificationId, Object... args) {
            this.notificationId = notificationId;
            this.args = args;
        }
        
    }

    private class NotificationsThread extends Thread {
        
        public NotificationsThread() {
            super();
            this.setName("NotificationsThread#" + this.getId());
        }

        @Override
        public void run() {
            Notification currentNotification;
            while (true) {
                currentNotification = NotificationsService.this.notificationsQueue.pollFirst();
                if (currentNotification == null) {
                    try {
                        synchronized(NotificationsService.this.notificationsQueue) {
                            NotificationsService.this.notificationsQueue.wait();
                        }
                    } catch (InterruptedException e) {
                        BotLogger.error(LOGTAG, e);
                    }
                } else {
                    NotificationsService.this.handleNotification(currentNotification);
                }
            }
        }
    }

}