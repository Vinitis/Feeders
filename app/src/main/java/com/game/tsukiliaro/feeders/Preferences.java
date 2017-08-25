package com.game.tsukiliaro.feeders;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Tsuki on 16.08.2017.
 */

class Preferences {

    public Context context;

    private static final Preferences ourInstance = new Preferences();

    static Preferences getInstance() {
        return ourInstance;
    }

    private Preferences() {
    }

    public void setContext (Context ctx){
        this.context = ctx;
    }

    public SharedPreferences getSharerdPreferences() {
        return this.context.getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
    }
}
