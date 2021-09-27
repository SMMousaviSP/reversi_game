package com.smmousavisp.game.ottlo;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class spalsh extends AppCompatActivity {
    ImageView pic1,pic2,pic3,pic4;
    Animation Rotate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        pic1=(ImageView)findViewById(R.id.img1);
        pic2=(ImageView)findViewById(R.id.img2);
        pic3=(ImageView)findViewById(R.id.img3);
        pic4=(ImageView)findViewById(R.id.img4);
        Rotate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate3d);
        pic1.setAnimation(Rotate);
        pic4.setAnimation(Rotate);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(spalsh.this, MainActivity.class);
                startActivity(i);
            }
        },1200);
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
