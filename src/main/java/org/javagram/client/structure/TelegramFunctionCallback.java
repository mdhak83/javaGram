package org.javagram.client.structure;

import org.javagram.api._primitives.TLObject;
import org.javagram.utils.RpcException;
import org.javagram.utils.TimeoutException;

public interface TelegramFunctionCallback<T extends TLObject> {

    void onSuccess(T result);
    void onRpcError(RpcException e);
    void onTimeout(TimeoutException e);
    void onUnknownError(Throwable e);

}
