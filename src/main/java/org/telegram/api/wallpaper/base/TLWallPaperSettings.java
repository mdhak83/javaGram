package org.telegram.api.wallpaper.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLObject;

/**
 * The type TL wall paper.
 */
public class TLWallPaperSettings extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5086cf8;

    private static final int FLAG_BACKGROUND_COLOR          = 0x00000001; // 0 : If set, a PNG pattern is to be combined with the color chosen by the user: the main color of the background in RGB24 format
    private static final int FLAG_BLUR                      = 0x00000002; // 1 : If set, the wallpaper must be downscaled to fit in 450x450 square and then box-blurred with radius 12
    private static final int FLAG_MOTION                    = 0x00000004; // 2 : If set, the background needs to be slightly moved when device is rotated
    private static final int FLAG_INTENSITY                 = 0x00000008; // 3 : Intensity of the pattern when it is shown above the main background color, 0-100
    private static final int FLAG_SECOND_BACKGROUND_COLOR   = 0x00000010; // 4 : If set, a PNG pattern is to be combined with the first and second background colors (RGB24 format) in a top-bottom gradient
    private static final int FLAG_ROTATION                  = 0x00000010; // 4 : Clockwise rotation angle of the gradient, in degrees; 0-359. Should be always divisible by 45

    /**
     * If set, a PNG pattern is to be combined with the color chosen by the user: the main color of the background in RGB24 format
     */
    private int backgroundColor;

    /**
     * If set, a PNG pattern is to be combined with the first and second background colors (RGB24 format) in a top-bottom gradient
     */
    private int secondBackgroundColor;

    /**
     * Intensity of the pattern when it is shown above the main background color, 0-100
     */
    private int intensity;

    /**
     * Clockwise rotation angle of the gradient, in degrees; 0-359. Should be always divisible by 45
     */
    private int rotation;

    /**
     * Wallpaper settings
     */
    private TLWallPaperSettings settings;

    public TLWallPaperSettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isBlur() {
        return this.isFlagSet(FLAG_BLUR);
    }

    public boolean isMotion() {
        return this.isFlagSet(FLAG_MOTION);
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getSecondBackgroundColor() {
        return secondBackgroundColor;
    }

    public void setSecondBackgroundColor(int secondBackgroundColor) {
        this.secondBackgroundColor = secondBackgroundColor;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public TLWallPaperSettings getSettings() {
        return settings;
    }

    public void setSettings(TLWallPaperSettings settings) {
        this.settings = settings;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if ((this.flags & FLAG_BACKGROUND_COLOR) != 0) {
            StreamingUtils.writeInt(this.backgroundColor, stream);
        }
        if ((this.flags & FLAG_SECOND_BACKGROUND_COLOR) != 0) {
            StreamingUtils.writeInt(this.secondBackgroundColor, stream);
        }
        if ((this.flags & FLAG_INTENSITY) != 0) {
            StreamingUtils.writeInt(this.intensity, stream);
        }
        if ((this.flags & FLAG_ROTATION) != 0) {
            StreamingUtils.writeInt(this.rotation, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_BACKGROUND_COLOR) != 0) {
            this.backgroundColor = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_SECOND_BACKGROUND_COLOR) != 0) {
            this.secondBackgroundColor = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_INTENSITY) != 0) {
            this.intensity = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_ROTATION) != 0) {
            this.rotation = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "wallPaperSettings#5086cf8";
    }

}