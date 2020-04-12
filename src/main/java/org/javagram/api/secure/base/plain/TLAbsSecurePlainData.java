package org.javagram.api.secure.base.plain;

import org.javagram.api._primitives.TLObject;

/**
 * The SecurePlainData type
 * Plaintext verified @see <a href="https://core.telegram.org/passport/encryption#secureplaindata">passport</a> data.
 */
public abstract class TLAbsSecurePlainData extends TLObject {

    protected TLAbsSecurePlainData() {
        super();
    }

}
