package org.javagram.mtproto.tl;

import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.javagram.utils.StreamingUtils.readInt;
import static org.javagram.utils.StreamingUtils.readLong;
import static org.javagram.utils.StreamingUtils.writeInt;
import static org.javagram.utils.StreamingUtils.writeLong;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 03.11.13
 * Time: 8:22
 */
public class MTPingDelayDisconnect extends TLObject {
    public static final int CLASS_ID = 0xf3427b8c;

    private long pingId;
    private int disconnectDelay;

    public MTPingDelayDisconnect(long pingId, int disconnectDelay) {
        this.pingId = pingId;
        this.disconnectDelay = disconnectDelay;
    }

    public MTPingDelayDisconnect() {

    }

    public long getPingId() {
        return this.pingId;
    }

    public int getDisconnectDelay() {
        return this.disconnectDelay;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeLong(this.pingId, stream);
        writeInt(this.disconnectDelay, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.pingId = readLong(stream);
        this.disconnectDelay = readInt(stream);
    }

    @Override
    public String toString() {
        return "ping_delay_disconnect#f3427b8c";
    }
}
