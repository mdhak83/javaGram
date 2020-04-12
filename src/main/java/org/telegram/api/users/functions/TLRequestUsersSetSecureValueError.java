package org.telegram.api.users.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBool;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.secure.base.valueerror.TLAbsSecureValueError;
import org.telegram.api.user.base.input.TLAbsInputUser;
import org.telegram.api._primitives.TLVector;

/**
 * Notify the user that the sent @see <a href="https://core.telegram.org/passport">passport</a> data contains some errors The user will not be able to re-submit their Passport data to you until the errors are fixed (the contents of the field for which you returned the error must change).
 * Use this if the data submitted by the user doesn't satisfy the standards your service requires for any reason.
 * For example, if a birthday date seems invalid, a submitted document is blurry, a scan shows evidence of tampering, etc. Supply some details in the error message to make sure the user knows how to correct the issues.
 * users.setSecureValueErrors#90c894b5 id:InputUser errors:Vector&lt;SecureValueError&gt; = Bool;
 */
public class TLRequestUsersSetSecureValueError extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x90c894b5;

    /**
     * The user
     */
    private TLAbsInputUser id;
    
    /**
     * Errors
     */
    private TLVector<TLAbsSecureValueError> errors;
    
    public TLRequestUsersSetSecureValueError() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputUser getId() {
        return id;
    }

    public void setId(TLAbsInputUser id) {
        this.id = id;
    }

    public TLVector<TLAbsSecureValueError> getErrors() {
        return errors;
    }

    public void setErrors(TLVector<TLAbsSecureValueError> errors) {
        this.errors = errors;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.id, stream);
        StreamingUtils.writeTLVector(this.errors, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
        this.errors = StreamingUtils.readTLVector(stream, context, TLAbsSecureValueError.class);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "users.setSecureValueErrors#90c894b5";
    }

}