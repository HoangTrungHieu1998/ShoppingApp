package com.example.shoppingapp.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.shoppingapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class WelcomeActivity extends AppCompatActivity {

    ActionBar actionBar;
    CircleImageView circleImageView;
    TextView mTxtWelcome;
    Button mBtnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);

        // Hide Actionbar
        actionBar = getSupportActionBar();
        actionBar.hide();

        // Ánh xạ
        circleImageView = findViewById(R.id.circleWelcome);
        mTxtWelcome = findViewById(R.id.txtWelcome);
        mBtnStart = findViewById(R.id.btnWelcome);


        // Set animation
        Context context;
        Animation animation_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_down);
        Animation animation_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_up);
        Animation animation_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        circleImageView.setAnimation(animation_down);
        mTxtWelcome.setAnimation(animation_up);
        mBtnStart.setAnimation(animation_up);

        // Set start button
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        });

    }
}