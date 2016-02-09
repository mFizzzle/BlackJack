package com.example.student.myapplication;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    Button but;
    Card[] card;
    int z = 0;
    View rootView;
    TextView tv;
    TextView tv1;
    Handler mHandler;
    TextView tv2;
    TextView tv3;
    SeekBar seek;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_main, container,
                false);
        seek = (SeekBar) rootView.findViewById(R.id.seekBar);
        seek.setMax(GetterSetter.cash);

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                GetterSetter.bet = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        tv = (TextView) rootView.findViewById(R.id.textView);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "alex.ttf");

        tv.setTypeface(tf);
        tv.setTextSize(14);
        tv.setTextColor(Color.GREEN);
        tv1 = (TextView) rootView.findViewById(R.id.textView1);

        tv1.setTypeface(tf);
        tv1.setTextSize(14);
        tv1.setTextColor(Color.GREEN);

        tv2 = (TextView) rootView.findViewById(R.id.textView2);

        tv2.setTypeface(tf);
        tv2.setTextSize(14);
        tv2.setTextColor(Color.GREEN);
        tv3 = (TextView) rootView.findViewById(R.id.textView3);

        tv3.setTypeface(tf);
        tv3.setTextSize(14);
        tv3.setTextColor(Color.GREEN);


        rootView.setBackgroundColor(Color.BLACK);

        card = new Card[52];

        for (int suit = 0; suit < 4; suit++) {
            for (int rank = 0; rank < 13; rank++) {
                card[z] = new Card(suit, rank);
                z++;
            }


        }
        card = shuffleDeck(card);
        GetterSetter.card = card;

        mHandler = new Handler();
        mHandler.post(mUpdate);

        return rootView;
    }

    private Runnable mUpdate = new Runnable() {

        public void run() {
            if (GetterSetter.startHand == 0) {
                tv2.setText("Cash: " + (GetterSetter.cash - GetterSetter.bet) + " ");
                tv3.setText("Place Bet: " + GetterSetter.bet + " ");

            } else {
                tv3.setText("Bet: " + GetterSetter.bet + " ");
                //tv.setText("Player: " + GetterSetter.playerScore + " ");

                if (GetterSetter.playerScore <= 21) {
                    tv.setText("Player: " + GetterSetter.playerScore + " ");
                    tv1.setText("Dealer: " + GetterSetter.dealerScore + " ");
                    tv2.setText("Cash: " + (GetterSetter.cash - GetterSetter.bet) + " ");


                }
                if (GetterSetter.playerScore != 0 && GetterSetter.playerScore >= 21) {
                    tv.setText("Bust!");
                    removeBet();
                    GetterSetter.isStanding = true;
                    seek.setProgress(0);
                    GetterSetter.bet = 0;

                }
                if (GetterSetter.buttonpressed == 0) {

                    if (GetterSetter.dealerhit > 1) {
                        if (GetterSetter.dealerScore < 17 && GetterSetter.dealerScore != 0) {
                            GetterSetter.playerScore = 0;
                            GetterSetter.dealerScore = 0;
                            GetterSetter.dealerhit++;
                            GetterSetter.buttonpressed = 1;


                        }
                        if (GetterSetter.dealerScore >= 17) {
                            computeWin();

                        }


                    }


                }


            }
            mHandler.postDelayed(this, 1);
        }


        };

    public void computeWin() {
        if (GetterSetter.playerScore > GetterSetter.dealerScore || GetterSetter.dealerScore > 21) {
            GetterSetter.cash = GetterSetter.cash + (GetterSetter.bet * 2);
            seek.setProgress(0);
            GetterSetter.bet = 0;

        }
        if (GetterSetter.dealerScore > GetterSetter.playerScore && GetterSetter.dealerScore <= 21) {
            removeBet();
            seek.setProgress(0);
            //GetterSetter.bet = 0;

        }
    }

    public void removeBet() {
        GetterSetter.cash = GetterSetter.cash - GetterSetter.bet;
        seek.setProgress(0);


    }

    public Card[] shuffleDeck(Card[] deck) {
        Random rnd = new Random();
        Card tempCard = new Card(0, 0);
        for (int shuffleCount = 0; shuffleCount < 52; shuffleCount++) {
            int randomCard = rnd.nextInt(52);
            tempCard = deck[randomCard];
            deck[randomCard] = deck[shuffleCount];
            deck[shuffleCount] = tempCard;

        }
        return deck;

    }

}
