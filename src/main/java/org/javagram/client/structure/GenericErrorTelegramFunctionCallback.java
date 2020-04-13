package org.javagram.client.structure;

import org.javagram.api._primitives.TLObject;
import org.javagram.utils.RpcException;
import org.javagram.utils.TimeoutException;

public abstract class GenericErrorTelegramFunctionCallback<T extends TLObject> implements TelegramFunctionCallback<T> {

    @Override
    public void onRpcError(RpcException e) {
        this.onError(e);
    }

    @Override
    public void onTimeout(TimeoutException e) {
        this.onError(e);
    }

    @Override
    public void onUnknownError(Throwable e) {
        this.onError(e);
    }

    public abstract void onError(Throwable e);

}