package org.javagram.mtproto;

import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;

public interface CallWrapper {

    TLObject wrapObject(TLMethod srcRequest);

}