package org.javagram.api.update.base;

import org.javagram.api.TLDcOption;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update dc options.
 */
public class TLUpdateDcOptions extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8e5e9873;

    private TLVector<TLDcOption> dcOptions = new TLVector<>();

    public TLUpdateDcOptions() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets dc options.
     *
     * @return the dc options
     */
    public TLVector getDcOptions() {
        return this.dcOptions;
    }

    /**
     * Sets dc options.
     *
     * @param dcOptions the dc options
     */
    public void setDcOptions(TLVector<TLDcOption> dcOptions) {
        this.dcOptions = dcOptions;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.dcOptions, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.dcOptions = StreamingUtils.readTLVector(stream, context, TLDcOption.class);
    }

    @Override
    public String toString() {
        return "updateDcOptions#8e5e9873";
    }

}