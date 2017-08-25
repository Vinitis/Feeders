package com.game.tsukiliaro.feeders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class StartScreen extends AppCompatActivity {

     TextView highScore;
     //TextView coins;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        highScore = (TextView) findViewById(R.id.highScore);
        //coins = (TextView) findViewById(R.id.coins);


        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int hScore = settings.getInt("HIGH_SCORE", 0);


        highScore.setText("High score: " + hScore);

        SharedPreferences set = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int coin = set.getInt("COIN", 0);

        //coins.setText("Coins: " + coin);


    }

    public void startGame(View view){
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void exit(View view){
        finish();
        System.exit(0);
    }

    public void options(View view){
        finish();
        startActivity(new Intent(getApplicationContext(), Options.class));
    }

    public void tutorial(View view){
        finish();
        startActivity(new Intent(getApplicationContext(), Tutorial.class));
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
