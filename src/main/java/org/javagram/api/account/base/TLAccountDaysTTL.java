package org.javagram.api.account.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Time to live in days of the current account
 * accountDaysTTL#b8d0afdf days:int = AccountDaysTTL;
 */
public class TLAccountDaysTTL extends TLObject {

    /**
     * The constant CLASS_ID of this class.
     */
    public static final int CLASS_ID = 0xb8d0afdf;

    /**
     * This account will self-destruct in the specified number of days
     */
    private int days;

    public TLAccountDaysTTL() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getDays() {
        return this.days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.days, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.days = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "account.accountDaysTLL#b8d0afdf";
    }
    
}