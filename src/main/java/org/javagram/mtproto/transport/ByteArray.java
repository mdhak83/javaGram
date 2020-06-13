package org.javagram.mtproto.transport;

import java.util.Arrays;

public class ByteArray {
    
    public int length;
    public byte[] bytes;

    public ByteArray() {
        this.bytes = null;
        this.length = 0;
    }

    public ByteArray(int len) {
        this.bytes = new byte[len];
        this.length = len;
    }

    public ByteArray(ByteArray byteArray) {
        if (byteArray != null) {
            this.bytes = new byte[byteArray.length];
            this.length = byteArray.length;
            System.arraycopy(byteArray.bytes, 0, this.bytes, 0, byteArray.length);
        }
    }

    public ByteArray(byte[] buffer, int len) {
        if (buffer != null && buffer.length >= len) {
            this.bytes = new byte[len];
            this.length = len;
            System.arraycopy(buffer, 0, this.bytes, 0, len);
        }
    }

    public void free() {
        if (this.bytes != null) {
            this.bytes = null;
            this.length = 0;
        }
    }

    public void alloc(int len) {
        if (this.bytes != null) {
            this.free();
        }
        this.bytes = new byte[len];
        this.length = len;
    }

    public boolean isEqualTo(ByteArray byteArray) {
        return byteArray.length == this.length && Arrays.equals(byteArray.bytes, this.bytes);
    }

}