package org.javagram.mtproto.transport;

import org.javagram.mtproto.tl.MTMessage;

public class NetworkMessage {
    
    public MTMessage message;
    public boolean invokeAfter = false;
    public boolean needQuickAck = false;
    public int requestId;

}
