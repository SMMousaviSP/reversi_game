package com.smmousavisp.game.ottlo;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class gamebord extends AppCompatActivity {

    ImageView[][] iv;
    ImageView img_playercolor;
    TextView txt_playername, txt_player1score, txt_player2score;

    int state = 1;
    String player1name, player2name;
    int[][] k = new int[9][9];
    int[][] p = new int[9][9];





    public void play(int i, int j){
        if (p[i][j] != 3) return;
        changeAnother(i, j);
        changeState(i, j);
        showPossibleMove();
        if ( !isAnotherMove() ) {
            changeState(-1, -1);
            showPossibleMove();
            if ( !isAnotherMove() ) {
                changeBoard();
                end();
            }
        }
        changeBoard();
    }

    public void changeState(int i, int j){
        if(state == 1){
            txt_playername.setText(player2name);
            img_playercolor.setImageResource(R.drawable.p2);
            try {
                iv[i][j].setImageResource(R.drawable.p1);
                k[i][j] = 1;
            } catch (Exception e) { }
            state++;
        } else {
            txt_playername.setText(player1name);
            img_playercolor.setImageResource(R.drawable.p1);
            try {
                iv[i][j].setImageResource(R.drawable.p2);
                k[i][j] = 2;
            } catch (Exception e) { }
            state--;
        }
    }

    public void changeBoard() {
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 9; j++){
                switch(k[i][j]) {
                    case 0:
                        iv[i][j].setImageResource(0);
                        break;
                    case 1:
                        iv[i][j].setImageResource(R.drawable.p1);
                        break;
                    case 2:
                        iv[i][j].setImageResource(R.drawable.p2);
                        break;
                }
                if (p[i][j] == 3){
                    iv[i][j].setImageResource(R.drawable.p3);
                }
            }
        }
        changeScore();
    }

    public void end() {
        String winner = "";
        int player1score = Integer.parseInt( txt_player1score.getText().toString() );
        int player2score = Integer.parseInt( txt_player2score.getText().toString() );
        if ( player1score  > player2score ) {
            winner = player1name;
        }
        if ( player1score  < player2score ) {
            winner = player2name;
        }

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(gamebord.this);
        View mView = getLayoutInflater().inflate(R.layout.end_dialog, null);

        TextView txt_status = mView.findViewById(R.id.txt_status);
        TextView txt_pinkscore = mView.findViewById(R.id.txt_pinkscore);
        TextView txt_bluescore = mView.findViewById(R.id.txt_bluescore);
        ImageView iv_exit = mView.findViewById(R.id.iv_exit);
        ImageView iv_again = mView.findViewById(R.id.iv_again);
        ImageView iv_star1 = mView.findViewById(R.id.iv_star1);
        ImageView iv_star2 = mView.findViewById(R.id.iv_star2);
        ImageView iv_star3 = mView.findViewById(R.id.iv_star3);

        iv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });

        if ( player1score != player2score) {
            txt_status.setText(winner);
            int point = Math.abs(player1score - player2score);
            if (point < 10) {
                iv_star1.setImageResource(R.drawable.active_star);
                iv_star2.setImageResource(R.drawable.disable_star);
                iv_star3.setImageResource(R.drawable.disable_star);
            }

            if (point >= 10 && point < 20) {
                iv_star1.setImageResource(R.drawable.active_star);
                iv_star2.setImageResource(R.drawable.active_star);
                iv_star3.setImageResource(R.drawable.disable_star);
            }

            if  (point >= 20) {
                iv_star1.setImageResource(R.drawable.active_star);
                iv_star2.setImageResource(R.drawable.active_star);
                iv_star3.setImageResource(R.drawable.active_star);
            }
        } else {
            txt_status.setText("مساوی");
            iv_star1.setImageResource(R.drawable.disable_star);
            iv_star2.setImageResource(R.drawable.disable_star);
            iv_star3.setImageResource(R.drawable.disable_star);
        }

        txt_bluescore.setText( String.valueOf(player1score) );
        txt_pinkscore.setText( String.valueOf(player2score) );

        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable( false );
        dialog.show();
    }

    public boolean isAnotherMove () {
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 9; j++){
                if (p[i][j] == 3){
                    return true;
                }
            }
        }
        return false;
    }

    public void showPossibleMove () {
        clearPossibleMove();
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (k[i][j] == 0){
                    colPossibleMove(i, j);
                    rowPossibleMove(i, j);
                    crossPossibleMove(i, j);
                }
            }
        }
    }

    public void clearPossibleMove() {
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 9; j++){
                p[i][j] = 0;
            }
        }
    }

    public void colPossibleMove(int i, int j) {
        for (int i2 = i + 1; i2 < 9; i2++){
            try {
                if (k[i + 1][j] == state) break;
            }
            catch (Exception e){}
            if (k[i2][j] == 0) break;
            if (k[i2][j] == state) p[i][j] = 3;
        }
        for (int i2 = i - 1; i2 > 0; i2--){
            try {
                if (k[i - 1][j] == state) break;
            }
            catch (Exception e){}
            if (k[i2][j] == 0) break;
            if (k[i2][j] == state) p[i][j] = 3;
        }
    }

    public void rowPossibleMove(int i, int j) {
        for (int j2 = j + 1; j2 < 9; j2++){
            try {
                if (k[i][j + 1] == state) break;
            }
            catch (Exception e){}
            if (k[i][j2] == 0) break;
            if (k[i][j2] == state) p[i][j] = 3;
        }
        for (int j2 = j - 1; j2 > 0; j2--){
            try {
                if (k[i][j - 1] == state) break;
            }
            catch (Exception e){}
            if (k[i][j2] == 0) break;
            if (k[i][j2] == state) p[i][j] = 3;
        }
    }

    public void crossPossibleMove(int i, int j) {
        for (int j2 = j + 1, i2 = i + 1; j2 < 9 && i2 < 9; j2++, i2++){
            try {
                if (k[i + 1][j + 1] == state) break;
            }
            catch (Exception e){}
            if (k[i2][j2] == 0) break;
            if (k[i2][j2] == state) p[i][j] = 3;
        }
        for (int j2 = j + 1, i2 = i - 1; j2 < 9 && i2 > 0; j2++, i2--){
            try {
                if (k[i - 1][j + 1] == state) break;
            }
            catch (Exception e){}
            if (k[i2][j2] == 0) break;
            if (k[i2][j2] == state) p[i][j] = 3;
        }
        for (int j2 = j - 1, i2 = i + 1; j2 > 0 && i2 < 9; j2--, i2++){
            try {
                if (k[i + 1][j - 1] == state) break;
            }
            catch (Exception e){}
            if (k[i2][j2] == 0) break;
            if (k[i2][j2] == state) p[i][j] = 3;
        }
        for (int j2 = j - 1, i2 = i - 1; j2 > 0 && i2 > 0; j2--, i2--){
            try {
                if (k[i - 1][j - 1] == state) break;
            }
            catch (Exception e){}
            if (k[i2][j2] == 0) break;
            if (k[i2][j2] == state) p[i][j] = 3;
        }

    }

    public void changeAnother(int i, int j) {
        changeCol(i, j);
        changeRow(i, j);
        changeCross(i, j);
    }

    public void changeRow(int i, int j) {
        for (int j2 = j + 1; j2 < 9; j2++){
            try {
                if (k[i][j + 1] == state) break;
            }
            catch (Exception e){}
            if (k[i][j2] == 0) break;
            if (k[i][j2] == state) {
                for (int j3 = j; j3 < j2; j3++){
                    k[i][j3] = state;
                }
            }
        }
        for (int j2 = j - 1; j2 > 0; j2--){
            try {
                if (k[i][j - 1] == state) break;
            }
            catch (Exception e){}
            if (k[i][j2] == 0) break;
            if (k[i][j2] == state) {
                for (int j3 = j; j3 > j2; j3--){
                    k[i][j3] = state;
                }
            }
        }

    }

    public void changeCol(int i, int j) {
        for (int i2 = i + 1; i2 < 9; i2++){
            try {
                if (k[i + 1][j] == state) break;
            }
            catch (Exception e){}
            if (k[i2][j] == 0) break;
            if (k[i2][j] == state) {
                for (int i3 = i; i3 < i2; i3++){
                    k[i3][j] = state;
                }
            }
        }
        for (int i2 = i - 1; i2 > 0; i2--){
            try {
                if (k[i - 1][j] == state) break;
            }
            catch (Exception e){}
            if (k[i2][j] == 0) break;
            if (k[i2][j] == state) {
                for (int i3 = i; i3 > i2; i3--){
                    k[i3][j] = state;
                }
            }
        }
    }

    public void changeCross(int i, int j) {
        for (int j2 = j + 1, i2 = i + 1; j2 < 9 && i2 < 9; j2++, i2++){
            try {
                if (k[i + 1][j + 1] == state) break;
            }
            catch (Exception e){}
            if (k[i2][j2] == 0) break;
            if (k[i2][j2] == state) {
                for (int i3 = i, j3 = j; i3 < i2 ; i3++, j3++){
                    k[i3][j3] = state;
                }
            }
        }
        for (int j2 = j + 1, i2 = i - 1; j2 < 9 && i2 > 0; j2++, i2--){
            try {
                if (k[i - 1][j + 1] == state) break;
            }
            catch (Exception e){}
            if (k[i2][j2] == 0) break;
            if (k[i2][j2] == state) {
                for (int i3 = i, j3 = j; i3 > i2 ; i3--, j3++){
                    k[i3][j3] = state;
                }
            }
        }
        for (int j2 = j - 1, i2 = i + 1; j2 > 0 && i2 < 9; j2--, i2++){
            try {
                if (k[i + 1][j - 1] == state) break;
            }
            catch (Exception e){}
            if (k[i2][j2] == 0) break;
            if (k[i2][j2] == state) {
                for (int i3 = i, j3 = j; i3 < i2 ; i3++, j3--){
                    k[i3][j3] = state;
                }
            }
        }
        for (int j2 = j - 1, i2 = i - 1; j2 > 0 && i2 > 0; j2--, i2--){
            try {
                if (k[i - 1][j - 1] == state) break;
            }
            catch (Exception e){}
            if (k[i2][j2] == 0) break;
            if (k[i2][j2] == state) {
                for (int i3 = i, j3 = j; i3 > i2 ; i3--, j3--){
                    k[i3][j3] = state;
                }
            }
        }
    }

    public void changeScore() {
        int player1score = 0, player2score = 0;
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 9; j++){
                if (k[i][j] == 1) player1score++;
                if (k[i][j] == 2) player2score++;
            }
        }
        txt_player1score.setText( String.valueOf(player1score) );
        txt_player2score.setText( String.valueOf(player2score) );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamebord);

        img_playercolor = findViewById(R.id.img_playercolor);
        txt_playername = findViewById(R.id.txt_playername);
        txt_player1score = findViewById(R.id.txt_player1score);
        txt_player2score = findViewById(R.id.txt_player2score);

        Bundle extras = getIntent().getExtras();
        player1name = extras.getString("PLAYER1NAME");
        player2name = extras.getString("PLAYER2NAME");
        txt_playername.setText(player1name);

        iv = new ImageView[][]  {
                { findViewById(R.id.iv11), findViewById(R.id.iv11), findViewById(R.id.iv12), findViewById(R.id.iv13), findViewById(R.id.iv14), findViewById(R.id.iv15), findViewById(R.id.iv16), findViewById(R.id.iv17), findViewById(R.id.iv18) } ,
                { findViewById(R.id.iv11), findViewById(R.id.iv11), findViewById(R.id.iv12), findViewById(R.id.iv13), findViewById(R.id.iv14), findViewById(R.id.iv15), findViewById(R.id.iv16), findViewById(R.id.iv17), findViewById(R.id.iv18) } ,
                { findViewById(R.id.iv21), findViewById(R.id.iv21), findViewById(R.id.iv22), findViewById(R.id.iv23), findViewById(R.id.iv24), findViewById(R.id.iv25), findViewById(R.id.iv26), findViewById(R.id.iv27), findViewById(R.id.iv28) } ,
                { findViewById(R.id.iv31), findViewById(R.id.iv31), findViewById(R.id.iv32), findViewById(R.id.iv33), findViewById(R.id.iv34), findViewById(R.id.iv35), findViewById(R.id.iv36), findViewById(R.id.iv37), findViewById(R.id.iv38) } ,
                { findViewById(R.id.iv41), findViewById(R.id.iv41), findViewById(R.id.iv42), findViewById(R.id.iv43), findViewById(R.id.iv44), findViewById(R.id.iv45), findViewById(R.id.iv46), findViewById(R.id.iv47), findViewById(R.id.iv48) } ,
                { findViewById(R.id.iv51), findViewById(R.id.iv51), findViewById(R.id.iv52), findViewById(R.id.iv53), findViewById(R.id.iv54), findViewById(R.id.iv55), findViewById(R.id.iv56), findViewById(R.id.iv57), findViewById(R.id.iv58) } ,
                { findViewById(R.id.iv61), findViewById(R.id.iv61), findViewById(R.id.iv62), findViewById(R.id.iv63), findViewById(R.id.iv64), findViewById(R.id.iv65), findViewById(R.id.iv66), findViewById(R.id.iv67), findViewById(R.id.iv68) } ,
                { findViewById(R.id.iv71), findViewById(R.id.iv71), findViewById(R.id.iv72), findViewById(R.id.iv73), findViewById(R.id.iv74), findViewById(R.id.iv75), findViewById(R.id.iv76), findViewById(R.id.iv77), findViewById(R.id.iv78) } ,
                { findViewById(R.id.iv81), findViewById(R.id.iv81), findViewById(R.id.iv82), findViewById(R.id.iv83), findViewById(R.id.iv84), findViewById(R.id.iv85), findViewById(R.id.iv86), findViewById(R.id.iv87), findViewById(R.id.iv88) }
        };

        k[4][4] = 2;
        k[5][5] = 2;
        k[5][4] = 1;
        k[4][5] = 1;


        showPossibleMove();
        changeBoard();
        changeScore();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play( Integer.parseInt( String.valueOf( v.getTag().toString().charAt(0) ) ), Integer.parseInt( String.valueOf( v.getTag().toString().charAt(1) ) ));
            }
        };

        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 9; j++){
                iv[i][j].setOnClickListener(listener);
            }
        }


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
