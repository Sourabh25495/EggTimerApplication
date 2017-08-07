package com.sourabhkulkarni.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekbar;
    TextView timerTextView;
    boolean ActiveCounter=false;
    Button controlbutton;
    CountDownTimer cdt;

    public void resetTimer(){
        timerTextView.setText("0:30");
        timerSeekbar.setProgress(30);
        cdt.cancel();
        controlbutton.setText("go");
        timerSeekbar.setEnabled(true);
        ActiveCounter=false;


    }

    public void updateTimer(int secLeft){

        int minutes=(int)secLeft/60;
        int sec=secLeft-minutes*60;

        String secStr=Integer.toString(sec);
          if(sec<=9) {
              secStr = "0" + secStr;
          }
              timerTextView.setText(Integer.toString(minutes) + ":" + secStr);


    }

    public void ControlTimer(View view){
        Log.i("Pressed", "Pressed");
        if (ActiveCounter==false) {
            ActiveCounter = true;
            timerSeekbar.setEnabled(false);
            controlbutton.setText("stop");


            cdt=new CountDownTimer(timerSeekbar.getProgress() * 1000 + 100, 1000) {


                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {
                    timerTextView.setText("0:00");

                    Log.i("Timer", "Finished");
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.bell);
                    mp.start();
                    //resetTimer();
                    controlbutton.setText("reset");


                }
            }.start();
        }else {

                resetTimer();

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekbar=(SeekBar)findViewById((R.id.seekBar));
        timerTextView=(TextView)findViewById(R.id.TimertextView);
        timerSeekbar.setMax(600);
        timerSeekbar.setProgress(30);
        controlbutton=(Button)findViewById(R.id.Controllerbutton);

        timerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
