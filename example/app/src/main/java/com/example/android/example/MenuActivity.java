package com.example.android.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
        private TextView text2;
        private int total;
        private int color;
        private RelativeLayout layout;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.menu);
            text2 = findViewById(R.id.text2);
            layout = findViewById(R.id.menu);
            total = getIntent().getIntExtra("points", 0);
            color = getIntent().getIntExtra("color", 0);
            text2.setText("Points: " + total);
            layout.setBackgroundColor(color);

        }

        public void redPressed(View v){
            total= total-50;
            text2.setText("Points: " + total);
            color = 0xFFFF0000;
            layout.setBackgroundColor(color);

        }
    public void bluePressed(View v){
        total= total-100;
        text2.setText("Points: " + total);
        color = 0xFF0000FF;
        layout.setBackgroundColor(color);

    }
        @Override
        public void onBackPressed(){
            Intent data = new Intent();
            data.putExtra("color", color);
            data.putExtra("points", total);
            setResult(RESULT_OK, data);
            finish();
        }


}
