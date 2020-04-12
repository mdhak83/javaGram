package org.javagram.api.input.reportreason;

public class TLInputReportReasonSpam extends TLAbsReportReason {
    
    public static final int CLASS_ID = 0x58dbcab8;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputReportReasonSpam#58dbcab8";
    }

}
