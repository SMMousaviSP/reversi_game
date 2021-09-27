package com.smmousavisp.game.ottlo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
    ImageView logo, btnstart, btnabout, btnexit;
    Animation bala, toleft, toright;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = findViewById(R.id.ottlo_bala);
        toleft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.toleft);
        toright = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.toright);
        bala = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bala);
        logo.setAnimation(bala);
        btnstart = findViewById(R.id.btnstart);
        btnabout = findViewById(R.id.btnabout);
        btnexit = findViewById(R.id.btnexit);
        btnstart.setAnimation(toleft);
        btnexit.setAnimation(toleft);
        btnabout.setAnimation(toright);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, selectgame.class);
                startActivity(i);
            }
        });

        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });



    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
