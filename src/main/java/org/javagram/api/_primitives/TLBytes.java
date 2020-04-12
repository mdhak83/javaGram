package org.javagram.api._primitives;

/**
 * 
 */
public class TLBytes {

    private final byte[] data;
    private final int offset;
    private final int len;

    public TLBytes(byte[] data) {
        this.data = data;
        this.offset = 0;
        this.len = data.length;
    }

    public TLBytes(byte[] data, int offset, int len) {
        this.data = data;
        this.offset = offset;
        this.len = len;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getOffset() {
        return this.offset;
    }

    public int getLength() {
        return this.len;
    }

    public byte[] cleanData() {
        if (this.offset == 0 && this.len == this.data.length) {
            return this.data;
        }
        byte[] result = new byte[this.len];
        System.arraycopy(this.data, this.offset, result, 0, this.len);
        return result;
    }

}