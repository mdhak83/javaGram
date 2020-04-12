package org.javagram.utils;

import org.javagram.api._primitives.TLObject;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 09.11.13
 * Time: 18:06
 * @param <T>  the type parameter
 */
public interface RpcCallbackEx<T extends TLObject> extends RpcCallback<T> {
    /**
     * On confirmed.
     */
    void onConfirmed();
}
