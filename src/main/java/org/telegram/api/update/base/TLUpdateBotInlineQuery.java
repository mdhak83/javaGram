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
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update channel group
 */
public class TLUpdateBotInlineQuery extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x54826690;

    private static final int FLAG_GEOPOINT = 0x00000001; // 0

    private int flags;
    private long queryId;
    private int userId;
    private String query;
    private TLAbsGeoPoint geo;
    private String offset;

    public TLUpdateBotInlineQuery() {
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

    public String getQuery() {
        return query;
    }

    public String getOffset() {
        return offset;
    }

    public int getFlags() {
        return flags;
    }

    public TLAbsGeoPoint getGeo() {
        return geo;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(this.queryId, stream);
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeTLString(this.query, stream);
        if ((flags & FLAG_GEOPOINT) != 0) {
            StreamingUtils.writeTLObject(this.geo, stream);
        }
        StreamingUtils.writeTLString(this.offset, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.queryId = StreamingUtils.readLong(stream);
        this.userId = StreamingUtils.readInt(stream);
        this.query = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_GEOPOINT) != 0) {
            this.geo = StreamingUtils.readTLObject(stream, context, TLAbsGeoPoint.class);
        }
        this.offset = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "updateBotInlineQuery#54826690";
    }

}