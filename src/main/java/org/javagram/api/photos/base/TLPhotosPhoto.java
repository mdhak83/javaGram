/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.javagram.api.photos.base;

import org.javagram.api.photo.base.TLAbsPhoto;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL photos photo.
 * @author Ruben Bermudez
 * @version 2.0
 * TLPhotosPhoto
 ** @since 13/NOV/2014
 */
public class TLPhotosPhoto extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x20212ca8;

    /**
     * The Photo.
     */
    protected TLAbsPhoto photo;
    /**
     * The Users.
     */
    protected TLVector<TLAbsUser> users;

    public TLPhotosPhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.photo, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.photo = StreamingUtils.readTLObject(stream, context, TLAbsPhoto.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "photos.photos#20212ca8";
    }

}