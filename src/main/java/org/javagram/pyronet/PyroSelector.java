package org.javagram.pyronet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class PyroSelector {

    public static final int BUFFER_SIZE = 16 * 1024;
    private Thread networkThread;
    private final Selector nioSelector;
    protected final ByteBuffer networkBuffer;
    private BlockingQueue<Runnable> tasks = new LinkedBlockingDeque<>();

    public PyroSelector() {
        this.networkBuffer = this.malloc(BUFFER_SIZE);
        try {
            this.nioSelector = Selector.open();
        } catch (IOException exc) {
            throw new PyroException("Failed to open a selector?!", exc);
        }
        this.networkThread = Thread.currentThread();
    }

    public ByteBuffer malloc(int size) {
        return ByteBuffer.allocate(size);
    }

    public final boolean isNetworkThread() {
        return this.networkThread == Thread.currentThread();
    }

    public final Thread networkThread() {
        return this.networkThread;
    }

    public final void checkThread() {
        if (!this.isNetworkThread()) {
            throw new PyroException("call from outside the network-thread, you must schedule tasks");
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
            } catch (SocketTimeoutException ex) {
                client.onConnectionError(ex);
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

        new Thread(new Runnable() {
            @Override
            public void run() {
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
                    while (true) {
                        PyroSelector.this.select();
                    }
                } catch (Exception ex) {
                    // this never be caused by Pyro-code
                    throw new IllegalStateException(ex);
                }
            }
        }, name).start();
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
        this.networkThread.interrupt();
        this.nioSelector.close();
        this.networkBuffer.clear();
    }    

    final SelectionKey register(SelectableChannel channel, int ops) throws IOException {
        return channel.register(this.nioSelector, ops);
    }

    final boolean adjustInterestOp(SelectionKey key, int op, boolean state) {
        this.checkThread();
        try {
            int ops = key.interestOps();
            boolean changed = state != ((ops & op) == op);
            if (changed) {
                key.interestOps(state ? (ops | op) : (ops & ~op));
            }
            return changed;
        } catch (CancelledKeyException exc) {
            // ignore
            return false;
        }
    }

}