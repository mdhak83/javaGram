package org.telegram.api;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Autodownload settings
 * autoDownloadSettings#e04232f3 flags:# disabled:flags.0?true video_preload_large:flags.1?true audio_preload_next:flags.2?true phonecalls_less_data:flags.3?true photo_size_max:int video_size_max:int file_size_max:int video_upload_maxbitrate:int = AutoDownloadSettings;
 */
public class TLAutoDownloadSettings extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe04232f3;

    private static final int FLAG_DISABLED              = 0x00000001; // 0 : Disable automatic media downloads?
    private static final int FLAG_VIDEO_PRELOAD_LARGE   = 0x00000002; // 1 : Whether to preload the first seconds of videos larger than the specified limit
    private static final int FLAG_AUDIO_PRELOAD_NEXT    = 0x00000004; // 2 : Whether to preload the next audio track when you're listening to music
    private static final int FLAG_PHONECALLS_LESS_DATA  = 0x00000008; // 3 : Whether to enable data saving mode in phone calls

    /**
     * Maximum size of photos to preload
     */
    private int photoSizeMax;
    
    /**
     * Maximum size of videos to preload
     */
    private int videoSizeMax;
    
    /**
     * Maximum size of other files to preload
     */
    private int fileSizeMax;
    
    /**
     * Maximum suggested bitrate for <strong>uploading</strong> videos
     */
    private int videoUploadMaxbitrate;
    
    public TLAutoDownloadSettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isDisabled() {
        return this.isFlagSet(FLAG_DISABLED);
    }
    
    public void setDisabled(boolean value) {
        this.setFlag(FLAG_DISABLED, value);
    }
    
    public boolean isPreloadLargeVideos() {
        return this.isFlagSet(FLAG_VIDEO_PRELOAD_LARGE);
    }
    
    public void setPreloadLargeVideos(boolean value) {
        this.setFlag(FLAG_VIDEO_PRELOAD_LARGE, value);
    }
    
    public boolean isAudioPreloadNext() {
        return this.isFlagSet(FLAG_AUDIO_PRELOAD_NEXT);
    }
    
    public void setAudioPreloadNext(boolean value) {
        this.setFlag(FLAG_AUDIO_PRELOAD_NEXT, value);
    }
    
    public boolean isPhonecallsLessData() {
        return this.isFlagSet(FLAG_PHONECALLS_LESS_DATA);
    }
    
    public void setPhonecallsLessData(boolean value) {
        this.setFlag(FLAG_PHONECALLS_LESS_DATA, value);
    }
    
    public int getPhotoSizeMax() {
        return photoSizeMax;
    }

    public void setPhotoSizeMax(int photoSizeMax) {
        this.photoSizeMax = photoSizeMax;
    }

    public int getVideoSizeMax() {
        return videoSizeMax;
    }

    public void setVideoSizeMax(int videoSizeMax) {
        this.videoSizeMax = videoSizeMax;
    }

    public int getFileSizeMax() {
        return fileSizeMax;
    }

    public void setFileSizeMax(int fileSizeMax) {
        this.fileSizeMax = fileSizeMax;
    }

    public int getVideoUploadMaxbitrate() {
        return videoUploadMaxbitrate;
    }

    public void setVideoUploadMaxbitrate(int videoUploadMaxbitrate) {
        this.videoUploadMaxbitrate = videoUploadMaxbitrate;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.photoSizeMax, stream);
        StreamingUtils.writeInt(this.videoSizeMax, stream);
        StreamingUtils.writeInt(this.fileSizeMax, stream);
        StreamingUtils.writeInt(this.videoUploadMaxbitrate, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.photoSizeMax = StreamingUtils.readInt(stream);
        this.videoSizeMax = StreamingUtils.readInt(stream);
        this.fileSizeMax = StreamingUtils.readInt(stream);
        this.videoUploadMaxbitrate = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "autoDownloadSettings#e04232f3";
    }

}