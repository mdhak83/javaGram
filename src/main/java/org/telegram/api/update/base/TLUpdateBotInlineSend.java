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

package org.telegram.api.update.base;

import org.telegram.api.geo.base.point.TLAbsGeoPoint;
import org.telegram.api.bot.base.input.TLInputBotInlineMessageId;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update channel group
 */
public class TLUpdateBotInlineSend extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe48f964;

    private static final int FLAG_GEOPOINT = 0x00000001; // 0
    private static final int FLAG_MSG_ID   = 0x00000002; // 1

    private int flags;
    private int userId;
    private String query;
    private TLAbsGeoPoint geo;
    private String id;
    private TLInputBotInlineMessageId msgId;

    public TLUpdateBotInlineSend() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getUserId() {
        return userId;
    }

    public String getQuery() {
        return query;
    }

    public String getId() {
        return id;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeTLString(this.query, stream);
        if ((flags & FLAG_GEOPOINT) != 0) {
            StreamingUtils.writeTLObject(this.geo, stream);
        }
        StreamingUtils.writeTLString(this.id, stream);
        if ((flags & FLAG_GEOPOINT) != 0) {
            StreamingUtils.writeTLObject(this.msgId, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.userId = StreamingUtils.readInt(stream);
        this.query = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_GEOPOINT) != 0) {
            this.geo = StreamingUtils.readTLObject(stream, context, TLAbsGeoPoint.class);
        }
        this.id = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_GEOPOINT) != 0) {
            this.msgId = StreamingUtils.readTLObject(stream, context, TLInputBotInlineMessageId.class);
        }
    }

    @Override
    public String toString() {
        return "updateBotInlineSend#e48f964";
    }

}