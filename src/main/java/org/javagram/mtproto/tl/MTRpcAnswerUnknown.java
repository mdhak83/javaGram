package org.javagram.mtproto.tl;

import org.javagram.api._primitives.TLObject;

/**
 * @author Ruben Bermudez
 * @version 2.0
 * @brief MTRpcAnswerUnknown
 * @date 21/02/15
 */
public class MTRpcAnswerUnknown extends TLObject {
    public static final int CLASS_ID = 0x5e2ad36e;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "MTRpcAnswerUnknown#5e2ad36e";
    }
}
