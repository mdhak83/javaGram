/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.message.base.decrypted;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Geopoint media content of an encrypted message
 * @author Ruben Bermudez
 * @version 2.0
 * @since 02/MAY/2015
 */
public class TLDecryptedMessageMediaGeoPoint extends TLAbsDecryptedMessageMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x35480a59;

    private double latitude; ///< Latitude of point
    private double longtitude; ///< Longtitude of poin

    public TLDecryptedMessageMediaGeoPoint() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longtitude.
     *
     * @return the longtitude
     */
    public double getLongtitude() {
        return this.longtitude;
    }

    /**
     * Sets longtitude.
     *
     * @param longtitude the longtitude
     */
    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeDouble(this.latitude, stream);
        StreamingUtils.writeDouble(this.longtitude, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.latitude = StreamingUtils.readDouble(stream);
        this.longtitude = StreamingUtils.readDouble(stream);
    }

    @Override
    public String toString() {
        return "decryptedMessageMediaGeoPoint#35480a59";
    }

}