package org.telegram.api.auth.functions;

import org.telegram.api.auth.base.authorization.TLExportedAuthorization;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Returns data for copying authorization to another data-centre.
 * auth.exportAuthorization#e5bfffcd dc_id:int = auth.ExportedAuthorization;
 */
public class TLRequestAuthExportAuthorization extends TLMethod<TLExportedAuthorization> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe5bfffcd;

    /**
     * Number of a target data-centre
     */
    private int dcId;

    public TLRequestAuthExportAuthorization() {
        super();
    }

    public TLRequestAuthExportAuthorization(int dcId) {
        super();
        this.setDcId(dcId);
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getDcId() {
        return this.dcId;
    }

    public void setDcId(int dcId) {
        this.dcId = dcId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.dcId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.dcId = StreamingUtils.readInt(stream);
    }

    @Override
    public TLExportedAuthorization deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLExportedAuthorization) {
            return (TLExportedAuthorization) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.auth.TLExportedAuthorization, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "auth.exportAuthorization#e5bfffcd";
    }

}