package org.telegram.api.photos.base;

import org.telegram.api.photo.base.TLAbsPhoto;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;

/**
 * The type TL abs photos.
 */
public abstract class TLAbsPhotos extends TLObject {
    /**
     * The Photos.
     */
    protected TLVector<TLAbsPhoto> photos;
    /**
     * The Users.
     */
    protected TLVector<TLAbsUser> users;

    protected TLAbsPhotos() {
        super();
    }

    /**
     * Gets photos.
     *
     * @return the photos
     */
    public TLVector<TLAbsPhoto> getPhotos() {
        return this.photos;
    }

    /**
     * Sets photos.
     *
     * @param value the value
     */
    public void setPhotos(TLVector<TLAbsPhoto> value) {
        this.photos = value;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public TLVector<TLAbsUser> getUsers() {
        return this.users;
    }

    /**
     * Sets users.
     *
     * @param value the value
     */
    public void setUsers(TLVector<TLAbsUser> value) {
        this.users = value;
    }

}