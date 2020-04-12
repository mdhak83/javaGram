package org.telegram.api.json.base;

/**
 * null JSON value
 * jsonNull#3f6d7b68 = JSONValue;
 */
public class TLJsonNull extends TLAbsJSONValue {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3f6d7b68;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "jsonNull#3f6d7b68";
    }
    
}