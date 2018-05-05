package com.example.android.slotmachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by android on 3/20/18.
 */

public class ColorActivity extends AppCompatActivity {

    private int color;
    private RadioGroup group;
    private TextView points2;
    int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        color = 0xFFFF0000;
        points2 = findViewById(R.id.points2);
        int total  = getIntent().getIntExtra("Points", 0);
        points2.setText("Points: " + total);
    }
    @Override
    public void onBackPressed(){
        Intent data = new Intent();
        data.putExtra("COLOR", color);
        setResult(RESULT_OK, data);
        finish();
    }

}
