package com.example.android.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LoanActivity extends AppCompatActivity {
    private int total;
    private int color;
    private RelativeLayout layout1;
    private TextView points;
    int c=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loan);
        layout1= findViewById(R.id.loan);
        points = findViewById(R.id.points);
        total = getIntent().getIntExtra("points", 0);
        color = getIntent().getIntExtra("color", 0);
        layout1.setBackgroundColor(color);




    }

    public void loanPressed(View v){

        if(c==0){
            total+=20;
            points.setText("Points: " + total);
            c++;
        }
        else if(c>=1){
            Intent data = new Intent();
            data.putExtra("color", color);
            data.putExtra("points", total);
            setResult(RESULT_OK, data);
            finish();
        }
    }

    public void backgroundPressed(View v){
        if(color==0xFFFFFFFF){
            Toast.makeText(this, "No Background, Take a loan", Toast.LENGTH_SHORT).show();
        }
        else if(color==0xFFfff933){
            total+=50;
            points.setText("Points: " + total);
            color=0xFFFFFFFF;
            layout1.setBackgroundColor(color);
            Toast.makeText(this, "Sold Yellow, +50 Points", Toast.LENGTH_SHORT).show();
        }
        else if(color==0xFF33f6ff){
            total+=100;
            points.setText("Points: "+total);
            color=0xFFFFFFFF;
            layout1.setBackgroundColor(color);
            Toast.makeText(this, "Sold Blue, +100 Points", Toast.LENGTH_SHORT).show();
        }
        else if(color==0xFF7aff33){
            total+=150;
            points.setText("Points: "+total);
            color=0xFFFFFFFF;
            layout1.setBackgroundColor(color);
            Toast.makeText(this, "Sold Green, +150 Points", Toast.LENGTH_SHORT).show();
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
