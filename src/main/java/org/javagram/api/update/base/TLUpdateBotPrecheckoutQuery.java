/**
 * This file is part of Support Bot.
 *
 *     Foobar is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.javagram.api.update.base;

import org.javagram.api.payment.base.TLPaymentRequestedInfo;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLUpdateBotPrecheckoutQuery extends TLAbsUpdate {
    public static final int CLASS_ID = 0x5d2f3aa9;

    private static final int FLAG_INFO                     = 0x00000001; // 0
    private static final int FLAG_SHIPPING_OPTION_ID       = 0x00000002; // 1

    private long queryId;
    private int userId;
    private TLBytes payload;
    private TLPaymentRequestedInfo info;
    private String shippingOptionId;
    private String currency;
    private long totalAmount;

    public TLUpdateBotPrecheckoutQuery() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getQueryId() {
        return queryId;
    }

    public int getUserId() {
        return userId;
    }

    public TLBytes getPayload() {
        return payload;
    }

    public TLPaymentRequestedInfo getInfo() {
        return info;
    }

    public String getShippingOptionId() {
        return shippingOptionId;
    }

    public String getCurrency() {
        return currency;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(queryId, stream);
        StreamingUtils.writeInt(userId, stream);
        StreamingUtils.writeTLBytes(payload, stream);
        if ((flags & FLAG_INFO) != 0) {
            StreamingUtils.writeTLObject(info, stream);
        }
        if ((flags & FLAG_SHIPPING_OPTION_ID) != 0) {
            StreamingUtils.writeTLString(shippingOptionId, stream);
        }
        StreamingUtils.writeTLString(currency, stream);
        StreamingUtils.writeLong(totalAmount, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        queryId = StreamingUtils.readLong(stream);
        userId = StreamingUtils.readInt(stream);
        payload = StreamingUtils.readTLBytes(stream, context);
        if ((flags & FLAG_INFO) != 0) {
            info = StreamingUtils.readTLObject(stream, context, TLPaymentRequestedInfo.class);
        }
        if ((flags & FLAG_SHIPPING_OPTION_ID) != 0) {
            shippingOptionId = StreamingUtils.readTLString(stream);
        }
        currency = StreamingUtils.readTLString(stream);
        totalAmount = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "updateBotPrecheckoutQuery#5d2f3aa9";
    }

}