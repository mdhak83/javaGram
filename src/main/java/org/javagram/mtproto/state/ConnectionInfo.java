package org.javagram.mtproto.state;

public class ConnectionInfo {
    
    /**
     * id=0 for MemoryProtoState connection ; 
     */
    private final int id;
    private final int priority;
    private final String address;
    private final int port;

    public ConnectionInfo(int id, int priority, String address, int port) {
        this.id = id;
        this.priority = priority;
        this.address = address;
        this.port = port;
    }

    public int getPriority() {
        return this.priority;
    }

    public String getAddress() {
        return this.address;
    }

    public int getPort() {
        return this.port;
    }

    public int getId() {
        return this.id;
    }

}