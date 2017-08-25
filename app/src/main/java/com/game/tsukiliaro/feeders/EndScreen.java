package com.game.tsukiliaro.feeders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class EndScreen extends AppCompatActivity {

    TextView tv_highScore;
    TextView tv_score;
    TextView tv_coin;


    public int highScore;
    public int coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        tv_highScore = (TextView) findViewById(R.id.tv_highScore);
        tv_score = (TextView) findViewById(R.id.tv_score);
        tv_coin = (TextView) findViewById(R.id.tv_coin);

        int score = getIntent().getIntExtra("SCORE", 0);
        tv_score.setText(score + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        highScore = settings.getInt("HIGH_SCORE", 0);

        if (score > highScore){
            tv_highScore.setText("High score: " + score);

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.commit();
        }
        else {
            tv_highScore.setText("High score: " + highScore);
        }

        coin = (score/10);
        tv_coin.setText("You get " + coin + " coins!" );

        SharedPreferences set = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        coin = set.getInt("COIN", 0);



    }

    public void backToStart(View view){
        finish();
        startActivity(new Intent(getApplicationContext(), StartScreen.class));
    }

    public void tryAgain(View view){
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
