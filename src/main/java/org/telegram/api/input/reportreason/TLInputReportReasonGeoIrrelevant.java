package org.telegram.api.input.reportreason;

public class TLInputReportReasonGeoIrrelevant extends TLAbsReportReason {
    
    public static final int CLASS_ID = 0xdbd4feed;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputReportReasonGeoIrrelevant#dbd4feed";
    }

}
