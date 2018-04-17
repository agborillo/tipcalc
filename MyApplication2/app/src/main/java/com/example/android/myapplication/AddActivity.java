package com.example.android.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by android on 4/12/18.
 */

public class AddActivity extends AppCompatActivity {
    private String movieadd;
    private String movieid;
    private EditText add;
    private EditText idadd;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmovie);
        add = findViewById(R.id.add);
        idadd = findViewById(R.id.idadd);

    }

    public void Buttonpressed(View v){
        movieadd = add.getText().toString();
        movieid = idadd.getText().toString();
        System.out.println("========" + movieadd);
        System.out.println("========" + movieid);
        Intent data = new Intent();
        data.putExtra("movie", movieadd);
        data.putExtra("id", movieid);
        setResult(RESULT_OK, data);
        finish();
    }
}