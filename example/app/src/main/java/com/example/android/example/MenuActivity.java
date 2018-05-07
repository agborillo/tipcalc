package com.example.android.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
            if(total>=50){
                total= total-50;
                text2.setText("Points: " + total);
                color = 0xFFFF0000;
                layout.setBackgroundColor(color);
            }
            else{
                Toast.makeText(this, "Insufficent funds, MAKE MORE MONEY!", Toast.LENGTH_SHORT).show();
            }


        }
    public void bluePressed(View v){
            if(total>=100){
                total= total-100;
                text2.setText("Points: " + total);
                color = 0xFF0000FF;
                layout.setBackgroundColor(color);
            }
            else{
                Toast.makeText(this, "Insufficent funds, MAKE MORE MONEY!", Toast.LENGTH_SHORT).show();
            }


    }
    public void greenPressed(View v){
            if(total>=150){
                total= total-150;
                text2.setText("Points: " + total);
                color = 0xFF00FF00;
                layout.setBackgroundColor(color);
            }
            else{
                Toast.makeText(this, "Insufficent funds, MAKE MORE MONEY!", Toast.LENGTH_SHORT).show();
            }


    }
    public void suprisePressed(View v){
            if(total>=1000) {
                total = total - 1000;
                text2.setText("Points: " + total);
                color = 0xFF206050;
                layout.setBackgroundColor(color);
            }
            else{
                Toast.makeText(this, "Insufficent funds, MAKE MORE MONEY!", Toast.LENGTH_SHORT).show();
            }

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
