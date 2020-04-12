package org.telegram.client.structure;

import org.telegram.api._primitives.TLObject;
import java.util.Comparator;
import org.telegram.api.update.base.TLUpdateChannel;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of April of 2016
 */
public class UpdateWrapper {
    private TLObject update;
    private boolean isChannel;
    private int pts;
    private int ptsCount;
    private int date;
    private int seq;
    private int seqStart;
    private int channelId;
    private boolean checkPts;
    private boolean updatePts;
    private boolean isGettingDifferences;

    public UpdateWrapper(TLObject update) {
        this.update = update;
        if (update instanceof TLUpdateChannel) {
            isChannel = true;
            channelId = ((TLUpdateChannel) update).getChannelId();
        } else {
            channelId = 0;
        }
        checkPts = true;
        updatePts = true;
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
        updatePts = false;
    }

    public void enableGettingDifferences() {
        isGettingDifferences = true;
    }

    public TLObject getUpdate() {
        return update;
    }

    public int getPts() {
        return pts;
    }

    public int getPtsCount() {
        return ptsCount;
    }

    public int getDate() {
        return date;
    }

    public int getSeq() {
        return seq;
    }

    public int getSeqStart() {
        return seqStart;
    }

    public boolean isCheckPts() {
        return checkPts;
    }

    public boolean isUpdatePts() {
        return updatePts;
    }

    public boolean isChannel() {
        return isChannel;
    }

    public boolean isGettingDifferences() {
        return isGettingDifferences;
    }

    public int getChannelId() {
        return channelId;
    }

    @Override
    public String toString() {
        return (update == null) ? null : update.toString();
    }

}