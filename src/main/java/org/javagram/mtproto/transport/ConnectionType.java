package org.javagram.mtproto.transport;

public class ConnectionType {

    public static final int TYPE_TCP = 0;

    private final int id;
    private final String host;
    private final int port;
    private final int connectionType;

    public ConnectionType(int id, String host, int port, int connectionType) {
        this.id = id;
        this.host = host;
        this.port = port;
        this.connectionType = connectionType;
    }

    public int getId() {
        return this.id;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public int getConnectionType() {
        return this.connectionType;
    }

}