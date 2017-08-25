package com.game.tsukiliaro.feeders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import static android.R.id.message;

public class Options extends AppCompatActivity {


    ToggleButton musicToggle;
    SeekBar volumeBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        musicToggle = (ToggleButton) findViewById(R.id.musicToggle);
        volumeBar = (SeekBar) findViewById(R.id.volumeBar);


        musicToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);

                if (isChecked) {
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putInt("PLAYBACKENABLE", 1);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putInt("PLAYBACKENABLE", 0);
                    editor.commit();
                }
            }
        });

    }



    public void backTM (View view){
        finish();
        startActivity(new Intent(getApplicationContext(), StartScreen.class));
    }

    public void resetHighScore (View view){

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("HIGH_SCORE", 0);
        editor.commit();

        Toast.makeText(Options.this, "Reset", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event){

        if (event.getAction() == KeyEvent.ACTION_DOWN){
            switch (event.getKeyCode()){
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }

        return super.dispatchKeyEvent(event);
    }
}
