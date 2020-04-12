package org.telegram.api.file.base.input.location;

/**
 * Empty constructor for takeout
 * inputTakeoutFileLocation#29be5899 = InputFileLocation;
 */
public class TLInputTakeoutFileLocation extends TLAbsInputFileLocation {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x29be5899;

    public TLInputTakeoutFileLocation() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputTakeoutFileLocation#29be5899";
    }

}