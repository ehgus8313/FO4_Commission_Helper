package com.kwon.ehgus.fo4commission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    private EditText Saleamount, Fee, Self, Discount, Receive,origin, CouponFee;
    private double feeamount=0;
    private CheckBox fee_checkbox2, fee_checkbox3, fee_checkbox4;
    private final DecimalFormat format = new DecimalFormat("###,###");//콤마
    private String Editresult="";
    private String Coupon_Editresult="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Saleamount = (EditText) findViewById(R.id.Saleamount);
        Saleamount.addTextChangedListener(watcher);

        Fee = (EditText) findViewById(R.id.Fee);

        CouponFee = (EditText) findViewById(R.id.CouponFee);
        CouponFee.addTextChangedListener(couponfee_watcher);

        Discount = (EditText) findViewById(R.id.Discount);

        Receive = (EditText) findViewById(R.id.Receive);

        origin = (EditText) findViewById(R.id.origin);


        //라디오 버튼 설정
        fee_checkbox2 = findViewById(R.id.fee_radio2);
        fee_checkbox3 = findViewById(R.id.fee_radio3);
        fee_checkbox4 = findViewById(R.id.fee_radio4);

        fee_checkbox2.setOnClickListener(checkBoxClickListener);
        fee_checkbox3.setOnClickListener(checkBoxClickListener);
        fee_checkbox4.setOnClickListener(checkBoxClickListener);
    }

    //Edittext입력시 천단위기호 변환
    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(!TextUtils.isEmpty(charSequence.toString()) && !charSequence.toString().equals(Editresult)){
                Editresult = format.format(Long.parseLong(charSequence.toString().replaceAll(",","")));
                Saleamount.setText(Editresult);
                Saleamount.setSelection(Editresult.length());

                try {
                    String tmp1 = Saleamount.getText().toString().replaceAll("\\,","");
                    if (Long.parseLong(tmp1) >= 1000) {
                        Calculate();
                    }
                } catch (Exception ignored) {

                }

            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher couponfee_watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(!TextUtils.isEmpty(charSequence.toString()) && !charSequence.toString().equals(Coupon_Editresult)){
                Coupon_Editresult = format.format(Long.parseLong(charSequence.toString().replaceAll(",","")));
                CouponFee.setText(Coupon_Editresult);
                CouponFee.setSelection(Coupon_Editresult.length());

                try {
                    String tmp1 = CouponFee.getText().toString().replaceAll("\\,","");
                    if (!tmp1.isEmpty() && Long.parseLong(tmp1) >= 1) {
                        feeamount = 0;
                        if(fee_checkbox2.isChecked()) {
                            feeamount += 0.2;
                        }
                        if(fee_checkbox3.isChecked()) {
                            feeamount += 0.3;
                        }
                        double tmp = (double) Long.parseLong(tmp1) * 0.01;
                        feeamount += tmp;
                        Calculate();
                    }
                } catch (Exception ignored) {

                }

            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };



    //라디오 버튼 클릭 리스너
    CheckBox.OnClickListener checkBoxClickListener = new CheckBox.OnClickListener() {
        @Override
        public void onClick(View view) {
            feeamount = 0;
            if(fee_checkbox2.isChecked()) {
                feeamount += 0.2;
            }
            if(fee_checkbox3.isChecked()) {
                feeamount += 0.3;
            }
            if(fee_checkbox4.isChecked()) {
                CouponFee.setEnabled(true);
                String tmp1 = CouponFee.getText().toString().replaceAll("\\,","");
                if (!tmp1.isEmpty() && Long.parseLong(tmp1) >= 1) {
                    double tmp = (double) Long.parseLong(tmp1) * 0.01;
                    feeamount += tmp;
                    Calculate();
                }
            } else {
                CouponFee.setEnabled(false);
                CouponFee.setText("");
            }
            try {
                String tmp1 = Saleamount.getText().toString().replaceAll("\\,","");
                if (Long.parseLong(tmp1) >= 1000) {
                    Calculate();
                }
            } catch (Exception ignored) {

            }
        }

    };

    void Calculate() {
        String tmp1 = Saleamount.getText().toString().replaceAll("\\,","");
        //판매금액 받기

        long result = (long) (Long.parseLong(tmp1) * 0.4);
        long original = (long) (Long.parseLong(tmp1) - result);
        long tmp3 = (long) ((Long.parseLong(tmp1) - result) + result * feeamount);
        long tmp4 = (long) (result * feeamount);

        //천단위 기호 변환
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
