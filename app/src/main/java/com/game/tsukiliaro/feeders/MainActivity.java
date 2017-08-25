package com.game.tsukiliaro.feeders;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.SoundPool;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Console;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView tv_score;
    private TextView tv_start;
    private ImageView iv_head;
    private ImageView iv_yellow;
    private ImageView iv_pink;
    private ImageView iv_black;
    private ImageView iv_car;
    //private ImageView iv_bottle;
    private Button pause;
    private Button menu;


    private int headY;
    private int iv_yellowX;
    private int iv_yellowY;
    private int iv_pinkX;
    private int iv_pinkY;
    private int iv_blackX;
    private int iv_blackY;
    private int iv_carX;
    private int iv_carY;
    //private int iv_bottleY;
    //private int iv_bottleX;


    int score = 0;
    int t = 0;
    int t1 = 0;
    int m = 1;


    private int frameHeight;
    private int headSize;

    int screenWidth;
    int screenHeight;

    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private SoundPlayer sound;

    private boolean actionFlg = false;
    private boolean startFlg = false;
    private boolean pauseFlg = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound = new SoundPlayer(this);

        tv_score = (TextView) findViewById(R.id.tv_score);
        tv_start = (TextView) findViewById(R.id.tv_start);
        iv_head = (ImageView) findViewById(R.id.iv_head);
        iv_yellow = (ImageView) findViewById(R.id.iv_yellow);
        iv_pink = (ImageView) findViewById(R.id.iv_pink);

        iv_black = (ImageView) findViewById(R.id.iv_black);
        iv_car = (ImageView) findViewById(R.id.iv_car);
        //iv_bottle = (ImageView) findViewById(R.id.iv_bottle);
        pause = (Button) findViewById(R.id.pause);
        menu = (Button) findViewById(R.id.menu);

        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        iv_yellow.setX(-80.0f);
        iv_yellow.setY(-80.0f);
        iv_pink.setX(-80.0f);
        iv_pink.setY(-80.0f);
        iv_black.setX(-80.0f);
        iv_black.setY(-80.0f);
        iv_car.setX(-80.0f);
        iv_car.setY(-80.0f);
        //iv_bottle.setX(-80.0f);
        //iv_bottle.setY(-80.0f);

        //tv_score.setVisibility(View.INVISIBLE);
        tv_score.setText("Score: 0");


    }
