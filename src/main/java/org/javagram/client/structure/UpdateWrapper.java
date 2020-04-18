package org.javagram.client.structure;

import org.javagram.api._primitives.TLObject;
import org.javagram.api.update.base.ITLUpdateChannel;

public class UpdateWrapper {

    private final TLObject update;
    private final int channelId;
    private boolean isChannel;
    private int pts;
    private int ptsCount;
    private int date;
    private int seq;
    private int seqStart;
    private boolean checkPts;
    private boolean updatePts;
    private boolean isGettingDifferences;

    public UpdateWrapper(TLObject update) {
        this.update = update;
        if (update instanceof ITLUpdateChannel) {
            this.isChannel = true;
            this.channelId = ((ITLUpdateChannel) update).getChannelId();
        } else {
            this.channelId = 0;
        }
        this.checkPts = true;
        this.updatePts = true;
    }

    public void setParams(int pts, int ptsCount, int date, int seq, int seqStart) {
        this.pts = pts;
        this.ptsCount = ptsCount;
        this.date = date;
        this.seq = seq;
        this.seqStart = seqStart;
    }

    public void disablePtsCheck() {
        this.checkPts = false;
    }

    public void disableUpdatePts() {
        this.updatePts = false;
    }

    public void enableGettingDifferences() {
        this.isGettingDifferences = true;
    }

    public TLObject getUpdate() {
        return this.update;
    }

    public int getPts() {
        return this.pts;
    }

    public int getPtsCount() {
        return this.ptsCount;
    }

    public int getDate() {
        return this.date;
    }

    public int getSeq() {
        return this.seq;
    }

    public int getSeqStart() {
        return this.seqStart;
    }

    public boolean isCheckPts() {
        return this.checkPts;
    }

    public boolean isUpdatePts() {
        return this.updatePts;
    }

    public boolean isChannel() {
        return this.isChannel;
    }

    public boolean isGettingDifferences() {
        return this.isGettingDifferences;
    }

    public int getChannelId() {
        return this.channelId;
    }

    @Override
    public String toString() {
        return (this.update == null ? null : this.update.toString());
    }

}