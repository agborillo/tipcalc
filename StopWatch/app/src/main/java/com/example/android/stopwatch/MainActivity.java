package com.example.android.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView timer;
    private Handler handler;
    private Update update1;
    private long startTime;
    private Button start;
    private Button stop;
    private Button reset;
    private long last;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = findViewById(R.id.timer);
        start = findViewById(R.id.button2);
        stop = findViewById(R.id.button);
        reset = findViewById(R.id.button3);
        handler = new Handler();
        update1 = new Update();
        startTime =System.currentTimeMillis();
        last = 0;

    }

    public void buttonPressed(View v) {
        if (start.isPressed()) {
            handler.postDelayed(update1, 0);

        } else if (stop.isPressed()) {
            handler.removeCallbacks(update1);
            last = Integer.parseInt(timer.getText().toString());
        }
        if (reset.isPressed()) {
            startTime=System.currentTimeMillis();
        }

    }

    private class Update implements Runnable {

        @Override
        public void run() {
            long current = System.currentTimeMillis();
            long elapsed = (current - startTime) / 1000;
            timer.setText("" + elapsed);
            handler.postDelayed(update1, 1000);
            if (elapsed == 10) {
                timer.setText("10");
            }

        }
    }
}
