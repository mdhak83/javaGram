package org.telegram.api.geo.base.point.input;

/**
 * The type TL input geo point empty.
 */
public class TLInputGeoPointEmpty extends TLAbsInputGeoPoint {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe4c123d6;

    public TLInputGeoPointEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputGeoPointEmpty#e4c123d6";
    }

}