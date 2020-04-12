package org.telegram.api.updates.base.channel.differences;

import org.telegram.api._primitives.TLObject;

/**
 * the updates.ChannelDifference type.
 */
public abstract class TLAbsUpdatesChannelDifference extends TLObject {

    protected static final int FLAG_FINAL = 0x00000001;   // 0 : Whether there are more updates that must be fetched (always false)
    protected static final int FLAG_TIMEOUT = 0x00000002; // 1 : Clients are supposed to refetch the channel difference after timeout seconds have elapsed

    /**
     * Clients are supposed to refetch the channel difference after timeout seconds have elapsed
     */
    protected int timeout;
    
    public TLAbsUpdatesChannelDifference() {
        super();
    }

    public boolean isFinal() {
        return this.isFlagSet(FLAG_FINAL);
    }
    
    public void setFinal(boolean value) {
        this.setFlag(FLAG_FINAL, value);
    }

    public boolean hasTimeout() {
        return this.isFlagSet(FLAG_TIMEOUT);
    }
    
    public void setTimeout(int timeout) {
        this.timeout = timeout;
        this.setFlag(FLAG_TIMEOUT, true);
    }

}