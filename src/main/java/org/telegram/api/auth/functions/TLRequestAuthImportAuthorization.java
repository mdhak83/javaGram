package org.telegram.api.auth.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.auth.base.authorization.TLAbsAuthAuthorization;

/**
 * Logs in a user using a key transmitted from his native data-centre.
 * auth.importAuthorization#e3ef9613 id:int bytes:bytes = auth.Authorization;
 */
public class TLRequestAuthImportAuthorization extends TLMethod<TLAbsAuthAuthorization> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe3ef9613;

    /**
     * User ID
     */
    private int id;
    
    /**
     * Authorization key
     */
    private TLBytes bytes;

    public TLRequestAuthImportAuthorization() {
        super();
    }

    public TLRequestAuthImportAuthorization(int id, TLBytes bytes) {
        super();
        this.setId(id);
        this.setBytes(bytes);
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int value) {
        this.id = value;
    }

    public TLBytes getBytes() {
        return this.bytes;
    }

    public void setBytes(TLBytes value) {
        this.bytes = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeTLBytes(this.bytes, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readInt(stream);
        this.bytes = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public TLAbsAuthAuthorization deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsAuthAuthorization) {
            return (TLAbsAuthAuthorization) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.auth.TLAuthorization, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "auth.importAuthorization#e3ef9613";
    }
    
}