package com.pierrdunn.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
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
    private static final int MAX_SOUNDS = 1;

    private AssetManager mAsset;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    public BeatBox(Context context){
        mAsset = context.getAssets();
        //Этот конструктор считается устаревшим,
        //но он требуется для обеспечения совместимости
        //mSoundPool.Builder
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSound();
    }

    public void play(Sound sound){
        Integer soundId = sound.getSoundId();
        if (soundId == null)
            return;
        mSoundPool.play(soundId, 1.0f, 1.0f,
                1, 0, 1.0f);
    }

    //выгрузка звуков из памяти
    public void release(){
        mSoundPool.release();
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
            try {
                String assetsPath = SOUND_FOLDER + "/" + filename;
                Sound sound = new Sound(assetsPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException ioe){
                Log.e(TAG, "Could not load sound " + filename, ioe);
            }

        }
    }

    private void load(Sound sound) throws IOException{
        AssetFileDescriptor afd = mAsset.openFd(sound.getAssetsPath());
        int soundId = mSoundPool.load(afd, 1);
        sound.setSoundId(soundId);
    }

    public List<Sound> getSound(){
        return mSounds;
    }
}
