package org.telegram.api.input.reportreason;

public class TLInputReportReasonPornography extends TLAbsReportReason {
    
    public static final int CLASS_ID = 0x2e59d922;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputReportReasonPornography#2e59d922";
    }

}
