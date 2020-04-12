package org.telegram.api._primitives;

/**
 * TL Vector of strings. @see org.telegram.api._primitives.TLVector
 *
 * @author Ruben Bermudez
 */
public class TLStringVector extends TLVector<String> {
    public TLStringVector() {
        setDestClass(String.class);
    }

    @Override
    public String toString() {
        return "vector<string>#1cb5c415";
    }
}
