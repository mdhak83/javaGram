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

/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.javagram.api.chat.base.participant.chatparticipants;

import org.javagram.api.chat.base.participant.chatparticipant.TLAbsChatParticipant;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Information of group members unavailable
 * @author Ruben Bermudez
 * @version 2.0
 * @since 02/MAY/2015
 */
public class TLChatParticipantsForbidden extends TLAbsChatParticipants {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfc900c2b;

    private static final int FLAG_SELF = 0x00000001; // 0

    private TLAbsChatParticipant selfParticipant;

    public TLChatParticipantsForbidden() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsChatParticipant getSelfParticipant() {
        return selfParticipant;
    }

    public void setSelfParticipant(TLAbsChatParticipant selfParticipant) {
        this.selfParticipant = selfParticipant;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.chatId, stream);
        if ((this.flags & FLAG_SELF) != 0) {
            StreamingUtils.writeTLObject(selfParticipant, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.chatId = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_SELF) != 0) {
            this.selfParticipant = StreamingUtils.readTLObject(stream, context, TLAbsChatParticipant.class);
        }
    }

    @Override
    public String toString() {
        return "chatParticipantsForbidden#fc900c2b";
    }

}