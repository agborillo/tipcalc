package com.example.android.example;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
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
    private EditText bet;
    private TextView points;
    public int money= 100;
    private int random1;
    private int random2;
    private RadioButton under;
    private RadioButton over;
    private Button menu;
    private RelativeLayout layout;
    private int color=0xFFFFFFFF;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.layout);
        menu = findViewById(R.id.menu);
        points = findViewById(R.id.money);
        bet = findViewById(R.id.bet);
        image = findViewById(R.id.cards);
        image2 = findViewById(R.id.cards2);
        under = findViewById(R.id.under);
        over = findViewById(R.id.over);
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
        String betS = bet.getText().toString();
        int betI = Integer.parseInt(betS);
        if(betI<=money) {
            random1 = rand.nextInt(cards.size());
            image.setImageBitmap(BitmapFactory.decodeStream(getAssets().open(cards.get(random1))));
            random2 = rand.nextInt(cards2.size());
            image2.setImageBitmap(BitmapFactory.decodeStream(getAssets().open(cards2.get(random2))));
            String card1 = cards.get(random1).toString();
            String card2 = cards2.get(random2).toString();
            int len = card1.length();
            int len2 = card2.length();
            System.out.println(len);
            System.out.println(len2);
            if (len > 6) {
                card1 = card1.substring(0, 2);
            } else {
                card1 = card1.substring(0, 1);
            }
            if (len2 > 6) {
                card2 = card2.substring(0, 2);
            } else {
                card2 = card2.substring(0, 1);
            }
            int Icard1 = Integer.parseInt(card1);
            int Icard2 = Integer.parseInt(card2);
            System.out.println(card1);
            System.out.println(card2);

            money = money - betI;
            if (under.isChecked()) {
                if (Icard1 == Icard2) {
                    Toast.makeText(this, "TIE! Points back", Toast.LENGTH_SHORT).show();
                    money = money + betI;
                    points.setText("Points: " + money);
                } else if (Icard1 > Icard2) {
                    Toast.makeText(this, "YOU LOSE! Points Lost", Toast.LENGTH_SHORT).show();
                    points.setText("Points: " + money);
                } else if (Icard1 < Icard2) {
                    Toast.makeText(this, "YOU WIN! Points Won", Toast.LENGTH_SHORT).show();
                    money = money + (betI * 2);
                    points.setText("Points: " + money);
                }
            }
            if (over.isChecked()) {
                if (Icard1 == Icard2) {
                    Toast.makeText(this, "TIE! Points back", Toast.LENGTH_SHORT).show();
                    money = money + betI;
                    points.setText("Points: " + money);
                } else if (Icard1 < Icard2) {
                    Toast.makeText(this, "YOU LOSE! Points Lost", Toast.LENGTH_SHORT).show();
                    points.setText("Points: " + money);
                } else if (Icard1 > Icard2) {
                    Toast.makeText(this, "YOU WIN! Points Won", Toast.LENGTH_SHORT).show();
                    money = money + (betI * 2);
                    points.setText("Points: " + money);
                }
            }
        }
        else{
            Toast.makeText(this, "Not enough points, bet less", Toast.LENGTH_SHORT).show();
        }

    }
    public void buttonPressed(View v) {
        Intent i = new Intent(this, MenuActivity.class);
        i.putExtra("points", money);
        i.putExtra("color", color);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        money = data.getIntExtra("points", 0);
        color = data.getIntExtra("color", 0xFFFFFF00);
        points.setText("Points: " + money);
        layout.setBackgroundColor(color);
    }

}
