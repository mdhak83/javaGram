package org.javagram.mtproto.schedule;

import org.javagram.mtproto.CallWrapper;
import org.javagram.mtproto.MTProto;
import org.javagram.mtproto.log.Logger;
import org.javagram.mtproto.time.TimeOverlord;
import org.javagram.mtproto.tl.MTInvokeAfter;
import org.javagram.mtproto.tl.MTMessage;
import org.javagram.mtproto.tl.MTMessagesContainer;
import org.javagram.mtproto.tl.MTMsgsAck;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Scheduler {

    // Share identity values across all connections to avoid collisions
    private static final AtomicInteger MESSAGES_IDS = new AtomicInteger(1);
    private static final Map<Long, Long> ID_GENERATION_TIME = new ConcurrentHashMap<>();
    private static final int SCHEDULER_TIMEOUT_MILLISECONDS = 15 * 1000;
    private static final long CONFIRM_TIMEOUT_milliSECONDS = 60 * 1000;
    private static final int MAX_WORKLOAD_SIZE = 3 * 1024;
    private static final int BIG_MESSAGE_SIZE = 1024;
    private static final long RETRY_TIMEOUT = 5 * 1000;
    private static final int MAX_ACK_COUNT = 5;
    private static final int PRIORITY_HIGH = 1;
    private static final int PRIORITY_NORMAL = 0;
    private static final int STATE_QUEUED = 0;
    private static final int STATE_SENT = 1;
    private static final int STATE_CONFIRMED = 2;
    private final String logtag;
    private final CallWrapper wrapper;
    private final Map<Integer, SchedulerPackage> messages = Collections.synchronizedSortedMap(new TreeMap<>());
    private final Set<Long> currentMessageGeneration = new HashSet<>();
    private final Set<Long> confirmedMessages = new HashSet<>();
    private long firstConfirmTime;
    private long lastMessageId;
    private long lastDependId;
    private int seqNo;

    public Scheduler(MTProto mtProto, CallWrapper wrapper) {
        this.logtag = "MTProto#" + mtProto.getInstanceIndex() + "#Scheduller";
        this.wrapper = wrapper;
    }

    public synchronized void updateMessageId(long newLastMessageId) {
        if (newLastMessageId > this.lastMessageId) {
            this.lastMessageId = newLastMessageId;
        }
    }

    private synchronized long generateMessageId() {
        long messageId = TimeOverlord.getInstance().createWeakMessageId();
        if (messageId <= this.lastMessageId) {
            messageId = this.lastMessageId = this.lastMessageId + 4;
        }
        while (ID_GENERATION_TIME.containsKey(messageId)) {
            messageId += 4;
        }
        this.lastMessageId = messageId;
        ID_GENERATION_TIME.put(messageId, getCurrentTime());
        this.currentMessageGeneration.add(messageId);
        return messageId;
    }

    private synchronized int generateSeqNoWeak() {
        return this.seqNo * 2;
    }

    private synchronized int generateSeqNo() {
        int res = this.seqNo * 2 + 1;
        this.seqNo++;
        return res;
    }

    private synchronized void generateParams(SchedulerPackage schedulerPackage) {
        schedulerPackage.messageId = generateMessageId();
        schedulerPackage.seqNo = generateSeqNo();
        schedulerPackage.idGenerationTime = getCurrentTime();
        schedulerPackage.relatedMessageIds.add(schedulerPackage.messageId);
        schedulerPackage.generatedMessageIds.add(schedulerPackage.messageId);
    }

    private long getCurrentTime() {
        return System.nanoTime() / 1000000;
    }

    public long getMessageIdGenerationTime(long msgId) {
        if (ID_GENERATION_TIME.containsKey(msgId)) {
            return ID_GENERATION_TIME.get(msgId);
        }
        return 0;
    }

    public int postMessageDelayed(TLObject object, boolean isRpc, long timeout, int delay, int contextId, boolean highPrioroty) {
        int id = MESSAGES_IDS.incrementAndGet();
        SchedulerPackage schedulerPackage = new SchedulerPackage(id);
        schedulerPackage.object = object;
        schedulerPackage.addTime = getCurrentTime();
        schedulerPackage.scheduleTime = schedulerPackage.addTime + delay;
        schedulerPackage.expiresTime = schedulerPackage.scheduleTime + timeout;
        schedulerPackage.ttlTime = schedulerPackage.scheduleTime + timeout * 2;
        schedulerPackage.isRpc = isRpc;
        schedulerPackage.queuedToChannel = contextId;
        schedulerPackage.priority = highPrioroty ? PRIORITY_HIGH : PRIORITY_NORMAL;
        schedulerPackage.isDepend = highPrioroty;
        schedulerPackage.supportTag = object.toString();
        schedulerPackage.serverErrorCount = 0;
        this.messages.put(id, schedulerPackage);
        return id;
    }

    public int postMessage(TLObject object, boolean isApi, long timeout) {
        return this.postMessageDelayed(object, isApi, timeout, 0, -1, false);
    }

    public int postMessage(TLObject object, boolean isApi, long timeout, boolean highPrioroty) {
        return this.postMessageDelayed(object, isApi, timeout, 0, -1, highPrioroty);
    }

    public synchronized void prepareScheduller(PrepareSchedule prepareSchedule, int[] connectionIds) {
        long time = getCurrentTime();

        // Clear packages for unknown channels
        outer:
        for (SchedulerPackage schedulerPackage : this.messages.values().toArray(new SchedulerPackage[0])) {
            if (schedulerPackage.queuedToChannel != -1) {
                for (int id : connectionIds) {
                    if (schedulerPackage.queuedToChannel == id) {
                        continue outer;
                    }
                }
                this.forgetMessage(schedulerPackage.id);
            }
        }

        // If there are no connections provide default delay
        if (connectionIds.length == 0) {
            prepareSchedule.setDelay(SCHEDULER_TIMEOUT_MILLISECONDS);
            prepareSchedule.setAllowedContexts(connectionIds);
            prepareSchedule.setDoWait(true);
            return;
        }

        long minDelay = SCHEDULER_TIMEOUT_MILLISECONDS;
        boolean allConnections = false;
        boolean doWait = true;
        final Set<Integer> supportedConnections = new HashSet<>();
        for (SchedulerPackage schedulerPackage : this.messages.values().toArray(new SchedulerPackage[0])) {
            boolean isPendingPackage = false;
            long packageTime = 0;

            if (schedulerPackage.state == STATE_QUEUED) {
                isPendingPackage = true;
                if (schedulerPackage.scheduleTime <= time) {
                    packageTime = 0;
                } else {
                    packageTime = Math.max(schedulerPackage.scheduleTime - time, 0);
                }
            } else if (schedulerPackage.state == STATE_SENT) {
                if (getCurrentTime() <= schedulerPackage.expiresTime) {
                    if (time - schedulerPackage.lastAttemptTime >= RETRY_TIMEOUT) {
                        isPendingPackage = true;
                        packageTime = 0;
                    }
                }
            }

            if (isPendingPackage) {
                if (schedulerPackage.queuedToChannel == -1) {
                    allConnections = true;
                } else {
                    supportedConnections.add(schedulerPackage.queuedToChannel);
                }

                if (packageTime == 0) {
                    minDelay = 0;
                    doWait = false;
                } else {
                    minDelay = Math.min(packageTime, minDelay);
                }
            }
	}
        prepareSchedule.setDoWait(doWait);
        prepareSchedule.setDelay(minDelay);

        if (allConnections) {
            prepareSchedule.setAllowedContexts(connectionIds);
        } else {
            Integer[] allowedBoxed = supportedConnections.toArray(new Integer[0]);
            int[] allowed = new int[allowedBoxed.length];
            for (int i = 0; i < allowed.length; i++) {
                allowed[i] = allowedBoxed[i];
            }
            prepareSchedule.setAllowedContexts(allowed);
        }
    }

    public synchronized void registerFastConfirm(long msgId, int fastConfirm) {
        for (SchedulerPackage schedulerPackage : this.messages.values().toArray(new SchedulerPackage[0])) {
            boolean contains = false;
            for (Long relatedMsgId : schedulerPackage.relatedMessageIds) {
                if (relatedMsgId == msgId) {
                    contains = true;
                    break;
                }
            }
            if (contains) {
                schedulerPackage.relatedFastConfirm.add(fastConfirm);
            }
        }
    }

    public int mapSchedulerId(long msgId) {
        for (SchedulerPackage schedulerPackage : this.messages.values().toArray(new SchedulerPackage[0])) {
            if (schedulerPackage.generatedMessageIds.contains(msgId)) {
                return schedulerPackage.id;
            }
        }
        return 0;
    }

    public void resetMessageId() {
        this.lastMessageId = 0;
        this.lastDependId = 0;
    }

    public void resetSession() {
        this.lastMessageId = 0;
        this.lastDependId = 0;
        this.seqNo = 0;
        this.currentMessageGeneration.clear();
        for (SchedulerPackage schedulerPackage : this.messages.values().toArray(new SchedulerPackage[0])) {
            schedulerPackage.idGenerationTime = 0;
            schedulerPackage.dependMessageId = 0;
            schedulerPackage.messageId = 0;
            schedulerPackage.seqNo = 0;
        }
    }

    public boolean isMessageFromCurrentGeneration(long msgId) {
        return this.currentMessageGeneration.contains(msgId);
    }

    public void resendAsNewMessage(long msgId) {
        this.resendAsNewMessageDelayed(msgId, 0);
    }

    public void resendAsNewMessageDelayed(long msgId, long delay) {
        for (SchedulerPackage schedulerPackage : this.messages.values().toArray(new SchedulerPackage[0])) {
            if (schedulerPackage.relatedMessageIds.contains(msgId)) {
                schedulerPackage.idGenerationTime = 0;
                schedulerPackage.dependMessageId = 0;
                schedulerPackage.messageId = 0;
                schedulerPackage.seqNo = 0;
                schedulerPackage.state = STATE_QUEUED;
                schedulerPackage.scheduleTime = getCurrentTime() + delay;
                Logger.d(this.logtag, "Resending as new #" + schedulerPackage.id);
            }
        }
    }

    public void resendMessage(long msgId) {
        for (SchedulerPackage schedulerPackage : this.messages.values().toArray(new SchedulerPackage[0])) {
            if (schedulerPackage.relatedMessageIds.contains(msgId)) {
                schedulerPackage.state = STATE_QUEUED;
                schedulerPackage.lastAttemptTime = 0;
            }
        }
    }

    public int[] mapFastConfirm(int fastConfirm) {
        final List<Integer> res = new ArrayList<>();
        for (SchedulerPackage schedulerPackage : this.messages.values().toArray(new SchedulerPackage[0])) {
            if (schedulerPackage.state == STATE_SENT) {
                if (schedulerPackage.relatedFastConfirm.contains(fastConfirm)) {
                    res.add(schedulerPackage.id);
                }
            }
        }
        int[] res2 = new int[res.size()];
        for (int i = 0; i < res2.length; i++) {
            res2[i] = res.get(i);
        }
        return res2;
    }

    public void onMessageFastConfirmed(int fastConfirm) {
        for (SchedulerPackage schedulerPackage : messages.values().toArray(new SchedulerPackage[0])) {
            if (schedulerPackage.state == STATE_SENT) {
                if (schedulerPackage.relatedFastConfirm.contains(fastConfirm)) {
                    schedulerPackage.state = STATE_CONFIRMED;
                }
            }
        }
    }

    public void onMessageConfirmed(long msgId) {
        for (SchedulerPackage schedulerPackage : this.messages.values().toArray(new SchedulerPackage[0])) {
            if (schedulerPackage.state == STATE_SENT) {
                if (schedulerPackage.relatedMessageIds.contains(msgId)) {
                    schedulerPackage.state = STATE_CONFIRMED;
                }
            }
        }
    }

    public void confirmMessage(long msgId) {
        synchronized(this.confirmedMessages) {
            this.confirmedMessages.add(msgId);
            if (this.firstConfirmTime == 0) {
                this.firstConfirmTime = getCurrentTime();
            }
        }
    }

    public boolean isMessaveConfirmed(long msgId) {
        synchronized(this.confirmedMessages) {
            return this.confirmedMessages.contains(msgId);
        }
    }

    public synchronized void forgetMessageByMsgId(long msgId) {
        int scId = mapSchedulerId(msgId);
        if (scId > 0) {
            this.forgetMessage(scId);
        }
    }

    public synchronized void forgetMessage(int id) {
        Logger.d(this.logtag, "Forgetting message: #" + id);
        this.messages.remove(id);
    }

    private synchronized ArrayList<SchedulerPackage> actualPackages(int contextId) {
        ArrayList<SchedulerPackage> foundedPackages = new ArrayList<SchedulerPackage>();
        long time = getCurrentTime();
        for (SchedulerPackage schedulerPackage : this.messages.values().toArray(new SchedulerPackage[0])) {
            if (schedulerPackage.queuedToChannel != -1 && contextId != schedulerPackage.queuedToChannel) {
                continue;
            }
            boolean isPendingPackage = false;

            if (schedulerPackage.ttlTime <= getCurrentTime()) {
                this.forgetMessage(schedulerPackage.id);
                continue;
            }

            if (schedulerPackage.state == STATE_QUEUED) {
                if (schedulerPackage.scheduleTime <= time) {
                    isPendingPackage = true;
                }
            } else if (schedulerPackage.state == STATE_SENT) {
                if (getCurrentTime() <= schedulerPackage.expiresTime) {
                    if (getCurrentTime() - schedulerPackage.lastAttemptTime >= RETRY_TIMEOUT) {
                        isPendingPackage = true;
                    }
                }
            }

            if (isPendingPackage) {
                if (schedulerPackage.serialized == null) {
                    try {
                        if (schedulerPackage.isRpc) {
                            schedulerPackage.serialized = this.wrapper.wrapObject((TLMethod) schedulerPackage.object).serialize();
                        } else {
                            schedulerPackage.serialized = schedulerPackage.object.serialize();
                        }
                    } catch (IOException e) {
                        Logger.e(this.logtag, e);
                        this.forgetMessage(schedulerPackage.id);
                        continue;
                    }
                }

                foundedPackages.add(schedulerPackage);
            }
        }
        return foundedPackages;
    }

    public synchronized PreparedPackage doSchedule(int contextId, boolean isInited) {
        ArrayList<SchedulerPackage> foundedPackages = actualPackages(contextId);

        synchronized(this.confirmedMessages) {
            if (foundedPackages.size() == 0 && (this.confirmedMessages.size() <= MAX_ACK_COUNT || (System.nanoTime() - this.firstConfirmTime) < CONFIRM_TIMEOUT_milliSECONDS)) {
                return null;
            }
        }

        boolean useHighPriority = false;

        for (SchedulerPackage p : foundedPackages) {
            if (p.priority == PRIORITY_HIGH) {
                useHighPriority = true;
                break;
            }
        }

        ArrayList<SchedulerPackage> packages = new ArrayList<SchedulerPackage>();

        if (useHighPriority) {
            Logger.d("Scheduller", "Using high priority scheduling");
            int totalSize = 0;
            for (SchedulerPackage p : foundedPackages) {
                if (p.priority == PRIORITY_HIGH) {
                    packages.add(p);
                    totalSize += p.serialized.length;
                    if (totalSize > MAX_WORKLOAD_SIZE) {
                        break;
                    }
                }
            }
        } else {
            int totalSize = 0;
            for (SchedulerPackage p : foundedPackages) {
                packages.add(p);
                Logger.d("Scheduller", "Prepare package: " + p.supportTag + " of size " + p.serialized.length);
                totalSize += p.serialized.length;
                Logger.d("Scheduller", "Total size: " + totalSize);
                if (totalSize > MAX_WORKLOAD_SIZE) {
                    break;
                }
            }
        }

        Logger.d(this.logtag, "Iteration: count: " + packages.size() + ", confirm:" + this.confirmedMessages.size());
        Logger.d(this.logtag, "Building package");
        if (foundedPackages.size() == 0 && this.confirmedMessages.size() != 0) {
            Long[] msgIds;
            synchronized(this.confirmedMessages) {
                msgIds = this.confirmedMessages.toArray(new Long[this.confirmedMessages.size()]);
                this.confirmedMessages.clear();
            }
            MTMsgsAck ack = new MTMsgsAck(msgIds);
            Logger.d(this.logtag, "Single msg_ack");
            try {
                return new PreparedPackage(generateSeqNoWeak(), generateMessageId(), ack.serialize(), useHighPriority);
            } catch (IOException e) {
                Logger.e(this.logtag, e);
                return null;
            }
        } else if (foundedPackages.size() == 1 && this.confirmedMessages.size() == 0) {
            SchedulerPackage schedulerPackage = foundedPackages.get(0);
            schedulerPackage.state = STATE_SENT;
            if (schedulerPackage.idGenerationTime == 0) {
                generateParams(schedulerPackage);
            }
            Logger.d(this.logtag, "Single package: #" + schedulerPackage.id + " " + schedulerPackage.supportTag + " (" + schedulerPackage.messageId + ", " + schedulerPackage.seqNo + ")");
            schedulerPackage.writtenToChannel = contextId;
            schedulerPackage.lastAttemptTime = getCurrentTime();
            return new PreparedPackage(schedulerPackage.seqNo, schedulerPackage.messageId, schedulerPackage.serialized, useHighPriority);
        } else {
            MTMessagesContainer container = new MTMessagesContainer();
            if ((this.confirmedMessages.size() > 0 && !useHighPriority) || (!isInited)) {
                try {
                    Long[] msgIds;
                    synchronized(this.confirmedMessages) {
                        msgIds = this.confirmedMessages.toArray(new Long[0]);
                        this.confirmedMessages.clear();
                    }
                    MTMsgsAck ack = new MTMsgsAck(msgIds);
                    Logger.d(this.logtag, "Adding msg_ack: " + msgIds.length);
                    container.getMessages().add(new MTMessage(generateMessageId(), generateSeqNoWeak(), ack.serialize()));
                } catch (IOException e) {
                    Logger.e(this.logtag, e);
                }
            }
            for (SchedulerPackage schedulerPackage : packages) {
                schedulerPackage.state = STATE_SENT;
                if (schedulerPackage.idGenerationTime == 0) {
                    generateParams(schedulerPackage);
                }

                if (schedulerPackage.isDepend) {
                    if (schedulerPackage.dependMessageId == 0) {
                        if (this.lastDependId > 0) {
                            schedulerPackage.dependMessageId = this.lastDependId;
                        } else {
                            schedulerPackage.dependMessageId = -1;
                        }
                    }

                    this.lastDependId = schedulerPackage.messageId;
                }
                schedulerPackage.writtenToChannel = contextId;
                schedulerPackage.lastAttemptTime = getCurrentTime();
                if (schedulerPackage.isDepend && schedulerPackage.dependMessageId > 0) {

                    Logger.d(this.logtag, "Adding package: #" + schedulerPackage.id + " " + schedulerPackage.supportTag + " (" + schedulerPackage.messageId + " on " + schedulerPackage.dependMessageId + ", " + schedulerPackage.seqNo + ")");

                    MTInvokeAfter invokeAfter = new MTInvokeAfter(schedulerPackage.dependMessageId, schedulerPackage.serialized);
                    try {
                        container.getMessages().add(new MTMessage(schedulerPackage.messageId, schedulerPackage.seqNo, invokeAfter.serialize()));
                    } catch (IOException e) {
                        Logger.e(this.logtag, e);
                        // Never happens
                    }
                } else {
                    Logger.d(this.logtag, "Adding package: #" + schedulerPackage.id + " " + schedulerPackage.supportTag + " (" + schedulerPackage.messageId + ", " + schedulerPackage.seqNo + ")");
                    container.getMessages().add(new MTMessage(schedulerPackage.messageId, schedulerPackage.seqNo, schedulerPackage.serialized));
                }
            }

            long containerMessageId = generateMessageId();
            int containerSeq = generateSeqNoWeak();

            for (SchedulerPackage schedulerPackage : packages) {
                schedulerPackage.relatedMessageIds.add(containerMessageId);
            }

            Logger.d(this.logtag, "Sending Package (" + containerMessageId + ", " + containerSeq + ")");

            try {
                return new PreparedPackage(containerSeq, containerMessageId, container.serialize(), useHighPriority);
            } catch (IOException e) {
                // Might not happens
                Logger.e(this.logtag, e);
                return null;
            }
        }
    }

    public synchronized void onServerError(long msgId) {

    }

    public synchronized void onConnectionDies(int connectionId) {
        Logger.d(this.logtag, "Connection dies " + connectionId);
        for (SchedulerPackage schedulerPackage : this.messages.values().toArray(new SchedulerPackage[0])) {
            if (schedulerPackage.writtenToChannel != connectionId) {
                continue;
            }

            if (schedulerPackage.queuedToChannel != -1) {
                Logger.d(this.logtag, "Removing: #" + schedulerPackage.id + " " + schedulerPackage.supportTag);
                this.forgetMessage(schedulerPackage.id);
            } else {
                if (schedulerPackage.isRpc) {
                    if (schedulerPackage.state == STATE_CONFIRMED || schedulerPackage.state == STATE_QUEUED) {
                        Logger.d(this.logtag, "Re-schedule: #" + schedulerPackage.id + " " + schedulerPackage.supportTag);
                        schedulerPackage.state = STATE_QUEUED;
                        schedulerPackage.lastAttemptTime = 0;
                    }
                } else {
                    if (schedulerPackage.state == STATE_SENT) {
                        Logger.d(this.logtag, "Re-schedule: #" + schedulerPackage.id + " " + schedulerPackage.supportTag);
                        schedulerPackage.state = STATE_QUEUED;
                        schedulerPackage.lastAttemptTime = 0;
                    }
                }

            }
        }
    }

    private class SchedulerPackage {

        public String supportTag;
        public int id;
        public TLObject object;
        public byte[] serialized;
        public long addTime;
        public long scheduleTime;
        public long expiresTime;
        public long ttlTime;
        public long lastAttemptTime;
        public int writtenToChannel = -1;
        public int queuedToChannel = -1;
        public int state;
        public int priority;
        public boolean isDepend;
        public boolean isSent;
        public long idGenerationTime;
        public long dependMessageId;
        public long messageId;
        public int seqNo;
        public HashSet<Integer> relatedFastConfirm = new HashSet<Integer>();
        public HashSet<Long> relatedMessageIds = new HashSet<Long>();
        public HashSet<Long> generatedMessageIds = new HashSet<Long>();
        public int serverErrorCount;
        public boolean isRpc;

        public SchedulerPackage(int id) {
            this.id = id;
        }
    }
}
