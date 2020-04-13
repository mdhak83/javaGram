package org.javagram.mtproto.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class BytesCache {

    private static final BytesCache INSTANCE = new BytesCache("GlobalByteCache");
    private final String logtag;
    private final int[] sizes = new int[]{64, 128, 3072, 20 * 1024, 40 * 1024};
    private final int maxSize = 40 * 1024;
    private final boolean TRACK_ALLOCATIONS = false;
    private final Map<Integer, HashSet<byte[]>> fastBuffers = new HashMap<>();
    private final Set<byte[]> mainFilter = new HashSet<>();
    private final Set<byte[]> byteBuffer = new HashSet<>();
    private final Map<byte[], StackTraceElement[]> references = new WeakHashMap<>();

    public BytesCache(String logTag) {
        this.logtag = logTag;
        for (int i = 0; i < this.sizes.length; i++) {
            this.fastBuffers.put(this.sizes[i], new HashSet<>());
        }
    }

    public static BytesCache getInstance() {
        return INSTANCE;
    }

    public synchronized void put(byte[] data) {
        this.references.remove(data);

        if (this.mainFilter.add(data)) {
            for (Integer i : this.sizes) {
                if (data.length == i) {
                    this.fastBuffers.get(i).add(data);
                    return;
                }
            }
            if (data.length <= this.maxSize) {
                return;
            }
            this.byteBuffer.add(data);
        }
    }

    public synchronized byte[] allocate(int minSize) {
        if (minSize <= this.maxSize) {
            for (int i = 0; i < this.sizes.length; i++) {
                if (minSize < this.sizes[i]) {
                    if (!this.fastBuffers.get(this.sizes[i]).isEmpty()) {
                        Iterator<byte[]> interator = this.fastBuffers.get(this.sizes[i]).iterator();
                        byte[] res = interator.next();
                        interator.remove();

                        this.mainFilter.remove(res);

                        if (this.TRACK_ALLOCATIONS) {
                            this.references.put(res, Thread.currentThread().getStackTrace());
                        }

                        return res;
                    }

                    return new byte[this.sizes[i]];
                }
            }
        } else {
            byte[] res = null;
            for (byte[] cached : this.byteBuffer) {
                if (cached.length < minSize) {
                    continue;
                }
                if (res == null) {
                    res = cached;
                } else if (res.length > cached.length) {
                    res = cached;
                }
            }

            if (res != null) {
                this.byteBuffer.remove(res);
                this.mainFilter.remove(res);
                if (this.TRACK_ALLOCATIONS) {
                    this.references.put(res, Thread.currentThread().getStackTrace());
                }
                return res;
            }
        }

        return new byte[minSize];
    }

}