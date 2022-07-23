package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    boolean timerWorking = false;
    Button button;
    CountDownTimer countDownTimer;
    TextView timer, textView2;
    public void resetTimer(){
        timer.setText("0.30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        button.setText("Start");
        timerWorking = false;
        //textView2.setVisibility(View.INVISIBLE);
    }
    public void startClicked(View view){

        if(timerWorking){
           resetTimer();
        }
        else{
            timerWorking = true;
            seekBar.setEnabled(false); //seekBar disables when timer starts, i.e, we cannot change the value one the timer is started
            button.setText("Stop");
            countDownTimer = new CountDownTimer(seekBar.getProgress()*1000 +100,1000) {
                // parameters passed in CountDownTimer- kaha se countdown shuru krna hai, kitne interval ka gap
                @Override
                public void onTick(long millisUntilFinished) {
                    // after passage of every second, we want to know that how much time is left
                    updateTimer((int)millisUntilFinished/1000); // to covert this into seconds
                }

                @Override
                public void onFinish() {

                    Toast.makeText(MainActivity.this, "TIME UP!!!!", Toast.LENGTH_SHORT).show();
                    //button.setText("Start");
                    resetTimer();
                }
            }.start();
        }
    }
    public void updateTimer(int i){
        int minutes = i/60;
        int seconds = i -(minutes*60);
        String secondString = Integer.toString(seconds);
        if(seconds <= 9){
            secondString = "0"+secondString;
        }
        timer.setText(Integer.toString(minutes)+":"+secondString);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (TextView)findViewById(R.id.timer);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        button = (Button)findViewById(R.id.button);

        seekBar.setMax(600);
        seekBar.setProgress(1000);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                updateTimer(i);  //i is basically the position of the seekbar
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