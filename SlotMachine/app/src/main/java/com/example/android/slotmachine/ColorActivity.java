package com.example.android.slotmachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

/**
 * Created by android on 3/20/18.
 */

public class ColorActivity extends AppCompatActivity {

    private int color;
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        group = findViewById(R.id.group);
        color = 0xFF000000;

        group.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i){
                            case R.id.whitebutton:
                                color = 0xFFFFFFFF; break;
                            case R.id.cyanbutton:
                                color = 0xFF00FFFF; break;
                            case R.id.yellowbutton:
                                color = 0xFFFFFF00; break;
                        }
                    }
                }
        );

    }
    @Override
    public void onBackPressed(){
        Intent data = new Intent();
        data.putExtra("COLOR", color);
        setResult(RESULT_OK, data);
        finish();
    }

}
