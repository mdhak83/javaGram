package org.javagram.api.channel.base.input;

/**
 * Represents the absence of a channel
 * inputChannelEmpty#ee8c1e86 = InputChannel;
 */
public class TLInputChannelEmpty extends TLAbsInputChannel {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xee8c1e86;

    public TLInputChannelEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputChannelEmpty#ee8c1e86";
    }

    @Override
    public String toLog() {
        return "InputChannelEmpty";
    }
    
}