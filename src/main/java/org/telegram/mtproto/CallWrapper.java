package org.telegram.mtproto;

import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 07.11.13
 * Time: 3:56
 */
public interface CallWrapper {
    TLObject wrapObject(TLMethod srcRequest);
}
