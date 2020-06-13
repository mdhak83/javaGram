/*
 * This is the source code of tgnet library v. 1.1
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov
 * Update 02/JAN/2020
 * https://github.com/DrKLO/Telegram/blob/master/TMessagesProj/jni/tgnet/Defines.h
 */

package org.javagram.mtproto.transport;

public final class TcpAddress {
    
    public enum TcpAddressFlag {
        TcpAddressFlagIpv6      (1),
        TcpAddressFlagDownload  (2),
        TcpAddressFlagO         (4),
        TcpAddressFlagCdn       (8),
        TcpAddressFlagStatic    (16),
        TcpAddressFlagTemp      (2048);
        
        private final int value;
        
        TcpAddressFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }
    
    private final String address;
    private final int flags;
    private final int port;
    private final byte[] secret;
    
    public TcpAddress(String address, int port, int flags, byte[] secret) {
        this.address = address;
        this.port = port;
        this.flags = flags;
        this.secret = secret;
    }

    public String getAddress() {
        return address;
    }

    public int getFlags() {
        return flags;
    }

    public int getPort() {
        return port;
    }

    public byte[] getSecret() {
        return secret;
    }

}