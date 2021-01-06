package com.example.shoppingapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingapp.R;
import com.example.shoppingapp.adapter.PaymentAdapter;

import java.text.DecimalFormat;

public class PaymentActivity extends AppCompatActivity {

    ActionBar actionBar;
    RecyclerView mRcv;
    PaymentAdapter mAdapter;
    static TextView txtTotal;
    Button mPayment,mContinue;
    TextView mTxtShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Ánh xạ
        txtTotal = findViewById(R.id.txtTotal);
        mRcv = findViewById(R.id.recycleViewPayment);
        mPayment = findViewById(R.id.btnPayment);
        mContinue = findViewById(R.id.btnContinue);
        mTxtShow = findViewById(R.id.txtShow);

        // Set Adapter
        mAdapter = new PaymentAdapter(getApplicationContext(),MainActivity.paymentArrayList);
        mRcv.setAdapter(mAdapter);

        // Set Actionbar
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Giỏ hàng");

        // Set Total Price
        Total();

        // Check data
        CheckData();

        // Set Continue button
        mContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        mAdapter.setOnPaymentListener(new OnPaymentListener() {
            @Override
            public void OnLongClick(int position) {
                Context context;
                AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
                builder.setTitle("Xác nhận xoá")
                        .setMessage("Bạn có muốn xoá ?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(MainActivity.paymentArrayList.size()<=0){
                                    mTxtShow.setVisibility(View.VISIBLE);
                                }else {
                                    MainActivity.paymentArrayList.remove(position);
                                    mAdapter.notifyDataSetChanged();
                                    Total();
                                    if(MainActivity.paymentArrayList.size()<=0){
                                        mTxtShow.setVisibility(View.VISIBLE);
                                    }else{
                                        mTxtShow.setVisibility(View.INVISIBLE);
                                        mAdapter.notifyDataSetChanged();
                                        Total();
                                    }
                                }

                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        // Set Payment button
        mPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.paymentArrayList.size()<=0){
                    Toast.makeText(PaymentActivity.this, "Giỏ hàng trống, không thể thanh toán", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getApplicationContext(),GuestInfoActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    private void CheckData() {
        if(MainActivity.paymentArrayList.size()<=0){
            mAdapter.notifyDataSetChanged();
            mTxtShow.setVisibility(View.VISIBLE);
            mRcv.setVisibility(View.INVISIBLE);
        }else {
            mAdapter.notifyDataSetChanged();
            mTxtShow.setVisibility(View.INVISIBLE);
            mRcv.setVisibility(View.VISIBLE);
        }
    }


    public static void Total() {
        long total = 0;
        for (int i = 0; i <MainActivity.paymentArrayList.size() ; i++) {
            total += MainActivity.paymentArrayList.get(i).getPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTotal.setText("Tổng tiền: "+decimalFormat.format(total)+" Đ");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}