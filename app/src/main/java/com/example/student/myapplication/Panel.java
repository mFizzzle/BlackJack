package com.example.student.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

/**
 * Created by student on 12/8/15.
 */
public class Panel extends SurfaceView implements SurfaceHolder.Callback {
    //GLOBAL VARIABLES
    Paint paint;
    private CanvasThread canvasthread;
    int localscore = 0;
    //Variable used to hold our bitmap

    CardDraw carddraw;



    public Panel(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub

        getHolder().addCallback(this);
        canvasthread = new CanvasThread(getHolder(), this);
        setFocusable(true);
        paint = new Paint();
        carddraw = new CardDraw(context);


    }


    public Panel(Context context) {
        super(context);
        getHolder().addCallback(this);
        canvasthread = new CanvasThread(getHolder(), this);
        setFocusable(true);

    }


    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);


        if (GetterSetter.startHand == 0) {

        } else {
            for (int q = 0; q <= 1; q++) {

                if(q  ==0 && GetterSetter.dealerhit < 3)
                {
                    carddraw.deal(canvas,501,80*q, -600);
                }
                else {
                    carddraw.deal(canvas, q, (80 * q), -600);
                }
                if (GetterSetter.buttonpressed == 1) {
                    scoreit(q, true, false);

                }


            }


            for (int q = 2; q <= GetterSetter.hit; q++) {

                if (q == GetterSetter.hit)

                {   if (GetterSetter.fly <81) {
                    GetterSetter.fly = GetterSetter.fly + 4;
                    GetterSetter.vertfly = GetterSetter.vertfly - 20;
                }

                    if (GetterSetter.vertfly < 0) {
                        GetterSetter.vertfly = 0;
                    }

                    carddraw.deal(canvas, q, (GetterSetter.fly * q), GetterSetter.vertfly);
                }
                else {
                    carddraw.deal(canvas, q, (80 * q), 0);
                }
                if (GetterSetter.buttonpressed == 1) {
                    scoreit(q, false, true);


                }

            }
            for (int x = (GetterSetter.hit + 1); x <= GetterSetter.dealerhit; x++) {

                if (x > 2) {
                    carddraw.deal(canvas, x, 80 * (x - (GetterSetter.hit - 1)), -600);
                } else {

                    carddraw.deal(canvas, x, 80 * x, -600);
                }
                if (GetterSetter.buttonpressed == 1) {
                    scoreit(x, true, false);


                }

            }
            GetterSetter.buttonpressed = 0;

        }
    }

    public void scoreit(int q, boolean dealer, boolean player) {
    if (q == 0 && GetterSetter.dealerhit < 3)
    {
        localscore = 0;
    }
        else {
        if (GetterSetter.card[q].rank >= 8 && GetterSetter.card[q].rank < 12) {
            localscore = 10;
        }
        if (GetterSetter.card[q].rank == 12) {

            if (player) {
                if (GetterSetter.playerScore > 10) {
                    localscore = 1;
                } else {
                    localscore = 11;
                }

            }

            if (dealer) {
                if (GetterSetter.dealerScore > 10) {
                    localscore = 1;
                } else {
                    localscore = 11;
                }

            }
        }
        if (GetterSetter.card[q].rank <= 8) {
            localscore = GetterSetter.card[q].rank + 2;
        }

        if (player) {
            GetterSetter.playerScore = GetterSetter.playerScore + localscore;
        }
        if (dealer) {
            GetterSetter.dealerScore = GetterSetter.dealerScore + localscore;
        }
    }


    }


    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub

    }

    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        canvasthread.setRunning(true);
        canvasthread.start();


    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        boolean retry = true;
        canvasthread.setRunning(false);
        while (retry) {
            try {
                canvasthread.join();
                retry = false;
            } catch (InterruptedException e) {
                // we will try it again and again...
            }
        }


    }


}