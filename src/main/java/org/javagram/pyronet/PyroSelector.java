package org.javagram.pyronet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class PyroSelector {

    private final static int DEFAULT_BUFFER_SIZE = 256 * 1024;
    private final Selector nioSelector;
    private final boolean DoNotCheckNetworkThread;
    private Thread networkThread;
    private boolean closeNetworkThread = false;
    protected final int bufferSize;
    protected final ByteBuffer networkBuffer;
    private BlockingQueue<Runnable> tasks = new LinkedBlockingDeque<>();
    
    public PyroSelector() {
        this(DEFAULT_BUFFER_SIZE, false);
    }
    
    public PyroSelector(int bufferSize, boolean doNotCheckNetworkThread) {
        this.DoNotCheckNetworkThread = doNotCheckNetworkThread;
        this.bufferSize = bufferSize;
        this.networkBuffer = ByteBuffer.allocateDirect(bufferSize);
        try {
            this.nioSelector = Selector.open();
        } catch (IOException ex) {
            throw new PyroException("Failed to open a selector?!", ex);
        }
        this.networkThread = Thread.currentThread();
    }

    final boolean isNetworkThread() {
        return DoNotCheckNetworkThread || (this.networkThread == Thread.currentThread());
    }

    public final Thread networkThread() {
        return this.networkThread;
    }

    public final void checkThread() {
        if (!DoNotCheckNetworkThread) {
            if (!this.isNetworkThread()) {
                throw new PyroException("call from outside the network-thread, you must schedule tasks");
            }
        }
    }

    public PyroClient connect(InetSocketAddress host) throws IOException {
        return this.connect(host, null);
    }

    public PyroClient connect(InetSocketAddress host, InetSocketAddress bind) throws IOException {
        return new PyroClient(this, bind, host);
    }

    public void select() {
        this.select(0);
    }

    public void select(long eventTimeout) {
        this.checkThread();
        this.executePendingTasks();
        this.performNioSelect(eventTimeout);
        final long now = System.currentTimeMillis();
        this.handleSelectedKeys(now);
        this.handleSocketTimeouts(now);
    }

    private void executePendingTasks() {
        Runnable task;
        while ((task = this.tasks.poll()) != null) {
            try {
                task.run();
            } catch (Throwable cause) {
                cause.printStackTrace();
            }
        }
    }

    private void performNioSelect(long timeout) {
        try {
            this.nioSelector.select(timeout);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void handleSelectedKeys(long now) {
        Iterator<SelectionKey> keys = this.nioSelector.selectedKeys().iterator();
        while (keys.hasNext()) {
            SelectionKey key = keys.next();
            keys.remove();
            if (key.channel() instanceof SocketChannel) {
                PyroClient client = (PyroClient) key.attachment();
                client.onInterestOp(now);
            }
        }
    }

    private void handleSocketTimeouts(long now) {
        this.nioSelector.keys().stream().filter((key) -> (key.channel() instanceof SocketChannel)).map((key) -> (PyroClient) key.attachment()).filter((client) -> (client.didTimeout(now))).forEachOrdered((client) -> {
            try {
                throw new SocketTimeoutException("PyroNet detected NIO timeout");
            } catch (SocketTimeoutException exc) {
                client.onConnectionError(exc);
            }
        });
    }

    public void spawnNetworkThread(final String name) {
        // now no thread can access this selector
        //
        // N.B.
        // -- updating this non-volatile field is thread-safe
        // -- because the current thread can see it (causing it
        // -- to become UNACCESSIBLE), and all other threads
        // -- that might not see the change will
        // -- (continue to) block access to this selector
        this.networkThread = null;

        Thread t = new Thread(() -> {
            // spawned thread can access this selector
            //
            // N.B.
            // -- updating this non-volatile field is thread-safe
            // -- because the current thread can see it (causing it
            // -- to become ACCESSIBLE), and all other threads
            // -- that might not see the change will
            // -- (continue to) block access to this selector
            PyroSelector.this.networkThread = Thread.currentThread();
            
            // start select-loop
            try {
                while (!PyroSelector.this.closeNetworkThread) {
                    PyroSelector.this.select();
                }
            } catch (Exception ex) {
                throw new IllegalStateException(ex);
            }
        }, name);
        t.start();
    }

    public void scheduleTask(Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }
        try {
            this.tasks.put(task);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.wakeup();
    }

    public void wakeup() {
        this.nioSelector.wakeup();
    }

    public void close() throws IOException {
        if (this.networkThread != null) {
            this.closeNetworkThread = true;
            try {
                this.networkThread.join();
            } catch (InterruptedException ex) {
            }
        }
        this.nioSelector.close();
        this.networkBuffer.clear();
    }

    protected final SelectionKey register(SelectableChannel channel, int ops) throws IOException {
        return channel.register(this.nioSelector, ops);
    }

    protected final boolean adjustInterestOp(SelectionKey key, int op, boolean state) {
        this.checkThread();
        try {
            int ops = key.interestOps();
            boolean changed = state != ((ops & op) == op);
            if (changed)
                key.interestOps(state ? (ops | op) : (ops & ~op));
            return changed;
        } catch (CancelledKeyException exc) {
            // ignore
            return false;
        }
    }

}