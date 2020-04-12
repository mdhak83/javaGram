package org.javagram.api.user.base.profile.photo;

/**
 * The type TL user profile photo empty.
 */
public class TLUserProfilePhotoEmpty extends TLAbsUserProfilePhoto {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4f11bae1;

    public TLUserProfilePhotoEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "userProfilePhotoEmpty#4f11bae1";
    }

}