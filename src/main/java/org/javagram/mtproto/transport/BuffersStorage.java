/*
 * This is the source code of Telegram for Android v. 1.3.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2014.
 */

package org.javagram.mtproto.transport;

import org.javagram.mtproto.log.Logger;

import java.util.ArrayList;

public class BuffersStorage {

    private final ArrayList<ByteBufferDesc> freeBuffers128;
    private final ArrayList<ByteBufferDesc> freeBuffers1024;
    private final ArrayList<ByteBufferDesc> freeBuffers4096;
    private final ArrayList<ByteBufferDesc> freeBuffers16384;
    private final ArrayList<ByteBufferDesc> freeBuffers32768;
    private final ArrayList<ByteBufferDesc> freeBuffersBig;
    private final boolean isThreadSafe;
    private final static Object LOCK = new Object();

    private static volatile BuffersStorage Instance = null;
    
    public static BuffersStorage getInstance() {
        BuffersStorage localInstance = Instance;
        if (localInstance == null) {
            synchronized(LOCK) {
                localInstance = Instance;
                if (localInstance == null) {
                    Instance = localInstance = new BuffersStorage(true);
                }
            }
        }
        return localInstance;
    }

    public BuffersStorage(boolean threadSafe) {
        this.isThreadSafe = threadSafe;
        this.freeBuffers128 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.freeBuffers128.add(new ByteBufferDesc(328));
        }
        this.freeBuffers1024 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.freeBuffers1024.add(new ByteBufferDesc(1224));
        }
        this.freeBuffers4096 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.freeBuffers4096.add(new ByteBufferDesc(4296));
        }
        this.freeBuffers16384 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.freeBuffers16384.add(new ByteBufferDesc(16584));
        }
        this.freeBuffers32768 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.freeBuffers32768.add(new ByteBufferDesc(32968));
        }
        this.freeBuffersBig = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.freeBuffersBig.add(new ByteBufferDesc(262344));
        }
    }

    public ByteBufferDesc getFreeBuffer(int size) {
        if (size <= 0) {
            return null;
        }
        int byteCount = 0;
        ArrayList<ByteBufferDesc> arrayToGetFrom = null;
        ByteBufferDesc buffer = null;
        if (size <= 128) {
            arrayToGetFrom = this.freeBuffers128;
            byteCount = 128;
        } else if (size <= 1024 + 200) {
            arrayToGetFrom = this.freeBuffers1024;
            byteCount = 1224 + 200;
        } else if (size <= 4096 + 200) {
            arrayToGetFrom = this.freeBuffers4096;
            byteCount = 4296 + 200;
        } else if (size <= 16384 + 200) {
            arrayToGetFrom = this.freeBuffers16384;
            byteCount = 16584 + 200;
        } else if (size <= 32768 + 200) {
            arrayToGetFrom = this.freeBuffers32768;
            byteCount = 32968;
        } else if (size <= 262144 + 200) {
            arrayToGetFrom = this.freeBuffersBig;
            byteCount = 262344;
        } else {
            buffer = new ByteBufferDesc(size);
        }

        if (arrayToGetFrom != null) {
            if (this.isThreadSafe) {
                synchronized (LOCK) {
                    if (arrayToGetFrom.size() > 0) {
                        buffer = arrayToGetFrom.get(0);
                        arrayToGetFrom.remove(0);
                    }
                }
            } else {
                if (arrayToGetFrom.size() > 0) {
                    buffer = arrayToGetFrom.get(0);
                    arrayToGetFrom.remove(0);
                }
            }

            if (buffer == null) {
                buffer = new ByteBufferDesc(byteCount);
                Logger.d("tmessages", "create new " + byteCount + " buffer");
            }
        }

        buffer.buffer.limit(size).rewind();
        return buffer;
    }

    public void reuseFreeBuffer(ByteBufferDesc buffer) {
        if (buffer == null) {
            return;
        }
        int maxCount = 10;
        ArrayList<ByteBufferDesc> arrayToReuse = null;
        switch (buffer.buffer.capacity()) {
            case 128:
                arrayToReuse = this.freeBuffers128;
                break;
            case 1024 + 200:
                arrayToReuse = this.freeBuffers1024;
                break;
            case 4096 + 200:
                arrayToReuse = this.freeBuffers4096;
                break;
            case 16384 + 200:
                arrayToReuse = this.freeBuffers16384;
                break;
            case 32768 + 200:
                arrayToReuse = this.freeBuffers32768;
                break;
            case 262144 + 200:
                arrayToReuse = this.freeBuffersBig;
                maxCount = 10;
                break;
            default:
                break;
        }
        if (arrayToReuse != null) {
            if (this.isThreadSafe) {
                synchronized (LOCK) {
                    if (arrayToReuse.size() < maxCount) {
                        arrayToReuse.add(buffer);
                    } else {
                        Logger.w("tmessages", "too more");
                    }
                }
            } else {
                if (arrayToReuse.size() < maxCount) {
                    arrayToReuse.add(buffer);
                }
            }
        }
    }

}