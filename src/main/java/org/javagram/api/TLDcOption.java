package org.javagram.api;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;

/**
 * dcOption
 * Data centre
 * dcOption#18b7a10d flags:# ipv6:flags.0?true media_only:flags.1?true tcpo_only:flags.2?true cdn:flags.3?true static:flags.4?true id:int ip_address:string port:int secret:flags.10?bytes = DcOption;
 */
public class TLDcOption extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x18b7a10d;

    private static final int FLAG_IPV6          = 0x00000001; // 0  : Whether the specified IP is an IPv6 address
    private static final int FLAG_MEDIA_ONLY    = 0x00000002; // 1  : Whether this DC should only be used to @see <a href="https://core.telegram.org/api/files">download or upload files</a>
    private static final int FLAG_TCPO_ONLY     = 0x00000004; // 2  : Whether this DC only supports connection with @see <a href="https://core.telegram.org/mtproto/mtproto-transports#transport-obfuscation">transport obfuscation</a>
    private static final int FLAG_CDN           = 0x00000008; // 3  : Whether this is a @see <a href="https://core.telegram.org/cdn">CDN DC</a>.
    private static final int FLAG_STATIC        = 0x00000010; // 4  : If set, this IP should be used when connecting through a proxy
    private static final int FLAG_SECRET        = 0x00000400; // 10 : If the tcpo_only flag is set, specifies the secret to use when connecting using @see <a href="https://core.telegram.org/mtproto/mtproto-transports#transport-obfuscation">transport obfuscation</a>

    /**
     * DC ID
     */
    private int id;
    
    /**
     * IP address of DC
     */
    private String ipAddress;
    
    /**
     * Port
     */
    private int port;
    
    /**
     * If the tcpo_only flag is set, specifies the secret to use when connecting using @see <a href="https://core.telegram.org/mtproto/mtproto-transports#transport-obfuscation">transport obfuscation</a>
     */
    private TLBytes secret;

    public TLDcOption() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isIPV6() {
        return this.isFlagSet(FLAG_IPV6);
    }

    public void setIPV6(boolean value) {
        this.setFlag(FLAG_IPV6, value);
    }

    public boolean isMediaOnly() {
        return this.isFlagSet(FLAG_MEDIA_ONLY);
    }

    public void setMediaOnly(boolean value) {
        this.setFlag(FLAG_MEDIA_ONLY, value);
    }

    public boolean isTcpoOnly() {
        return this.isFlagSet(FLAG_TCPO_ONLY);
    }

    public void setTcpoOnly(boolean value) {
        this.setFlag(FLAG_TCPO_ONLY, value);
    }

    public boolean isCdn() {
        return this.isFlagSet(FLAG_CDN);
    }

    public void setCdn(boolean value) {
        this.setFlag(FLAG_CDN, value);
    }

    public boolean isStatic() {
        return this.isFlagSet(FLAG_STATIC);
    }

    public void setStatic(boolean value) {
        this.setFlag(FLAG_STATIC, value);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean hasSecret() {
        return this.isFlagSet(FLAG_SECRET);
    }

    public TLBytes getSecret() {
        return secret;
    }

    public void setSecret(TLBytes secret) {
        this.secret = secret;
        this.setFlag(FLAG_STATIC, secret != null);
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeTLString(this.ipAddress, stream);
        StreamingUtils.writeInt(this.port, stream);
        if (this.hasSecret()) {
            StreamingUtils.writeTLBytes(this.secret, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        this.ipAddress = StreamingUtils.readTLString(stream);
        this.port = StreamingUtils.readInt(stream);
        if (this.hasSecret()) {
            this.secret = StreamingUtils.readTLBytes(stream, context);
        }
    }

    @Override
    public String toString() {
        return "dcOption#18b7a10d";
    }

}