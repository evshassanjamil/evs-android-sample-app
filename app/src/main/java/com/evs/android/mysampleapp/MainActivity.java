package com.evs.android.mysampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.week10.Week10Activity;
import com.evs.android.mysampleapp.week11.Week11Activity;
import com.evs.android.mysampleapp.week12.Week12Activity;
import com.evs.android.mysampleapp.week6.Week6Activity;
import com.evs.android.mysampleapp.week7.Week7Activity;
import com.evs.android.mysampleapp.week8.Week8Activity;
import com.evs.android.mysampleapp.week9.Week9Activity;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class MainActivity extends AppCompatActivity {

    public final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnWeek6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Week6Activity.class));
            }
        });

        findViewById(R.id.btnWeek7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Week7Activity.class));
            }
        });

        findViewById(R.id.btnWeek8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Week8Activity.class));
            }
        });

        findViewById(R.id.btnWeek9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Week9Activity.class));
            }
        });

        findViewById(R.id.btnWeek10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Week10Activity.class));
            }
        });

        findViewById(R.id.btnWeek11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Week11Activity.class));
            }
        });

        findViewById(R.id.btnWeek12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Week12Activity.class));
            }
        });
    }
}
