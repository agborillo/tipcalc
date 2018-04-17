package com.example.android.example;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView image;
    private ArrayList<String> cards;
    private Random rand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.image);
        cards = new ArrayList<String>();
        rand = new Random();
        AssetManager manager = getAssets();
        try {
            for (String filename : manager.list("")) {
                cards.add(filename);
            }
        } catch(IOException e ){
            Toast.makeText(this, "Bad Assets", Toast.LENGTH_LONG).show();
        }
    }
    public void imagePressed(View v) throws IOException{
        int r = rand.nextInt(cards.size());
        image.setImageBitmap(BitmapFactory.decodeStream(getAssets().open(cards.get(r))));

    }
}
