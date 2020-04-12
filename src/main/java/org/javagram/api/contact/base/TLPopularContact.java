package org.javagram.api.contact.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Popular contact
 * popularContact#5ce14175 client_id:long importers:int = PopularContact;
 */
public class TLPopularContact extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5ce14175;

    /**
     * Contact identifier
     */
    private long clientId;
    
    /**
     * How many people imported this contact
     */
    private int importers;

    public TLPopularContact() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public int getImporters() {
        return importers;
    }

    public void setImporters(int importers) {
        this.importers = importers;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.clientId, stream);
        StreamingUtils.writeInt(this.importers, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.clientId = StreamingUtils.readLong(stream);
        this.importers = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "popularContact#5ce14175";
    }

}