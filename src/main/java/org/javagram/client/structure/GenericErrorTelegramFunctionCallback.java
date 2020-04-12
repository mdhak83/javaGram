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
public abstract class GenericErrorTelegramFunctionCallback<T extends TLObject> implements TelegramFunctionCallback<T> {
    @Override
    public void onRpcError(RpcException e) {
        onError(e);
    }

    @Override
    public void onTimeout(TimeoutException e) {
        onError(e);
    }

    @Override
    public void onUnknownError(Throwable e) {
        onError(e);
    }

    public abstract void onError(Throwable e);
}
