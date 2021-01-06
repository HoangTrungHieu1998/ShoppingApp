package com.example.shoppingapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingapp.R;
import com.example.shoppingapp.model.Payment;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    Spinner spinner;
    ActionBar actionBar;
    ImageView mImgDetail;
    TextView mTxtName,mTxtPrice,mTxtDescribe;
    Button mBtnBuy;
    String mImage,mName,mDescribe = "";
    Integer mID;
    Integer mPrice =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Ánh xạ
        mImgDetail = findViewById(R.id.imgDetail);
        mTxtPrice = findViewById(R.id.txtPrice);
        mTxtName = findViewById(R.id.txtName);
        mTxtDescribe = findViewById(R.id.txtDescribe);
        mBtnBuy = findViewById(R.id.btnBuy);
        spinner = findViewById(R.id.spinner);

        // Set Actionbar
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Chi tiết");

        // Spinner
        EvenSpinner();

        // Get intent
        Intent intent = getIntent();
        if(intent!=null){
            mImage = intent.getStringExtra("image");
            mPrice = intent.getIntExtra("price",0);
            mName = intent.getStringExtra("name");
            mDescribe = intent.getStringExtra("describe");
            mID = intent.getIntExtra("id",0);

            // Set View
            Picasso.with(getApplicationContext()).load(mImage)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.drawable.pokemon)
                    .into(mImgDetail);
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            mTxtPrice.setText(decimalFormat.format(mPrice)+" Đ");
            mTxtName.setText(mName);
            mTxtDescribe.setText(mDescribe);
        }

        // Set Button
        mBtnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.paymentArrayList.size()>0){
                    int num1 = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exist = false;
                    for(int i =0; i<MainActivity.paymentArrayList.size();i++){
                        if(MainActivity.paymentArrayList.get(i).getItemID()==mID){
                            MainActivity.paymentArrayList.get(i).setItemNumber(MainActivity.paymentArrayList.get(i).getItemNumber()+num1);
                            if(MainActivity.paymentArrayList.get(i).getItemNumber()>=10){
                                MainActivity.paymentArrayList.get(i).setItemNumber(10);
                            }
                            MainActivity.paymentArrayList.get(i).setPrice(mPrice*MainActivity.paymentArrayList.get(i).getItemNumber());
                            exist = true;
                        }
                    }
                    if (exist == false){
                        int num = Integer.parseInt(spinner.getSelectedItem().toString());
                        int newPrice = num * mPrice;
                        MainActivity.paymentArrayList.add(new Payment(mName,mImage,mID,num,newPrice));
                    }
                }
                else {
                    int num = Integer.parseInt(spinner.getSelectedItem().toString());
                    int newPrice = num * mPrice;
                    MainActivity.paymentArrayList.add(new Payment(mName,mImage,mID,num,newPrice));
                }
                Intent intent1 = new Intent(getApplicationContext(),PaymentActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void EvenSpinner() {
        Integer[] number = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,number);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}