package com.example.himanshupalve.dicegame;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {

    int Turnscore=0;
    int compscore=0,playerscore=0;
    boolean turn,game;
    Handler handler=new Handler();
    public MainActivity() {
        turn = false;
        game=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void computer()
    {
        if(!game) return;
        int d;
        ImageView i= findViewById(R.id.DiceImage);
        Random rand = new Random();
        int r = rand.nextInt(6) + 1;
            d=rand.nextInt(6)+1;
        Animation animation1 =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        switch (d)
        {
            case 1:
                i.setImageResource(R.drawable.dice1);
                Turnscore=0;
                i.startAnimation(animation1);
                setscore();
                return;
            //break;
            case 2:i.setImageResource(R.drawable.dice2);
                break;
            case 3:i.setImageResource(R.drawable.dice3);
                break;
            case 4:i.setImageResource(R.drawable.dice4);
                break;
            case 5:i.setImageResource(R.drawable.dice5);
                break;
            case 6:i.setImageResource(R.drawable.dice6);
                break;
        }
        Turnscore+=d;
        disp();
        i.startAnimation(animation1);
        if(r<4)
        {
            computer();
            //handler.postDelayed(th,1000);
        }
        else
            setscore();
//        new android.os.Handler().postDelayed();
    }
    Runnable th=new Runnable()
    {
        @Override
        public void run()
        {
            computer();
        }
    };
    public void rollDice(View view) {
        if(!game||turn) return;
        ImageView i= findViewById(R.id.DiceImage);
        Random rand=new Random();
        int d=rand.nextInt(6)+1;
        Animation animation1 =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        switch (d)
        {
            case 1:
                i.setImageResource(R.drawable.dice1);
                Turnscore=0;
                i.startAnimation(animation1);
                setscore();
                return;
//                    break;
            case 2:i.setImageResource(R.drawable.dice2);
                break;
            case 3:i.setImageResource(R.drawable.dice3);
                break;
            case 4:i.setImageResource(R.drawable.dice4);
                break;
            case 5:i.setImageResource(R.drawable.dice5);
                break;
            case 6:i.setImageResource(R.drawable.dice6);
                break;
        }
        i.startAnimation(animation1);
        Turnscore+=d;
        disp();
    }

    public void setscore()
    {

        if(turn)
            compscore+=Turnscore;
        else
            playerscore+=Turnscore;
//        Toast toast;
        if(playerscore>=100)
        {
            makeText(MainActivity.this, "You Win!!", LENGTH_LONG).show();
            game=false;
        }
        else if(compscore>=100)
        {
            makeText(MainActivity.this, "Computer Won;)", LENGTH_LONG).show();
            game=false;
        }
        turn^=true;
        Turnscore=0;
        if(turn)
            handler.postDelayed(th,1000);
        disp();

    }
    public void HoldDice(View view) {
        if(game) {
            setscore();
        }
    }

    public void disp()
    {
        TextView t= findViewById(R.id.turnscore);
        t.setText(Integer.toString(Turnscore));
        t= findViewById(R.id.turn);
        String s1,s2;
        String st=" ";
        TextView s= findViewById(R.id.textview);
        s.setText(Integer.toString(playerscore)+st+Integer.toString(compscore));
        s1="Computer";
        s2="YOU";
        if(turn) {
            t.setText(s1);
        }
        else
            t.setText(s2);
    }

    public void Reset(View view) {
        Turnscore=0;
        compscore=0;
        playerscore=0;
        turn=false;
        game=true;
        disp();
    }
}
