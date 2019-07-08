package com.kwon.ehgus.fo4commission;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Saleamount, Fee, Self, Discount, Receive;
    private Button Button1, Button2;
    private RadioGroup radioGroup;
    private double feeamount=0;
    private RadioButton fee_radio1, fee_radio2, fee_radio3, fee_radio4 ,fee_radio5, fee_radio6, fee_radio7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //입력부분설정
        Saleamount = (EditText) findViewById(R.id.Saleamount);
        Fee = (EditText) findViewById(R.id.Fee);
        Self = (EditText) findViewById(R.id.Self);
        Discount = (EditText) findViewById(R.id.Discount);
        Receive = (EditText) findViewById(R.id.Receive);


        //라디오 버튼 설정
        fee_radio1 = (RadioButton) findViewById(R.id.fee_radio1);
        fee_radio2 = (RadioButton) findViewById(R.id.fee_radio2);
        fee_radio3 = (RadioButton) findViewById(R.id.fee_radio3);
        fee_radio4 = (RadioButton) findViewById(R.id.fee_radio4);
        fee_radio5 = (RadioButton) findViewById(R.id.fee_radio5);
        fee_radio6 = (RadioButton) findViewById(R.id.fee_radio6);
        fee_radio7 = (RadioButton) findViewById(R.id.fee_radio7);
        fee_radio1.setOnClickListener(radioButtonClickListener);
        fee_radio2.setOnClickListener(radioButtonClickListener);
        fee_radio3.setOnClickListener(radioButtonClickListener);
        fee_radio4.setOnClickListener(radioButtonClickListener);
        fee_radio5.setOnClickListener(radioButtonClickListener);
        fee_radio6.setOnClickListener(radioButtonClickListener);
        fee_radio7.setOnClickListener(radioButtonClickListener);

        //라디오 그룹 설정
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

            //계산버튼
            Button1 = (Button) findViewById(R.id.Button1);
            Button1.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String tmp1 = Saleamount.getText().toString();
                    if (Saleamount.length() <= 0) {
                        Toast.makeText(MainActivity.this, "금액을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        //판매금액 받기
                        Integer result = (int) (Integer.parseInt(tmp1) * 0.3);
                        Integer tmp3 = (int) ((Integer.parseInt(tmp1) - (Integer.parseInt(tmp1) * 0.3)) + (Integer.parseInt(tmp1) * 0.3) * feeamount);
                        Integer tmp4 = (int) ((Integer.parseInt(tmp1) * 0.3) * feeamount);
                        Fee.setText(result.toString());
                        Receive.setText(tmp3.toString());
                        Discount.setText("+" + tmp4.toString());
                        System.out.println(feeamount);
                    }
                }
            });
        }

    //라디오 버튼 클릭 리스너
    RadioButton.OnClickListener radioButtonClickListener = new RadioButton.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (fee_radio1.isChecked()) {
                feeamount = 0;
            } else if(fee_radio2.isChecked()) {
                feeamount = 0.3;
            }else if(fee_radio3.isChecked()) {
                feeamount = 0.4;
            }else if(fee_radio4.isChecked()) {
                feeamount = 0.5;
            }else if(fee_radio5.isChecked()) {
                feeamount = 0.6;
            }else if(fee_radio6.isChecked()) {
                feeamount = 0.7;
            }else if(fee_radio7.isChecked()) {
                LinearLayout lay = (LinearLayout) findViewById(R.id.lay);
                lay.setVisibility(View.VISIBLE);
                Button2 = (Button) findViewById(R.id.Button2);
                Button2.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if ( Self.length() <= 0 ) {
                            Toast.makeText(MainActivity.this, "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
                        }else{
                        String fee = Self.getText().toString();
                        feeamount = Double.parseDouble(fee) / 100;
                        }
                    }
                });
            }
        }

    };





}
