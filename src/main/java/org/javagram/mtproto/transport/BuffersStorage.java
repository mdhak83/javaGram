/*
 * This is the source code of Telegram for Android v. 1.3.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2014.
 * Update 14/MAY/2019
 * https://github.com/DrKLO/Telegram/blob/master/TMessagesProj/jni/tgnet/BuffersStorage.cpp
 */

package org.javagram.mtproto.transport;

import org.javagram.mtproto.log.Logger;
import java.util.ArrayList;
import java.util.List;

public class BuffersStorage {

    private final static int BUFFER_SIZE_008B = 8;
    private final static int BUFFER_SIZE_128B = 128;
    private final static int BUFFER_SIZE_001K = 1024;
    private final static int BUFFER_SIZE_004K = 4096;
    private final static int BUFFER_SIZE_016K = 16384;
    private final static int BUFFER_SIZE_040K = 40000;
    private final static int BUFFER_SIZE_160K = 160000;
    
    private final List<ByteBufferDesc> freeBuffers8 = new ArrayList<>();
    private final List<ByteBufferDesc> freeBuffers128 = new ArrayList<>();
    private final List<ByteBufferDesc> freeBuffers1024 = new ArrayList<>();
    private final List<ByteBufferDesc> freeBuffers4096 = new ArrayList<>();
    private final List<ByteBufferDesc> freeBuffers16384 = new ArrayList<>();
    private final List<ByteBufferDesc> freeBuffers32768 = new ArrayList<>();
    private final List<ByteBufferDesc> freeBuffersBig = new ArrayList<>();

    private static BuffersStorage Instance = null;
    
    public static BuffersStorage getInstance() {
        BuffersStorage ret;
        synchronized(BuffersStorage.class) {
            if (Instance == null) {
                Instance = new BuffersStorage();
            }
            ret = Instance;
        }
        return ret;
    }

    private BuffersStorage() {
        for (int i = 0; i < 4; i++) {
            this.freeBuffers8.add(new ByteBufferDesc(BUFFER_SIZE_008B));
        }
        for (int i = 0; i < 5; i++) {
            this.freeBuffers1024.add(new ByteBufferDesc(BUFFER_SIZE_001K));
        }
    }

    public ByteBufferDesc getFreeBuffer(int size) {
        int byteCount = 0;
        List<ByteBufferDesc> arrayToGetFrom = null;
        ByteBufferDesc buffer = null;
        if (size <= BUFFER_SIZE_008B) {
            arrayToGetFrom = this.freeBuffers8;
            byteCount = BUFFER_SIZE_008B;
        } else if (size <= BUFFER_SIZE_128B) {
            arrayToGetFrom = this.freeBuffers128;
            byteCount = BUFFER_SIZE_128B;
        } else if (size <= BUFFER_SIZE_001K + 200) {
            arrayToGetFrom = this.freeBuffers1024;
            byteCount = BUFFER_SIZE_001K + 200;
        } else if (size <= BUFFER_SIZE_004K + 200) {
            arrayToGetFrom = this.freeBuffers4096;
            byteCount = BUFFER_SIZE_004K + 200;
        } else if (size <= BUFFER_SIZE_016K + 200) {
            arrayToGetFrom = this.freeBuffers16384;
            byteCount = BUFFER_SIZE_016K + 200;
        } else if (size <= BUFFER_SIZE_040K) {
            arrayToGetFrom = this.freeBuffers32768;
            byteCount = BUFFER_SIZE_040K;
        } else if (size <= BUFFER_SIZE_160K) {
            arrayToGetFrom = this.freeBuffersBig;
            byteCount = BUFFER_SIZE_160K;
        } else {
            buffer = new ByteBufferDesc(size);
        }

        if (arrayToGetFrom != null) {
            synchronized(arrayToGetFrom) {
                if (arrayToGetFrom.size() > 0) {
                    buffer = arrayToGetFrom.get(0);
                    arrayToGetFrom.remove(0);
                }
            }

            if (buffer == null) {
                buffer = new ByteBufferDesc(byteCount);
                Logger.d("[BuffersStorage]", "Creating new " + byteCount + " buffer");
            }
        }

        if (buffer != null) {
            buffer.buffer.limit(size).rewind();
        }
        return buffer;
    }

    public void reuseFreeBuffer(ByteBufferDesc buffer) {
        if (buffer == null) {
            return;
        }
        int capacity = buffer.buffer.capacity();
        int maxCount = 10;
        List<ByteBufferDesc> arrayToReuse = null;
        switch (capacity) {
            case BUFFER_SIZE_008B:
                arrayToReuse = this.freeBuffers8;
                maxCount = 80;
                break;
            case BUFFER_SIZE_128B:
                arrayToReuse = this.freeBuffers128;
                maxCount = 80;
                break;
            case BUFFER_SIZE_001K + 200:
                arrayToReuse = this.freeBuffers1024;
                break;
            case BUFFER_SIZE_004K + 200:
                arrayToReuse = this.freeBuffers4096;
                break;
            case BUFFER_SIZE_016K + 200:
                arrayToReuse = this.freeBuffers16384;
                break;
            case BUFFER_SIZE_040K:
                arrayToReuse = this.freeBuffers32768;
                break;
            case BUFFER_SIZE_160K:
                arrayToReuse = this.freeBuffersBig;
                break;
            default:
                break;
        }
        if (arrayToReuse != null) {
            synchronized(arrayToReuse) {
                if (arrayToReuse.size() < maxCount) {
                    arrayToReuse.add(buffer);
                } else {
                    Logger.w("[BuffersStorage]", "Too many " + buffer.capacity() + " buffers.");
                }
            }
        }
    }

}