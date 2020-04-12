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

import org.javagram.api.json.base.TLDataJSON;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLUpdateBotWebhookJSON extends TLAbsUpdate {
    public static final int CLASS_ID = 0x8317c0c3;

    private TLDataJSON data;

    public TLUpdateBotWebhookJSON() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLDataJSON getData() {
        return data;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(data, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        data = StreamingUtils.readTLObject(stream, context, TLDataJSON.class);
    }

    @Override
    public String toString() {
        return "updateBotWebhookJSON#8317c0c3";
    }

}