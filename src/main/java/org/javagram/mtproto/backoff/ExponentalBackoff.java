package org.javagram.mtproto.backoff;

import org.javagram.mtproto.log.Logger;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ExponentalBackoff {

    private static final int MIN_DELAY = 100;
    private static final int MAX_DELAY = 15000;
    private static final int MAX_FAILURE_COUNT = 50;
 
    private final String logtag;
    private final AtomicInteger currentFailureCount = new AtomicInteger();

    public ExponentalBackoff(String logTag) {
        this.logtag = logTag;
    }

    public void onFailure() throws InterruptedException {
        int val = this.currentFailureCount.incrementAndGet();
        if (val > 50) {
            this.currentFailureCount.compareAndSet(val, MAX_FAILURE_COUNT);
            val = MAX_FAILURE_COUNT;
        }

        int delay = MIN_DELAY + ((MAX_DELAY - MIN_DELAY) / MAX_FAILURE_COUNT) * val;

        synchronized (this) {
            Logger.d(this.logtag, "onFailure: wait " + delay + " ms");
            this.wait(delay);
        }
    }

    public void onFailureNoWait() {
        Logger.d(this.logtag, "onFailureNoWait");
        int val = this.currentFailureCount.incrementAndGet();
        if (val > MAX_FAILURE_COUNT) {
            this.currentFailureCount.compareAndSet(val, MAX_FAILURE_COUNT);
        }
    }

    public void onSuccess() {
        Logger.d(this.logtag, "onSuccess");
        reset();
    }

    public void reset() {
        Logger.d(this.logtag, "reset");
        this.currentFailureCount.set(0);

        synchronized (this) {
            notifyAll();
        }
    }

}