package org.telegram.api.phone.base.call.discardreason;

import org.telegram.api._primitives.TLObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPhoneCallDiscardReasonHangup extends TLAbsPhoneCallDiscardReason {
    public static final int CLASS_ID = 0x57adc690;

    public TLPhoneCallDiscardReasonHangup() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "phoneCallDiscardReasonHangup#57adc690";
    }

}
