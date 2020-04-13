package org.javagram.mtproto.schedule;

public class PrepareSchedule {

    private long delay;
    private int[] allowedContexts;
    private boolean doWait;

    public boolean isDoWait() {
        return this.doWait;
    }

    public void setDoWait(boolean doWait) {
        this.doWait = doWait;
    }

    public long getDelay() {
        return this.delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public int[] getAllowedContexts() {
        return this.allowedContexts;
    }

    public void setAllowedContexts(int[] allowedContexts) {
        this.allowedContexts = allowedContexts;
    }

}