`
    public void menuMove(){
        finish();
        startActivity(new Intent(getApplicationContext(), StartScreen.class));
    }

    public void pauseNow (View view){

        if(!pauseFlg){
            pauseFlg = true;
            timer.cancel();
            timer = null;
            pause.setText("START");
        }else{
            pauseFlg = false;
            pause.setText("START");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            },0,20);

        }
    }

    public void changePos(){

        hitCheck();


            iv_yellowX -= 12;
            if (iv_yellowX < (-500)){
                iv_yellowX = screenWidth + 20;
                iv_yellowY = (int) Math.floor(Math.random() * (frameHeight - iv_yellow.getHeight()));
            }
            iv_yellow.setX(iv_yellowX);
            iv_yellow.setY(iv_yellowY);

            iv_blackX -= 10;
            if (iv_blackX < (-500)){
                iv_blackX = screenWidth + 10;
                iv_blackY = (int) Math.floor(Math.random() * (frameHeight) - iv_black.getHeight() );
            }

            iv_black.setX(iv_blackX);
            iv_black.setY(iv_blackY);



            /*iv_bottleX -= 11;
            if (iv_bottleX < (-500)){
                iv_bottleX = screenWidth + 300;
                iv_bottleY = (int) Math.floor(Math.random() * (frameHeight) - iv_bottle.getHeight());
            }
            iv_bottle.setX(iv_bottleX);
            iv_bottle.setY(iv_bottleY); */

            iv_pinkX -= 15;
            if (iv_pinkX < (-500)){
                iv_pinkX =  screenHeight + 5000;
                iv_pinkY = (int) Math.floor(Math.random() * (frameHeight) - iv_pink.getHeight());
            }
            iv_pink.setX(iv_pinkX);
            iv_pink.setY(iv_pinkY);

        if (actionFlg){
            t1 ++;
            headY -= ((2/m) * t1);
        } else {
            t ++;
            headY += ((2/m) * t);
        }


        if (headY < 0) headY = 0;

        if (headY > frameHeight - headSize) headY = frameHeight - headSize;

        iv_head.setY(headY);

        tv_score.setText("Score: " + score);

    }

    public void hitCheck(){

        int iv_yellowCenterX = iv_yellowX + iv_yellow.getWidth() / 2;
        int iv_yellowCenterY = iv_yellowY + iv_yellow.getHeight() / 2;

        if( 0 <= iv_yellowCenterX && iv_yellowCenterX <= headSize
                && headY <= iv_yellowCenterY && iv_yellowCenterY <= headY + headSize){
            score += 10;
            iv_yellowX = -150;
            sound.playHitSound();
        }

        int iv_blackCenterX = iv_blackX + iv_black.getWidth() / 112;
        int iv_blackCenterY = iv_blackY + iv_black.getHeight() / 2;

        if( 0 <= iv_blackCenterX && iv_blackCenterX <= headSize
                && headY <= iv_blackCenterY && iv_blackCenterY <= headY + headSize){

            //End
            timer.cancel();
            timer = null;
            sound.playOverSound();

            //End screen
            finish();
            Intent intent = new Intent(getApplicationContext(), EndScreen.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);


        }

        int iv_carCenterX = iv_carX + iv_car.getWidth() / 2;
        int iv_carCenterY = iv_carY + iv_car.getHeight() / 2;

        if( 0 <= iv_carCenterX && iv_carCenterX <= headSize
                && headY <= iv_carCenterY && iv_carCenterY <= headY + headSize){

            timer.cancel();
            timer = null;
            sound.playOverSound();

            finish();
            Intent iiii = new Intent(getApplicationContext(), EndScreen.class);
            iiii.putExtra("SCORE", score);
            startActivity(iiii);

        }

        /*int iv_bottleCenterX = iv_bottleX + iv_bottle.getWidth() / 2;
        int iv_bottleCenterY = iv_bottleY + iv_bottle.getHeight() / 2;

        if( 0 <= iv_bottleCenterX && iv_bottleCenterX <= headSize
                && headY <= iv_bottleCenterY && iv_bottleCenterY <= headY + headSize){

            timer.cancel();
            timer = null;
            sound.playOverSound();

            finish();
            Intent iiiii = new Intent(getApplicationContext(), EndScreen.class);
            iiiii.putExtra("SCORE", score);
            startActivity(iiiii);

        } */

        int iv_pinkCenterX = iv_pinkX + iv_pink.getWidth() / 2;
        int iv_pinkCenterY = iv_pinkY + iv_pink.getHeight() / 2;

        if( 0 <= iv_pinkCenterX && iv_pinkCenterX <= headSize
                && headY <= iv_pinkCenterY && iv_pinkCenterY <= headY + headSize){
            score += 30;
            iv_pinkX = -100;
            sound.playHitSound();
        }

        if (( 0 <= iv_blackCenterX && iv_blackCenterX <= headSize
                && headY <= iv_blackCenterY && iv_blackCenterY <= headY + headSize)
                && ( 0 <= iv_carCenterX && iv_carCenterX <= headSize
                && headY <= iv_carCenterY && iv_carCenterY <= headY + headSize)){

            timer.cancel();
            timer = null;
            sound.playOverSound();

            finish();
            Intent iiiii = new Intent(getApplicationContext(), EndScreen.class);
            iiiii.putExtra("SCORE", score);
            startActivity(iiiii);
        }



    }

    public boolean onTouchEvent(MotionEvent me) {

        if (!startFlg){

            startFlg = true;
            sound.loopBlank();


            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
            frameHeight = frame.getHeight();

            headY = (int)iv_head.getY();

            headSize = iv_head.getHeight();

            tv_start.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            changePos();
                        }
                    });
                }
            },0,20);

        }else {
            if(me.getAction() == MotionEvent.ACTION_DOWN){
                iv_pink.setVisibility(View.VISIBLE);
                iv_yellow.setVisibility(View.VISIBLE);
                iv_black.setVisibility(View.VISIBLE);
                iv_car.setVisibility(View.VISIBLE);
                actionFlg = true;
                t1 = 0;
            }else if(me.getAction() == MotionEvent.ACTION_UP) {
                actionFlg = false;
                t = 0;
            }
        }

        return true;
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
