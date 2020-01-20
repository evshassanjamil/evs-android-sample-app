package com.evs.android.mysampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        long splashTime = 2 * 1000;     // 2s = 2000ms

        (new Handler()).postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            finish();
                                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                        }
                                    }, splashTime);
    }
}
