package com.example.student.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button but;
    ImageButton imagebutton;
    FragmentManager fm;
    MainActivityFragment fragment;
    int i = 0;
    TextView tv = null;
    int a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagebutton = (ImageButton) findViewById(R.id.button2);
        fm = getSupportFragmentManager();
        fragment = (MainActivityFragment) fm.findFragmentById(R.id.fragment);
        tv = fragment.tv;
        imagebutton.setImageResource(R.drawable.dealup);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void clickMethod(View view) {

        if (!GetterSetter.isStanding) {
            GetterSetter.playerScore = 0;
            GetterSetter.dealerScore = 0;
            GetterSetter.hit++;
            GetterSetter.buttonpressed = 1;
            GetterSetter.fly = 0;
            GetterSetter.vertfly = 400;

        }

        //tv.setText("Card Number:" + GetterSetter.hit  + " suit:" + fragment.card[GetterSetter.hit].suit + " rank:" + fragment.card[GetterSetter.hit].rank);
        //tv.setText("Player Score:"  + GetterSetter.playerScore);


    }

    public void clickMethod1(View view) {

        GetterSetter.playerScore = 0;
        GetterSetter.dealerScore = 0;
        GetterSetter.dealerhit = GetterSetter.hit;
        GetterSetter.buttonpressed = 1;
        GetterSetter.isStanding = true;




        //tv.setText("Card Number:" + GetterSetter.hit  + " suit:" + fragment.card[GetterSetter.hit].suit + " rank:" + fragment.card[GetterSetter.hit].rank);
        //tv.setText("Player Score:"  + GetterSetter.playerScore);


    }

    public void clickMethod2(View view) {

        if (GetterSetter.startHand == 1)
        {
            GetterSetter.startHand = 0;
            imagebutton.setImageResource(R.drawable.dealup);
            fragment.seek.setEnabled(true);

            //fragment.tv3.setText("Place Your Bet!");
            //fragment.tv2.setText("");
            //fragment.tv1.setText("");


        }
        else {
            GetterSetter.playerScore = 0;
            GetterSetter.dealerScore = 0;
            GetterSetter.hit = 3;
            GetterSetter.dealerhit = 1;
            GetterSetter.buttonpressed = 1;
            fragment.shuffleDeck(GetterSetter.card);
            GetterSetter.isStanding = false;
            imagebutton.setImageResource(R.drawable.redeal);
            GetterSetter.startHand = 1;
            fragment.seek.setEnabled(false);
        }


        //tv.setText("Card Number:" + GetterSetter.hit  + " suit:" + fragment.card[GetterSetter.hit].suit + " rank:" + fragment.card[GetterSetter.hit].rank);
        //tv.setText("Player Score:"  + GetterSetter.playerScore);


    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
