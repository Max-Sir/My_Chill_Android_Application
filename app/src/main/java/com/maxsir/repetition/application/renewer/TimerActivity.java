package com.maxsir.repetition.application.renewer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
//import android.app.Instrumentation;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class TimerActivity extends AppCompatActivity {

    private boolean started;
    /**
     * @param started
     *      used by methods to understand even this started by method onStart();
     */
    private int milliseconds =0;
    private boolean running;
    private boolean playingMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            milliseconds =savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            runTimerService();
        }
        setContentView(R.layout.activity_timer);
    }

    private boolean isStarted(){
        return started;
    }

    private boolean isRunning(){
        return running;
    }
    public void onStart(View view) {
        if(isRunning()||isStarted()){
            return;
        }
        started=true;
        running=true;
        handler.removeCallbacksAndMessages(null);
        runTimerService();
    }

    public void onReset(View view) {
        milliseconds =0;
        started=false;
        running=false;
        handler.removeCallbacksAndMessages(null);
        TextView tv=findViewById(R.id.timerView);
        tv.setText(R.string.nullStopwatch);
    }

    public void onStop(View view) {
        Button btn = findViewById(R.id.stop);
        if(isStarted()) {
            running = false;
            started= false;
            btn.setText(getString(R.string.resume));
            handler.removeCallbacksAndMessages(null);
        }
        else {
            started=true;
            running=true;
            btn.setText(getString(R.string.stop));
            runTimerService();
        }


    }

    final Handler handler=new Handler();

    private void runTimerService(){

        TextView tv= findViewById(R.id.timerView);

        handler.post(new Runnable() {
            @Override
            public void run() {
                int seconds= milliseconds /100;
                int hours= seconds /3600;
                int minutes=(seconds %3600)/60;
                int secondsIsOnTimer= seconds%60;
                int ms= milliseconds %1_00;


                String time=String.format(Locale.getDefault(),"%02d:%02d:%02d",hours,minutes,secondsIsOnTimer);
                @SuppressLint("DefaultLocale") String displayedTime=time+String.format(".%02d",ms%100);
                tv.setText(displayedTime);

                if(running){
                    milliseconds++;
                }

                if(milliseconds %200==0){
                    Log.d("Music started at ",time);
                    //MediaPlayer mp=MediaPlayer.create(R.raw.file);
                }


                handler.postDelayed(this,1);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", milliseconds);
        outState.putBoolean("running",running);
       // Instrumentation instrumentation=new Instrumentation();
    }
}
