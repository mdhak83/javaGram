package org.javagram.api._primitives;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Basic object for RPC methods. It contains special methods for deserializing result of RPC method call.
 * @param <T> return type of method
 */
public abstract class TLMethod<T extends TLObject> extends TLObject {
    
    public T deserializeResponse(byte[] data, TLContext context) throws IOException {
        return deserializeResponse(new ByteArrayInputStream(data), context);
    }

    public T castResponse(TLObject response) {
        return (T) response;
    }

    public abstract T deserializeResponse(InputStream stream, TLContext context) throws IOException;

}