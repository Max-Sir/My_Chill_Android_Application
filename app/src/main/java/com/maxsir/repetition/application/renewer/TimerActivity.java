package com.maxsir.repetition.application.renewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class TimerActivity extends AppCompatActivity {

    private int seconds=0;
    private boolean running;
    private boolean playingMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            runTimerService();
        }
        setContentView(R.layout.activity_timer);
    }


    private boolean isRunning(){
        return running;
    }
    public void onStart(View view) {
        running=true;
        runTimerService();
    }

    public void onReset(View view) {
        seconds=0;
        running=false;
        handler.removeCallbacksAndMessages(null);
        TextView tv=findViewById(R.id.timerView);
        tv.setText(R.string.nullStopwatch);
    }

    public void onStop(View view) {
        running=false;
        handler.removeCallbacksAndMessages(null);
    }

    final Handler handler=new Handler();

    private void runTimerService(){

        TextView tv= findViewById(R.id.timerView);

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int minutes=(seconds%3600)/60;
                int secondsIsOnTimer=seconds%60;

                String time=String.format(Locale.getDefault(),"%02d:%02d:%02d",hours,minutes,secondsIsOnTimer);
                tv.setText(time);

                if(running){
                    seconds++;
                }

                if(seconds%15==0){
                    //MediaPlayer mp=MediaPlayer.create(R.raw.file);
                }

                handler.postDelayed(this,1000);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds",seconds);
        outState.putBoolean("running",running);
    }
}