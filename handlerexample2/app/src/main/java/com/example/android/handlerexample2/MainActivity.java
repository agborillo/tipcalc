package com.example.android.handlerexample2;

import android.graphics.drawable.Drawable;
import android.hardware.SensorEventListener;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import java.util.Random;
import java.util.concurrent.Delayed;

import static java.lang.Math.pow;

public class MainActivity extends AppCompatActivity {

    private GridLayout grid;
    private SeekBar bar;
    private Handler handler;
    private Handler handler2;
    private Update update1;
    public int num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid = findViewById(R.id.grid);
        bar = findViewById(R.id.bar);
        handler = new Handler();
        update1 = new Update();
        handler2 = new Handler();

        bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        resetGrid();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
        handler.postDelayed(update1, 1000);


        for(int i=0; i<9; i++){
            ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.image_layout, grid, false);
            imageView.setImageDrawable(null);
            grid.addView(imageView);
        }


    }

    public void resetGrid(){
        grid.removeAllViews();
        int dimension = bar.getProgress()*3+3;
        grid.setColumnCount(dimension);
        grid.setRowCount(dimension);
        int size = dimension*dimension;
        for(int i=0; i<size; i++){
            ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.image_layout, grid, false);
            imageView.setImageDrawable(null);
            grid.addView(imageView);
        }
    }

    private class Update implements Runnable {

        @Override
        public void run() {
            ImageView i = (ImageView) grid.getChildAt(num);
            i.setImageDrawable(null);
            Drawable d = getResources().getDrawable(R.drawable.f45);
            int dimension = bar.getProgress()*3+3;
            int size = dimension*dimension;
            Random rand = new Random();
            num = rand.nextInt(size);
            ImageView i2 = (ImageView) grid.getChildAt(num);
            i2.setImageDrawable(d);
            handler.postDelayed(update1, 1000);
        }

    }
}


