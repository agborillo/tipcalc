package com.example.android.tipcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText inputbill;
    private EditText diners;
    private TextView outputtip;
    private TextView outputbill;
    private RadioButton divide;
    private SeekBar tipPercent;
    private TextView showTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputbill = findViewById(R.id.inputbill);
        diners = findViewById(R.id.diners2);
        outputtip =  findViewById(R.id.tipout);
        outputbill = findViewById(R.id.totalbill);
        divide = findViewById(R.id.check);
        tipPercent = findViewById((R.id.seekbar));
        showTip = findViewById(R.id.percent);
        tipPercent.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        showTip.setText(i+"%");
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );
    }

    public void buttonPressed(View v){
        //String percent = showTip.getText().toString();
        double tippercent=tipPercent.getProgress();
        double result;
        double tiptotal;
        String bill = inputbill.getText().toString();
        double bill1 = Double.parseDouble(bill);
        //find percent of tip
        tiptotal = bill1*(tippercent/100);
        result=tiptotal+bill1;
        String diners1= diners.getText().toString();
        double l = Double.parseDouble(diners1);
        //String input = outputbill.getText().toString();
        //double t = Double.parseDouble(input);
        if(divide.isChecked()){
            result=result/l;
        }
        outputtip.setText("Tip: $"+tiptotal+"");
        outputbill.setText("You owe: $"+result+"");
    }
}
