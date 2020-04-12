package org.telegram.api;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL nearest dc.
 */
public class TLNearestDc extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8e1a1775;

    private String country;
    private int thisDc;
    private int nearestDc;

    public TLNearestDc() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Sets country.
     *
     * @param value the value
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets this dc.
     *
     * @return the this dc
     */
    public int getThisDc() {
        return this.thisDc;
    }

    /**
     * Sets this dc.
     *
     * @param value the value
     */
    public void setThisDc(int value) {
        this.thisDc = value;
    }

    /**
     * Gets nearest dc.
     *
     * @return the nearest dc
     */
    public int getNearestDc() {
        return this.nearestDc;
    }

    /**
     * Sets nearest dc.
     *
     * @param value the value
     */
    public void setNearestDc(int value) {
        this.nearestDc = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.country, stream);
        StreamingUtils.writeInt(this.thisDc, stream);
        StreamingUtils.writeInt(this.nearestDc, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.country = StreamingUtils.readTLString(stream);
        this.thisDc = StreamingUtils.readInt(stream);
        this.nearestDc = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "nearestDc#8e1a1775";
    }

}