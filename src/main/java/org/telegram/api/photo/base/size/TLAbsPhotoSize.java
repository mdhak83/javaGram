package org.telegram.api.photo.base.size;

import org.telegram.api._primitives.TLObject;

/**
 * The PhotoSize type.
 * Location of a certain size of a picture
 */
public abstract class TLAbsPhotoSize extends TLObject {

    /**
     * Thumbnail type
     * Type / Image filter / Size
     * <code>s</code> /  box /  100 *  100
     * <code>m</code> /  box /  320 *  320
     * <code>x</code> /  box /  800 *  800
     * <code>y</code> /  box / 1280 * 1280
     * <code>w</code> /  box / 2560 * 2560
     * <code>a</code> / crop /  160 *  160
     * <code>b</code> / crop /  320 *  320
     * <code>c</code> / crop /  640 *  640
     * <code>d</code> / crop / 1280 * 1280
     */
    protected String type;
    
    protected TLAbsPhotoSize() {
        super();
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

}