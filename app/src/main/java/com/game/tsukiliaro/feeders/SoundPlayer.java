package com.game.tsukiliaro.feeders;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by Tsuki on 07.08.2017.
 */

public class SoundPlayer {

    private static SoundPool soundPool;
    private static int hitSound;
    private static int overSound;
    private static int blankSpace;

    public SoundPlayer(Context context){

        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        hitSound = soundPool.load(context, R.raw.hit, 1);
        overSound = soundPool.load(context, R.raw.over, 1);
        blankSpace = soundPool.load(context, R.raw.blank, 1);

    }

    public void playHitSound(){
        this.playSound(hitSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playOverSound(){
        this.playSound(overSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playSound (int sound, float leftVol, float rightVol, int priority, int loop, float rate){
            if(this.isPlaybackEnable()) {
                soundPool.play(sound, leftVol, rightVol, priority, loop, rate);
            }
    }

    //public void playBlankSpace(){ soundPool.play(blankSpace, 1.0f, 1.0f, 1, 0, 1.0f); }

    public void loopBlank () { soundPool.setLoop(blankSpace, -1);}

    public boolean isPlaybackEnable(){
        SharedPreferences settings = Preferences.getInstance().getSharerdPreferences();
        if(settings.getInt("PLAYBACKENABLE", 0) == 0){
            return false;
        }else {return true;}

    }
}
