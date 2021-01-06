package com.example.shoppingapp.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingapp.R;
import com.example.shoppingapp.model.Guest;
import com.example.shoppingapp.viewmodel.ItemViewModel;

import java.util.List;

public class GuestInfoActivity extends AppCompatActivity {

    ItemViewModel itemViewModel;
    ActionBar actionBar;
    EditText edtName,edtPhone,edtEmail;
    Button btnConfirm, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_info);

        // Ánh xạ
        Initialize();

        // Hide actionbar
        getSupportActionBar().hide();

        // View model
        itemViewModel = ViewModelProviders.of(GuestInfoActivity.this).get(ItemViewModel.class);

        // Set confirm button
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String guestname = edtName.getText().toString().trim();
               String phone = edtPhone.getText().toString().trim();
               String guestemail = edtEmail.getText().toString().trim();
               if (guestname.isEmpty() || guestemail.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(
                            GuestInfoActivity.this,
                            "Bạn chưa nhập đủ thông tin",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
               }
               Integer guestphone = Integer.parseInt(phone);
               itemViewModel.insertGuest(guestname,guestphone,guestemail);
               Toast.makeText(getApplicationContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(getApplicationContext(),MainActivity.class);
               startActivity(intent);
               MainActivity.paymentArrayList.clear();
            }
        });

        // Set Back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Initialize() {
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnBack = findViewById(R.id.btnBack);
    }
}