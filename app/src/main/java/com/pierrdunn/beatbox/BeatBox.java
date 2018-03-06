package com.pierrdunn.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierrdunn on 06.03.18.
 */

public class BeatBox {
    private static final String TAG = "BeatBox";

    private static final String SOUND_FOLDER = "sample_sounds";

    private AssetManager mAsset;
    private List<Sound> mSounds = new ArrayList<>();

    public BeatBox(Context context){
        mAsset = context.getAssets();
        loadSound();
    }

    //Метод, который проходится по списку звуков в ресурсах
    private void loadSound(){
        String[] soundName;
        try {
            soundName = mAsset.list(SOUND_FOLDER);
            Log.i(TAG, "Found " + soundName.length + "sounds");
        } catch (IOException ioe){
            Log.e(TAG, "Could not list assets", ioe);
            return;
        }

        for (String filename : soundName) {
            String assetsPath = SOUND_FOLDER + "/" + filename;
            Sound sound = new Sound(assetsPath);
            mSounds.add(sound);
        }
    }

    public List<Sound> getSound(){
        return mSounds;
    }
}
