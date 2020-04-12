package org.telegram.api.input;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Info about an @see <a href="https://core.telegram.org/mtproto/mtproto-transports#transport-obfuscation">MTProxy</a> used to connect.
 * inputClientProxy#75588b3f address:string port:int = InputClientProxy;
 */
public class TLInputClientProxy extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x75588b3f;

    /**
     * Proxy address
     */
    private String address;
    
    /**
     * Proxy port
     */
    private int port;

    public TLInputClientProxy() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.address, stream);
        StreamingUtils.writeInt(this.port, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.address = StreamingUtils.readTLString(stream);
        this.port = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "inputClientProxy#75588b3f";
    }

}