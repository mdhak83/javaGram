package org.telegram.client.structure;

import org.telegram.api._primitives.TLObject;
import org.telegram.utils.RpcException;
import org.telegram.utils.TimeoutException;

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
