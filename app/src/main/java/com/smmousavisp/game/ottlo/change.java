package com.smmousavisp.game.ottlo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class change extends AppCompatActivity {
    EditText txt_player1, txt_player2;
    ImageView btngo;
    String player1name;
    String player2name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        txt_player1 = findViewById(R.id.txt_player1);
        txt_player2 = findViewById(R.id.txt_player2);
        btngo = findViewById(R.id.btngo);
        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                player1name = txt_player1.getText().toString();
                player2name = txt_player2.getText().toString();

                Intent i = new Intent(change.this, gamebord.class);

                if ( player1name.isEmpty() ) player1name = "بازیکن اول";
                if ( player2name.isEmpty() ) player2name = "بازیکن دوم";
                i.putExtra("PLAYER1NAME",player1name);
                i.putExtra("PLAYER2NAME",player2name);
                startActivity(i);
            }
        });
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
