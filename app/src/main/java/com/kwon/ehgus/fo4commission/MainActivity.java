package com.kwon.ehgus.fo4commission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.DecimalFormat;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {


    private EditText Saleamount, Fee, Self, Discount, Receive,origin;
    private Button Button1, Button2;
    private RadioGroup radioGroup;
    private double feeamount=0;
    private RadioButton fee_radio1, fee_radio2, fee_radio3, fee_radio4 ,fee_radio5, fee_radio6, fee_radio7;
    private LinearLayout lay;
    private DecimalFormat format = new DecimalFormat("###,###");//콤마
    private String Editresult="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Saleamount = (EditText) findViewById(R.id.Saleamount);
        Saleamount.addTextChangedListener(watcher);
        Fee = (EditText) findViewById(R.id.Fee);
        Self = (EditText) findViewById(R.id.Self);
        Discount = (EditText) findViewById(R.id.Discount);
        Receive = (EditText) findViewById(R.id.Receive);
        origin = (EditText) findViewById(R.id.origin);


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
        lay = (LinearLayout) findViewById(R.id.lay);

        //라디오 그룹 설정
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        //계산버튼
        Button1 = (Button) findViewById(R.id.Button1);
        Button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp1 = Saleamount.getText().toString().replaceAll("\\,","");;
                if (Saleamount.length() <= 0) {
                    Toast.makeText(MainActivity.this, "금액을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    //판매금액 받기
                    Integer original = (int) (Integer.parseInt(tmp1) - (Integer.parseInt(tmp1) * 0.3));
                    Integer result = (int) (Integer.parseInt(tmp1) * 0.3);
                    Integer tmp3 = (int) ((Integer.parseInt(tmp1) - (Integer.parseInt(tmp1) * 0.3)) + (Integer.parseInt(tmp1) * 0.3) * feeamount);
                    Integer tmp4 = (int) ((Integer.parseInt(tmp1) * 0.3) * feeamount);

                    //천단위 기호 변환환
                    long resultlong = Long.parseLong(String.valueOf(result));
                    long tmp3long = Long.parseLong(String.valueOf(tmp3));
                    long tmp4long = Long.parseLong(String.valueOf(tmp4));
                    long originallong = Long.parseLong(String.valueOf(original));



                    Fee.setText(format.format(resultlong));
                    Receive.setText(format.format(tmp3long));
                    Discount.setText("+" + format.format(tmp4long));
                    origin.setText(format.format(originallong));
                    System.out.println(feeamount);
                }
            }
        });

        Button2 = (Button) findViewById(R.id.Button2);
        Button2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( Self.length() <= 0 ) {
                    Toast.makeText(MainActivity.this, "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    String fee = Self.getText().toString();
                    Toast.makeText(MainActivity.this, "수수료 할인율 : " + fee + "%", Toast.LENGTH_SHORT).show();
                    feeamount = Double.parseDouble(fee) / 100;
                    if (fee == null) {
                        Toast.makeText(MainActivity.this, "할인율을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
//Edittext입력시 천단위기호 변환
    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(!TextUtils.isEmpty(charSequence.toString()) && !charSequence.toString().equals(Editresult)){
                Editresult = format.format(Double.parseDouble(charSequence.toString().replaceAll(",","")));
                Saleamount.setText(Editresult);
                Saleamount.setSelection(Editresult.length());
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };



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
                lay.setVisibility(VISIBLE);
            }

            if(fee_radio7.isChecked() == false) {
                lay.setVisibility(GONE);
            }
        }

    };
}
