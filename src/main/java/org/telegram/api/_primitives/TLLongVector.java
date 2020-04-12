package org.telegram.api._primitives;

/**
 * TL Vector of longs. @see org.telegram.api._primitives.TLVector
 *
 * @author Ruben Bermudez
 */
public class TLLongVector extends TLVector<Long> {
    public TLLongVector() {
        setDestClass(Long.class);
    }

    @Override
    public String toString() {
        return "vector<long>#1cb5c415";
    }
}
