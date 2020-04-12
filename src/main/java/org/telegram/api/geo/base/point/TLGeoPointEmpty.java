package org.telegram.api.geo.base.point;

/**
 * The type TL geo point empty.
 */
public class TLGeoPointEmpty extends TLAbsGeoPoint {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1117dd5f;

    public TLGeoPointEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "geoPointEmpty#1117dd5f";
    }

}