package org.javagram.mtproto.transport;

enum TcpConnectionState {
    TcpConnectionStageSuspended,
    TcpConnectionStageIdle,
    TcpConnectionStageConnecting,
    TcpConnectionStageReconnecting,
    TcpConnectionStageConnected
}
