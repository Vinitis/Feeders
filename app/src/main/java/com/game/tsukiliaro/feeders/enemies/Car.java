package com.game.tsukiliaro.feeders.enemies;

import android.content.Context;
import android.widget.ImageView;


/**
 * Created by Tsuki on 25.08.2017.
 */

public class Car extends ImageView implements EnemiseAction {


    int iv_carX;

    public Car(Context context) {
        super(context);
    }

    @Override
    public void hitCheck() {
        iv_carX -= 20;
        if (iv_carX < (-500)){
            iv_carX = screenWidth + 100;
            iv_carY = (int) Math.floor(Math.random() * (frameHeight) - iv_car.getHeight());
        }
        iv_car.setX(iv_carX);
        iv_car.setY(iv_carY);
    }

    @Override
    public void changePos() {

    }
}
