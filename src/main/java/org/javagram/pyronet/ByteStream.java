package org.javagram.pyronet;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.javagram.mtproto.transport.BuffersStorage;
import org.javagram.mtproto.transport.ByteBufferDesc;

public class ByteStream {

    private final List<ByteBufferDesc> queue;

    public ByteStream() {
        // the queue is expected to be relatively small, and iterated often.
        // hence removing the first element will be fast, even when using an ArrayList
        this.queue = new ArrayList<>();
    }

    /**
     * Appends the ByteBuffer instance to the ByteStream.The bytes are not
     * copied, so do not modify the contents of the ByteBuffer.
     *
     * @param buf
     */
    public void append(ByteBufferDesc buf) {
        if (buf == null) {
            throw new NullPointerException();
        }
        this.queue.add(buf);
    }

    /**
     * Returns whether there are any bytes pending in this stream
     *
     * @return
     */
    public boolean hasData() {
        final int size = this.queue.size();
        if (this.queue.stream().anyMatch((aQueue) -> (aQueue.hasRemaining()))) {
            return true;
        }
        return false;
    }

    /**
     * Fills the specified buffer with as much bytes as possible.When N bytes
     * are read, the buffer position will be increased by N
     *
     * @param dst
     */
    public void get(ByteBuffer dst) {
        if (dst == null) {
            throw new NullPointerException();
        }

        for (ByteBufferDesc bufferDesc : this.queue) {
            ByteBuffer data = bufferDesc.buffer.slice();

            if (data.remaining() > dst.remaining()) {
                data.limit(dst.remaining());
                dst.put(data);
                break;
            }

            dst.put(data);

            if (!dst.hasRemaining()) {
                break;
            }
        }
    }

    /**
     * Discards the specified amount of bytes from the stream.
     *
     * @param count
     * @throws PyroException if it failed to discard the specified number of
     * bytes
     */
    public void discard(int count) {
        int original = count;

        while (count > 0) {
            ByteBufferDesc data = this.queue.get(0);

            if (count < data.buffer.remaining()) {
                data.position(data.position() + count);
                count = 0;
                break;
            }

            this.queue.remove(0);
            BuffersStorage.getInstance().reuseFreeBuffer(data);
            count -= data.buffer.remaining();
        }

        if (count != 0) {
            throw new PyroException("discarded " + (original - count) + "/" + original + " bytes");
        }
    }

}