package org.telegram.client.kernel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.MyTLAppConfiguration;

public class DifferenceParametersService implements IDifferenceParametersService {
    
    private final ConcurrentHashMap<Integer, DifferenceParametersService.DifferenceData> differenceDatas = new ConcurrentHashMap<>();
    private final AtomicBoolean loaded = new AtomicBoolean(false);
    private final Object lock = new Object();
    private MyTLAppConfiguration config;

    public DifferenceParametersService() { }
    
    public void build(MyTLAppConfiguration config) {
        this.config = config;
        if (!this.loaded.get()){
            this.loadParamsFromDatabase();
        }
    }

    @Override
    public void setNewUpdateParams(int chatId, @Nullable Integer newPts, @Nullable Integer newSeq, @NotNull Integer newDate) {
        if (!this.differenceDatas.containsKey(chatId)) {
            this.create(chatId);
        }
        synchronized(this.lock) {
            this.differenceDatas.get(chatId).pts = ((newPts == null) || (newPts == 0)) ? differenceDatas.get(chatId).pts : newPts;
            this.differenceDatas.get(chatId).seq = ((newSeq == null) || (newSeq == 0)) ? differenceDatas.get(chatId).seq : newSeq;
            this.differenceDatas.get(chatId).date = (newDate < differenceDatas.get(chatId).date) ? differenceDatas.get(chatId).date : newDate;
            this.config.getDatabaseManager().updateDifferencesData(chatId, this.differenceDatas.get(chatId).pts, this.differenceDatas.get(chatId).date, this.differenceDatas.get(chatId).seq);
        }
    }

    @Override
    public int getDate(int chatId) {
        if (!this.differenceDatas.containsKey(chatId)) {
            this.create(chatId);
        }
        return this.differenceDatas.get(chatId).date;
    }

    @Override
    public int getPts(int chatId) {
        if (!this.differenceDatas.containsKey(chatId)) {
            this.create(chatId);
        }
        return this.differenceDatas.get(chatId).pts;
    }

    @Override
    public int getSeq(int chatId) {
        if (!this.differenceDatas.containsKey(chatId)) {
            this.create(chatId);
        }
        return this.differenceDatas.get(chatId).seq;
    }

    @Override
    public boolean mustGetDifferences(int chatId, int pts, @Nullable Integer ptsCount, int seq, @Nullable Integer seqStart) {
        synchronized(this.lock) {
            boolean mustGetDifferences = false;
            if (pts > 0) {
                final int newPts = getPts(chatId) + ((ptsCount == null) ? 0 : ptsCount);
                if (newPts != pts) {
                    mustGetDifferences = true;
                }
            } else if (seq > 0) {
                final int newSeqStart = (seqStart == null) ? seq : seqStart;
                if ((newSeqStart != (getSeq(chatId) + 1)) && (newSeqStart > getSeq(chatId))) {
                    mustGetDifferences = true;
                }
            }
            return mustGetDifferences;
        }
    }

    private void loadParamsFromDatabase() {
        synchronized(this.lock) {
            final Map<Integer, int[]> differencesDatas = this.config.getDatabaseManager().getDifferencesData();
            differencesDatas.entrySet().forEach((entry) -> {
                final DifferenceParametersService.DifferenceData data = new DifferenceParametersService.DifferenceData();
                data.pts = entry.getValue()[0];
                data.date = entry.getValue()[1];
                data.seq = entry.getValue()[2];
                this.differenceDatas.put(entry.getKey(), data);
            });
            this.loaded.set(true);
        }
    }

    private void create(int chatId) {
        synchronized (lock) {
            if (!this.differenceDatas.containsKey(chatId)) {
                this.differenceDatas.put(chatId, new DifferenceParametersService.DifferenceData());
                this.config.getDatabaseManager().updateDifferencesData(chatId, 0, 0, 0);
            }
        }
    }

    private class DifferenceData {
        int pts;
        int date;
        int seq;

        DifferenceData() {
            this.pts = 0;
            this.date = 0;
            this.seq = 0;
        }
    }

}
