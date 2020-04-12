package org.javagram.client.structure;

import org.javagram.api._primitives.TLObject;
import org.javagram.utils.RpcException;
import org.javagram.utils.TimeoutException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Callback to execute telegram api method
 * @date 26 of September of 2015
 */
public interface TelegramFunctionCallback<T extends TLObject> {
    void onSuccess(T result);
    void onRpcError(RpcException e);
    void onTimeout(TimeoutException e);
    void onUnknownError(Throwable e);
}
