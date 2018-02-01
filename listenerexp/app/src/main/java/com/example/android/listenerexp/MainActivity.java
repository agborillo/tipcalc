package com.example.android.listenerexp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView bar;
    private SeekBar seek;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = findViewById(R.id.bar);
        seek = findViewById(R.id.seek);
        input = findViewById(R.id.input);
        input.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        String data = input.getText().toString();
                        int progress = Integer.parseInt(data);
                        seek.setProgress(progress);
                        return false;
                    }
                }
        );
        seek.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        bar.setText(i + "");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );

    }
    public void buttonPressed(View v){
        int progress = seek.getProgress();
        Toast.makeText(context: this, "Progress : "+progress, Toast.LENGTH_SHORT).show();

    }

    }



}
