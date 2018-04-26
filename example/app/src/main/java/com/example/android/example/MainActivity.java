package com.example.android.example;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView image;
    private ImageView image2;
    private ArrayList<String> cards;
    private ArrayList<String> cards2;
    private Random rand;
    private TextView points;
    public int money= 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        points = findViewById(R.id.money);
        image = findViewById(R.id.cards);
        image2 = findViewById(R.id.cards2);
        cards = new ArrayList<String>();
        cards2 = new ArrayList<String>();
        rand = new Random();
        AssetManager manager = getAssets();
        try {
            for (String filename : manager.list("")) {
                cards.add(filename);
                cards2.add(filename);
            }
        } catch(IOException e ){
            Toast.makeText(this, "Bad Assets", Toast.LENGTH_LONG).show();
        }
    }
    public void imagePressed(View v) throws IOException{
        int r = rand.nextInt(cards.size());
        image.setImageBitmap(BitmapFactory.decodeStream(getAssets().open(cards.get(r))));
        int r2 = rand.nextInt(cards2.size());
        image2.setImageBitmap(BitmapFactory.decodeStream(getAssets().open(cards2.get(r2))));
        System.out.println(cards.get(r));


    }
    //public void imagePressed2(View v) throws IOException{
     //   int r = rand.nextInt(cards2.size());
      //  image2.setImageBitmap(BitmapFactory.decodeStream(getAssets().open(cards.get(r))));

//    }
}
