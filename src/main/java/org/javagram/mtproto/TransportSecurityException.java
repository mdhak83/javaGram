package org.javagram.mtproto;

import java.io.IOException;

public class TransportSecurityException extends IOException {

    public TransportSecurityException() {
    }

    public TransportSecurityException(String s) {
        super(s);
    }

    public TransportSecurityException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public TransportSecurityException(Throwable throwable) {
        super(throwable);
    }

}