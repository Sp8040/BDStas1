package ru.startandroid.bdstas1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        Timer timer = new Timer();
        TimerTask ts = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(StartScreen.this, SignUp.class);
                startActivity(intent);
            }
        };
        timer.schedule(ts, 3000L);
    }
}
