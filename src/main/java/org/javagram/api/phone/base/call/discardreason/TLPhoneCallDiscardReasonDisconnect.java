package org.javagram.api.phone.base.call.discardreason;

import org.javagram.api._primitives.TLObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPhoneCallDiscardReasonDisconnect extends TLAbsPhoneCallDiscardReason {
    public static final int CLASS_ID = 0xe095c1a0;

    public TLPhoneCallDiscardReasonDisconnect() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "phoneCallDiscardReasonDisconnect#e095c1a0";
    }

}
