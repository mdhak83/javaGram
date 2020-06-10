package org.javagram.client.kernel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.javagram.MyTLAppConfiguration;

/**
 * This class maintains the current status (pts, seq, date) of all channels/chats.
 * @author Mehdi Dhakouani
 */
public class DifferenceParametersService implements IDifferenceParametersService {
    
    /**
     * Configuration
     */
    private MyTLAppConfiguration config;
    
    /**
     * Whether this service has loaded previous information from the database
     */
    private final AtomicBoolean loaded = new AtomicBoolean(false);
    
    /**
     * Common (id = 0) and Channel Update States
     */
    private final Map<Integer, DifferenceParametersService.DifferenceData> differenceDatas = new HashMap<>();

    public DifferenceParametersService() { }
    
    public void build(MyTLAppConfiguration config) {
        this.config = config;
        if (!this.loaded.get()){
            this.loadParamsFromDatabase();
        }
    }

    @Override
    public void setNewUpdateParams(int chatId, Integer newPts, Integer newSeq, Integer newDate) {
        if (!this.isChatDataAvailable(chatId)) {
            this.createChat(chatId);
        }
        int pts, seq, date;
        synchronized(this.differenceDatas) {
            DifferenceData diffData = this.differenceDatas.get(chatId);
            if ((newPts == null) || (newPts == 0)) {
                pts = diffData.pts;
            } else {
                pts = newPts;
                diffData.pts = pts;
            }
            if ((newSeq == null) || (newSeq == 0)) {
                seq = diffData.seq;
            } else {
                seq = newSeq;
                diffData.seq = seq;
            }
            if (newDate != null && newDate < diffData.date) {
                date = diffData.date;
            } else {
                date = newDate;
                diffData.date = date;
            }
        }
        this.config.getDatabaseManager().updateDifferencesData(chatId, pts, date, seq);
    }

    @Override
    public int getDate(int chatId) {
        if (!this.isChatDataAvailable(chatId)) {
            this.createChat(chatId);
        }
        synchronized(this.differenceDatas) {
            return this.differenceDatas.get(chatId).date;
        }
    }

    @Override
    public int getPts(int chatId) {
        if (!this.isChatDataAvailable(chatId)) {
            this.createChat(chatId);
        }
        synchronized(this.differenceDatas) {
            return this.differenceDatas.get(chatId).pts;
        }
    }

    @Override
    public int getSeq(int chatId) {
        if (!this.isChatDataAvailable(chatId)) {
            this.createChat(chatId);
        }
        synchronized(this.differenceDatas) {
            return this.differenceDatas.get(chatId).seq;
        }
    }

    @Override
    public boolean mustGetDifferences(int chatId, int pts, Integer ptsCount, int seq, Integer seqStart) {
        boolean mustGetDifferences = false;
        if (pts > 0) {
            final int newPts = this.getPts(chatId) + ((ptsCount == null) ? 0 : ptsCount);
            if (newPts != pts) {
                mustGetDifferences = true;
            }
        } else if (seq > 0) {
            final int newSeqStart = (seqStart == null) ? seq : seqStart;
            int currentSeq = this.getSeq(chatId);
            if ((newSeqStart != (currentSeq + 1)) && (newSeqStart > currentSeq)) {
                mustGetDifferences = true;
            }
        }
        return mustGetDifferences;
    }

    private void loadParamsFromDatabase() {
        final Map<Integer, int[]> differencesDatas = this.config.getDatabaseManager().getDifferencesData();
        differencesDatas.entrySet().forEach((entry) -> {
            final DifferenceParametersService.DifferenceData data = new DifferenceParametersService.DifferenceData();
            data.pts = entry.getValue()[0];
            data.date = entry.getValue()[1];
            data.seq = entry.getValue()[2];
            synchronized(this.differenceDatas) {
                this.differenceDatas.put(entry.getKey(), data);
            }
        });
        this.loaded.set(true);
    }

    private void createChat(int chatId) {
        if (!this.isChatDataAvailable(chatId)) {
            synchronized(this.differenceDatas) {
                this.differenceDatas.put(chatId, new DifferenceParametersService.DifferenceData());
            }
            this.config.getDatabaseManager().updateDifferencesData(chatId, 0, 0, 0);
        }
    }
    
    private boolean isChatDataAvailable(int chatId) {
        synchronized(this.differenceDatas) {
            return this.differenceDatas.containsKey(chatId);
        }
    }

    private class DifferenceData {
        protected int pts;
        protected int date;
        protected int seq;

        DifferenceData() {
            this.pts = 0;
            this.date = 0;
            this.seq = 0;
        }
    }

}