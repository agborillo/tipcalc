package com.example.android.carloan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText cost;
    private EditText downpayment;
    private EditText apr;
    private RadioButton lease;
    private RadioButton loan;
    private SeekBar seekBar;
    private TextView payment;
    private TextView months;
    private EditText payout;
    private RadioGroup buttons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cost = findViewById(R.id.costinput);
        downpayment = findViewById(R.id.downinput);
        apr = findViewById(R.id.aprinput);
        lease = findViewById(R.id.lease);
        loan = findViewById(R.id.loan);
        seekBar = findViewById(R.id.seek);
        payment = findViewById(R.id.paymentout);
        months = findViewById(R.id.months);
      //  payout = findViewById(R.id.outpay);
       buttons = findViewById(R.id.buttons);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        months.setText("Length of Loan: " + i + " months");
                        buttonPressed(seekBar);
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar){

                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
        apr.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        buttonPressed(textView);
                        return false;
                    }
                }
        );
        buttons.setOnCheckedChangeListener(

                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            buttonPressed(radioGroup);
                    }
                }
        );
        if(savedInstanceState != null){
            cost.setText(savedInstanceState.getString( "carcost"));
            downpayment.setText(savedInstanceState.getString( "downpay"));
            apr.setText(savedInstanceState.getString( "apr"));
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("carcost", cost.getText().toString() );
        savedInstanceState.putString("downpay", downpayment.getText().toString() );
        savedInstanceState.putString("apr", apr.getText().toString() );
    }
    public void buttonPressed(View v){
            double months = seekBar.getProgress();
            String carcost =  cost.getText().toString();
            double CC = Double.parseDouble(carcost);
            String downpay = downpayment.getText().toString();
            double dp = Double.parseDouble(downpay);
            String ap = apr.getText().toString();
            double apr2 = Double.parseDouble(ap);
            apr2=apr2/100;
            double total=0;
            double mr;
            months = months*-1;
            if(lease.isChecked()){
                double loan3 = CC/3;
                loan3=loan3-dp;
                mr = apr2/12;
                total = mr*loan3/(1-(Math.pow((1+mr), (-36))));

            }
            else if(loan.isChecked()){
                double loan2 = CC-dp;
                mr = apr2/12;
                total = mr*loan2/(1-(Math.pow((1+mr), (months))));
            }
        payment.setText("Monthly Payment: $"+String.format("%.2f", total)+"");
    }

}



