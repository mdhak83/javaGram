package org.javagram.mtproto;

public interface MTProtoCallback {

    void onSessionCreated(MTProto proto);
    
    void onAuthInvalidated(MTProto proto);
    
    void onApiMessage(byte[] message, MTProto proto);
    
    void onRpcResult(int callId, byte[] response, MTProto proto);
    
    void onRpcError(int callId, int errorCode, String message, MTProto proto);
    
    void onConfirmed(int callId);

}