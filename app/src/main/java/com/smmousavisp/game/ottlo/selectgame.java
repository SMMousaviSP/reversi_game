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

public class selectgame extends AppCompatActivity {
    ImageView frendbtn,cpubtn,cupbtn;
    Animation ro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectgame);
        frendbtn=(ImageView)findViewById(R.id.fbtn);
        ro= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate3d);
        frendbtn.setAnimation(ro);
        frendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(selectgame.this, change.class);
                startActivity(i);

            }
        });
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
