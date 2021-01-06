package com.example.shoppingapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.shoppingapp.R;
import com.example.shoppingapp.adapter.ItemAdapter;
import com.example.shoppingapp.model.Item;
import com.example.shoppingapp.model.Payment;
import com.example.shoppingapp.viewmodel.ItemViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mActionBar;
    NavigationView navigationView;
    public static ArrayList<Payment> paymentArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        Home home = new Home();
        fragmentManager.beginTransaction().add(R.id.frameLayout,home).commit();

        // Ánh xạ
        Initialize();

        // Cài đặt Drawer Layout
        SetupDrawer();

        // Set navigation click
        Even();

    }

    private void Initialize() {
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navView);
        frameLayout = findViewById(R.id.frameLayout);

        // Get data if array have item
        if(paymentArrayList != null){
            // Do nothing if array have item
        }else{
            paymentArrayList = new ArrayList<>();
        }
    }

    private void SetupDrawer() {
        mActionBar = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(mActionBar);
        mActionBar.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Trang chính");
    }

    private void Even() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (item.getItemId()){
                    case R.id.main:
                        fragmentTransaction.replace(R.id.frameLayout,new Home()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        getSupportActionBar().setTitle("Trang chính");
                        return true;
                    case R.id.phone:
                        fragmentTransaction.replace(R.id.frameLayout,new Phone()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        getSupportActionBar().setTitle("Điện thoại");
                        return true;
                    case R.id.laptop:
                        fragmentTransaction.replace(R.id.frameLayout,new Laptop()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        getSupportActionBar().setTitle("Laptop");
                        return true;
                    case R.id.headphone:
                        fragmentTransaction.replace(R.id.frameLayout,new Headphone()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        getSupportActionBar().setTitle("Tai nghe");
                        return true;
                    case R.id.contact:
                        fragmentTransaction.replace(R.id.frameLayout,new Contact()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        getSupportActionBar().setTitle("Liên hệ");
                        return true;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cartMenu:
                Intent intent = new Intent(getApplicationContext(),PaymentActivity.class);
                startActivity(intent);
        }
        if(mActionBar.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.paymentmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}