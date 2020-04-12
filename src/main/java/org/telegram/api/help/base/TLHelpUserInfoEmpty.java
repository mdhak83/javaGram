package org.telegram.api.help.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.message.base.entity.TLAbsMessageEntity;
import org.telegram.api._primitives.TLVector;

/**
 * Internal use
 * help.userInfoEmpty#f3ae2eed = help.UserInfo;
 */
public class TLHelpUserInfoEmpty extends TLAbsHelpUserInfo {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf3ae2eed;

    public TLHelpUserInfoEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "help.userInfoEmpty#f3ae2eed";
    }

}