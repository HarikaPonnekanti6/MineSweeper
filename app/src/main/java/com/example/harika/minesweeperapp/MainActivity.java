package com.example.harika.minesweeperapp;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static int BOMB_NUMBER =10;
    public static ImageButton button;
    public static TextView textView1,textView2;
    public static int count = BOMB_NUMBER;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridMain.getInstance().DisplayGrid(this);
        button = (ImageButton) findViewById(R.id.button);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        //ImageButton for start/Reset the game
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setText(Integer.toString(10));
                BOMB_NUMBER = 10;
                button.setImageResource(R.drawable.smileyhappy);
                GridMain.getInstance().stopTimer();
                textView1.setText(Integer.toString(000));
                GridMain.getInstance().DisplayGrid(getBaseContext());
            }
        });
        textView2.setText( Integer.toString(BOMB_NUMBER));

    }

    //Creating Menu and populating the menu values.
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuoptions, menu);
        return true;
    }

    //When menu values are selected, populate the number of bombs
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.normal:
                BOMB_NUMBER = 8;
                textView2.setText( Integer.toString(BOMB_NUMBER));
                button.setImageResource(R.drawable.smileyhappy);
                GridMain.getInstance().stopTimer();
                textView1.setText(Integer.toString(000));
                invalidateOptionsMenu();
                GridMain.getInstance().DisplayGrid(this);
                return true;
            case R.id.intermediate:
                BOMB_NUMBER = 24;
                textView2.setText( Integer.toString(BOMB_NUMBER));
                button.setImageResource(R.drawable.smileyhappy);
                GridMain.getInstance().stopTimer();
                textView1.setText(Integer.toString(000));
                invalidateOptionsMenu();
                GridMain.getInstance().DisplayGrid(this);
                return true;
            case R.id.hard:
                BOMB_NUMBER = 40;
                textView2.setText( Integer.toString(BOMB_NUMBER));
                button.setImageResource(R.drawable.smileyhappy);
                GridMain.getInstance().stopTimer();
                textView1.setText(Integer.toString(000));
                invalidateOptionsMenu();
                GridMain.getInstance().DisplayGrid(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
