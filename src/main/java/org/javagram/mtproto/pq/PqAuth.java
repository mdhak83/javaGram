package org.javagram.mtproto.pq;

import java.net.Socket;

public class PqAuth {

    private final byte[] authKey;
    private final long serverSalt;
    private final Socket socket;

    public PqAuth(byte[] authKey, long serverSalt, Socket socket) {
        this.authKey = authKey;
        this.serverSalt = serverSalt;
        this.socket = socket;
    }

    public byte[] getAuthKey() {
        return this.authKey;
    }

    public long getServerSalt() {
        return this.serverSalt;
    }

    public Socket getSocket() {
        return this.socket;
    }

}