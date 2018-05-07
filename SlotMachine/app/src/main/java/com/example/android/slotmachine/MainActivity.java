package com.example.android.slotmachine;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Random;
public class MainActivity extends AppCompatActivity {
  private GridLayout grid;
  private Button start;
  private Button rules;
  private SeekBar bar;
  private TextView points;
  private Handler handler;
  private Handler handler2;
  private Update update1;
  private Update update2;
  private Update update3;
  private RelativeLayout layout;
  int num1=1000;
  int num2=1000;
  int num3=1000;
  int total=0;
  Drawable d;
  Drawable d2;
  Drawable d3;
  Drawable d4;
  int x=0;
  int y=1;
  int z=2;
  int speed=500;
  int click = 0;
  int place1 = 0;
  int place2 = 1;
  int place3 = 2;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    grid = findViewById(R.id.grid);
    start = findViewById(R.id.start);
    rules = findViewById(R.id.rules);
    bar = findViewById(R.id.bar);
    points = findViewById(R.id.points);
    handler = new Handler();
    update1 = new Update();
    update2 = new Update();
    update3 = new Update();
    handler2 = new Handler();
    layout = findViewById(R.id.layout);
    layout.setBackgroundColor(0xFF00FF5F);
    bar.setOnSeekBarChangeListener(
            new SeekBar.OnSeekBarChangeListener() {
              @Override
              public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(bar.getProgress()==0){
                  speed=500;
                }
                else if(bar.getProgress()==1){
                  speed=200;
                }
                else if(bar.getProgress()==2){
                  speed=0;
                }
              }
              @Override
              public void onStartTrackingTouch(SeekBar seekBar) {
              }
              @Override
              public void onStopTrackingTouch(SeekBar seekBar) {
              }
            }
    );

    ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.imagevie, grid, false);
    Drawable d = getResources().getDrawable(R.drawable.pear);
    imageView.setImageDrawable(d);
    grid.addView(imageView);
    ImageView imageView2 = (ImageView) getLayoutInflater().inflate(R.layout.imagevie, grid, false);
    Drawable d2 = getResources().getDrawable(R.drawable.grape);
    imageView2.setImageDrawable(d2);
    grid.addView(imageView2);
    ImageView imageView3 = (ImageView) getLayoutInflater().inflate(R.layout.imagevie, grid, false);
    Drawable d3 = getResources().getDrawable(R.drawable.strawberry);
    imageView3.setImageDrawable(d3);
    grid.addView(imageView3);

    if(savedInstanceState != null){
      points.setText(savedInstanceState.getString( "points"));
      total = savedInstanceState.getInt("total");
    }
  }
  @Override
  public void onSaveInstanceState(Bundle savedInstanceState){
    super.onSaveInstanceState(savedInstanceState);
    savedInstanceState.putString("points", points.getText().toString() );
    savedInstanceState.putInt("total", total);
    savedInstanceState.putInt("image1", R.drawable.cherry);
    savedInstanceState.putInt("image2", R.drawable.strawberry);
    savedInstanceState.putInt("image3", R.drawable.grape);
    savedInstanceState.putInt("image4", R.drawable.pear);
  }

  public void buttonPressed(View v){
    if(start.getText().equals("Start")){
      d = getResources().getDrawable(R.drawable.strawberry);
      d2 = getResources().getDrawable(R.drawable.cherry);
      d3 = getResources().getDrawable(R.drawable.grape);
      d4 = getResources().getDrawable(R.drawable.pear);
      handler.postDelayed(update1, num1);
      start.setText("Stop");
    }
    else if(start.getText().equals("Stop")){
      handler.removeCallbacks(update1);
      ImageView q = (ImageView) grid.getChildAt(place1);
      ImageView q2 = (ImageView) grid.getChildAt(place2);
      ImageView q3 = (ImageView) grid.getChildAt(place3);
      Drawable f = q.getDrawable();
      Drawable f2 = q2.getDrawable();
      Drawable f3 = q3.getDrawable();
      if(f.equals(d) && f2.equals(d) && f3.equals(d)){
        total +=80;
        points.setText("Points: "+ total);
        Toast.makeText(this, "+80 Points!", Toast.LENGTH_SHORT).show();
      }
      else if((f.equals(d) && f2.equals(d))){
        total +=20;
        points.setText("Points: "+ total);
        Toast.makeText(this, "+20 Points!", Toast.LENGTH_SHORT).show();
      }
      else if((f2.equals(d) && f3.equals(d))){
        total +=20;
        points.setText("Points: "+ total);
        Toast.makeText(this, "+20 Points!", Toast.LENGTH_SHORT).show();
      }
      else if(f.equals(d) && f3.equals(d)){
        total +=20;
        points.setText("Points: "+ total);
        Toast.makeText(this, "+20 Points!", Toast.LENGTH_SHORT).show();
      }
      else if(f.equals(d2) && f2.equals(d2) && f3.equals(d2)){
        total += 50;
        points.setText("Points: "+ total);
        Toast.makeText(this, "+50 Points!", Toast.LENGTH_SHORT).show();
      }
      else if(f.equals(d4) && f2.equals(d4) && f3.equals(d4)){
        total +=50;
        points.setText("Points: "+ total);
        Toast.makeText(this, "+50 Points!", Toast.LENGTH_SHORT).show();
      }
      else if(f.equals(d3) && f2.equals(d3) && f3.equals(d3)) {
        total += 50;
        points.setText("Points: " + total);
        Toast.makeText(this, "+50 Points!", Toast.LENGTH_SHORT).show();
      }
      else if(f.equals(d) || f2.equals(d) || f3.equals(d)) {
        total += 10;
        points.setText("Points: "+ total);
        Toast.makeText(this, "+10 Points!", Toast.LENGTH_SHORT).show();
      }

      start.setText("Start");
    }
  }
  public void buttonPressed2(View v) {
    Intent i = new Intent(this, ColorActivity.class);
    i.putExtra("Points", total);
    startActivity(i);
  }
  private class Update implements Runnable {
    @Override
    public void run() {
      click++;
      if(click==4){
        click = 1; }
      Random rand = new Random();
      num1= rand.nextInt((200)+speed);
      Random rand2 = new Random();
      num2= rand2.nextInt((200)+speed);
      Random rand3 = new Random();
      num3= rand3.nextInt((200)+speed);
      int place1 = 0;
      int place2 = 1;
      int place3 = 2;
      Drawable[] a = new  Drawable[4];
      ImageView i = (ImageView) grid.getChildAt(place1);
      ImageView i2 = (ImageView) grid.getChildAt(place2);
      ImageView i3 = (ImageView) grid.getChildAt(place3);
      a[0] = d;
      a[1] = d2;
      a[2] = d3;
      a[3] = d4;
      if(click == 1){
        i.setImageDrawable(a[x]);
        handler.postDelayed(update1, num1); }
      else if(click == 2){
        i2.setImageDrawable(a[y]);
        handler.postDelayed(update1, num2); }
      else if(click ==3){
        i3.setImageDrawable(a[z]);
        handler.postDelayed(update1, num3); }
      x++;
      y++;
      z++;
      if(x==4){
        x=x-4; }
      if(y==4){
        y=y-4; }
      if(z==4){
        z=z-4; }

    }

  }

}
