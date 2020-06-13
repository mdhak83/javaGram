package org.javagram.mtproto.transport;

import java.util.ArrayList;
import java.util.List;

public class ByteStream {
    
    private final List<ByteBufferDesc> buffersQueue = new ArrayList<>();

    public ByteStream() { }

    public void append(ByteBufferDesc buffer) {
        if (buffer != null) {
            this.buffersQueue.add(buffer);
        }
    }

    public boolean hasData() {
        int size = buffersQueue.size();
        for (int i = 0; i < size; i++) {
            if (this.buffersQueue.get(i).hasRemaining()) {
                return true;
            }
        }
        return false;
    }

    public void get(ByteBufferDesc dst) {
        if (dst != null) {
            int size = this.buffersQueue.size();
            ByteBufferDesc buffer;
            for (int i = 0; i < size; i++) {
                buffer = this.buffersQueue.get(i);
                if (buffer.remaining() > dst.remaining()) {
                    dst.writeByteArray(buffer.readByteArray(false), buffer.position(), dst.remaining());
                    break;
                }
                dst.writeByteArray(buffer.readByteArray(false), buffer.position(), buffer.remaining());
                if (!dst.hasRemaining()) {
                    break;
                }
            }
        }
    }

    public void discard(int count) {
        int remaining;
        ByteBufferDesc buffer;
        while (count > 0) {
            if (this.buffersQueue.isEmpty()) {
                break;
            }
            buffer = this.buffersQueue.get(0);
            remaining = buffer.remaining();
            if (count < remaining) {
                buffer.position(buffer.position() + count);
                break;
            }
            buffer.reuse();
            this.buffersQueue.remove(0);
            count -= remaining;
        }
    }

    public void clean() {
        if (!this.buffersQueue.isEmpty()) {
            int size = this.buffersQueue.size();
            for (int i = 0 ; i < size ; i++) {
                ByteBufferDesc buffer = this.buffersQueue.get(i);
                buffer.reuse();
            }
            this.buffersQueue.clear();
        }
    }

}