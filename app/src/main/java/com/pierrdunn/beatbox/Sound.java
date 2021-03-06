package com.pierrdunn.beatbox;


/**
 * Created by pierrdunn on 06.03.18.
 */

public class Sound {
    private String mAssetsPath;
    private String mName;
    private Integer mSoundId;

    public Sound(String assetsPath){
        mAssetsPath = assetsPath;
        String[] components = assetsPath.split("/");
        String filename = components[components.length - 1];
        mName = filename.replace(".mp3", "");
    }

    public String getAssetsPath() {
        return mAssetsPath;
    }

    public String getName() {
        return mName;
    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }
}
